package com.example.bankintentdemo.model

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 토크나이저 결과.
 * - ids: [CLS] … [SEP] 토큰 ID (패딩 없음, 실제 길이)
 * - wordIds: 토큰마다 속한 어절 번호. [CLS]/[SEP]은 -1
 * - words: 사용자가 입력한 어절(띄어쓰기 단위) 원문. 문장부호는 붙어 있는 어절에 포함
 * - tokens: 디버깅/개발자 모드 표시용 토큰 문자열
 */
data class Encoding(
    val ids: LongArray,
    val wordIds: IntArray,
    val words: List<String>,
    val tokens: List<String>,
)

/**
 * 학습 때 쓴 Hugging Face ElectraTokenizer(BERT WordPiece, do_lower_case=False)와
 * 같은 결과를 내도록 구현한 토크나이저.
 *
 * 처리 순서 (HF BasicTokenizer + WordpieceTokenizer와 동일)
 * 1. 제어문자 제거, 공백류 문자를 공백으로 통일
 * 2. 공백으로 어절 분리
 * 3. 어절 안에서 문장부호와 한자(CJK)를 한 글자씩 별도 토큰으로 분리
    (HF는 한자 앞뒤에 공백을 넣지만, 사용자가 쓴 어절 단위를 유지하려고 어절 안에서 분리. 토큰 결과는 동일)
 * 4. WordPiece 최장 일치. 한 조각이라도 사전에 없으면 그 단어 전체를 [UNK] 하나로 처리
 */
class ElectraTokenizer(private val vocab: Map<String, Long>) {

    companion object {
        private const val UNK = "[UNK]"
        private const val CLS = "[CLS]"
        private const val SEP = "[SEP]"
        private const val MAX_CHARS_PER_WORD = 100
        private val SPECIAL_TOKENS = setOf("[UNK]", "[SEP]", "[PAD]", "[CLS]", "[MASK]")

        /** assets 폴더의 vocab.txt로 토크나이저 생성 */
        fun fromAssets(context: Context, fileName: String = "vocab.txt"): ElectraTokenizer {
            val vocab = HashMap<String, Long>()
            BufferedReader(InputStreamReader(context.assets.open(fileName), Charsets.UTF_8)).use { reader ->
                var index = 0L
                reader.forEachLine { line -> vocab[line.trimEnd('\r')] = index++ }
            }
            return ElectraTokenizer(vocab)
        }
    }

    private val unkId: Long = vocab.getValue(UNK)
    private val clsId: Long = vocab.getValue(CLS)
    private val sepId: Long = vocab.getValue(SEP)
    val padId: Long = vocab.getValue("[PAD]")
    val maskId: Long = vocab.getValue("[MASK]")

    fun encode(text: String, maxLength: Int = 128): Encoding {
        val words = cleanText(text).split(' ').filter { it.isNotEmpty() }

        val tokens = ArrayList<String>()
        val wordIds = ArrayList<Int>()
        tokens.add(CLS); wordIds.add(-1)

        for ((w, word) in words.withIndex()) {
            for (piece in basicSplit(word)) {
                for (sub in wordPiece(piece)) {
                    tokens.add(sub); wordIds.add(w)
                }
            }
        }

        // 길이 제한: [CLS] + 본문 + [SEP] <= maxLength (HF truncation=True와 같은 방식)
        if (tokens.size > maxLength - 1) {
            while (tokens.size > maxLength - 1) {
                tokens.removeAt(tokens.size - 1); wordIds.removeAt(wordIds.size - 1)
            }
        }
        tokens.add(SEP); wordIds.add(-1)

        val ids = LongArray(tokens.size) { vocab[tokens[it]] ?: unkId }
        return Encoding(ids, wordIds.toIntArray(), words, tokens)
    }

    /** 1단계: 제어문자 제거, 공백류 문자를 공백으로 통일 */
    private fun cleanText(text: String): String {
        val sb = StringBuilder(text.length)
        var i = 0
        while (i < text.length) {
            val cp = text.codePointAt(i)
            i += Character.charCount(cp)
            when {
                cp == 0 || cp == 0xFFFD || isControl(cp) -> Unit
                isWhitespace(cp) -> sb.append(' ')
                else -> sb.appendCodePoint(cp)
            }
        }
        return sb.toString()
    }

    /** 3단계: 문장부호·한자 분리 (특수 토큰 문자열은 그대로 유지) */
    private fun basicSplit(word: String): List<String> {
        if (word in SPECIAL_TOKENS) return listOf(word)
        val out = ArrayList<String>()
        val cur = StringBuilder()
        var i = 0
        while (i < word.length) {
            val cp = word.codePointAt(i)
            i += Character.charCount(cp)
            if (isPunctuation(cp) || isChineseChar(cp)) {
                if (cur.isNotEmpty()) { out.add(cur.toString()); cur.setLength(0) }
                out.add(String(Character.toChars(cp)))
            } else {
                cur.appendCodePoint(cp)
            }
        }
        if (cur.isNotEmpty()) out.add(cur.toString())
        return out
    }

    /** 4단계: WordPiece 최장 일치 (코드포인트 단위) */
    private fun wordPiece(piece: String): List<String> {
        val cps = piece.codePoints().toArray()
        if (cps.size > MAX_CHARS_PER_WORD) return listOf(UNK)

        val subTokens = ArrayList<String>()
        var start = 0
        while (start < cps.size) {
            var end = cps.size
            var found: String? = null
            while (start < end) {
                val sub = String(cps, start, end - start)
                val candidate = if (start > 0) "##$sub" else sub
                if (vocab.containsKey(candidate)) { found = candidate; break }
                end--
            }
            if (found == null) return listOf(UNK)   // 한 조각이라도 실패하면 단어 전체를 [UNK]
            subTokens.add(found)
            start = end
        }
        return subTokens
    }

    private fun isWhitespace(cp: Int): Boolean =
        cp == ' '.code || cp == '\t'.code || cp == '\n'.code || cp == '\r'.code ||
            Character.getType(cp) == Character.SPACE_SEPARATOR.toInt()

    private fun isControl(cp: Int): Boolean {
        if (cp == '\t'.code || cp == '\n'.code || cp == '\r'.code) return false
        return when (Character.getType(cp)) {
            Character.CONTROL.toInt(), Character.FORMAT.toInt(), Character.UNASSIGNED.toInt(),
            Character.PRIVATE_USE.toInt(), Character.SURROGATE.toInt() -> true
            else -> false
        }
    }

    private fun isPunctuation(cp: Int): Boolean {
        if (cp in 33..47 || cp in 58..64 || cp in 91..96 || cp in 123..126) return true
        return when (Character.getType(cp)) {
            Character.CONNECTOR_PUNCTUATION.toInt(), Character.DASH_PUNCTUATION.toInt(),
            Character.START_PUNCTUATION.toInt(), Character.END_PUNCTUATION.toInt(),
            Character.INITIAL_QUOTE_PUNCTUATION.toInt(), Character.FINAL_QUOTE_PUNCTUATION.toInt(),
            Character.OTHER_PUNCTUATION.toInt() -> true
            else -> false
        }
    }

    private fun isChineseChar(cp: Int): Boolean =
        cp in 0x4E00..0x9FFF || cp in 0x3400..0x4DBF || cp in 0x20000..0x2A6DF ||
            cp in 0x2A700..0x2B73F || cp in 0x2B740..0x2B81F || cp in 0x2B820..0x2CEAF ||
            cp in 0xF900..0xFAFF || cp in 0x2F800..0x2FA1F
}
