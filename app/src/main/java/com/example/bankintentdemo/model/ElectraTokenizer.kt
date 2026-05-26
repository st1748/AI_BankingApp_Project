package com.example.bankintentdemo.model

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

class ElectraTokenizer(context: Context, vocabFileName: String) {
    private val vocab = mutableMapOf<String, Long>()
    private val unkToken = "[UNK]"
    private val clsToken = "[CLS]"
    private val sepToken = "[SEP]"
    private val padToken = "[PAD]"

    init {
        loadVocab(context, vocabFileName)
    }

    // 1. assets 폴더의 vocab.txt 사전을 메모리로 불러오는 함수
    private fun loadVocab(context: Context, fileName: String) {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var index = 0L
        reader.forEachLine { line ->
            vocab[line.trim()] = index++
        }
        reader.close()
    }

    // 2. 사용자의 문장을 입력받아 숫자로 변환하는 함수
    fun tokenize(text: String, maxLength: Int = 128): LongArray {
        val tokens = mutableListOf<String>()
        tokens.add(clsToken) // 문장의 시작을 알림

        // 특수문자 제거 및 띄어쓰기 기준 분리
        val cleanText = text.trim().replace(Regex("\\s+"), " ")
        val words = cleanText.split(" ")

        // WordPiece 알고리즘 (글자를 쪼개서 사전에 있는지 매칭)
        for (word in words) {
            var chars = word
            var isFirst = true
            while (chars.isNotEmpty()) {
                var longestMatch = ""
                var matchLength = 0
                for (i in chars.length downTo 1) {
                    val subStr = if (isFirst) chars.substring(0, i) else "##" + chars.substring(0, i)
                    if (vocab.containsKey(subStr)) {
                        longestMatch = subStr
                        matchLength = i
                        break
                    }
                }
                if (longestMatch.isEmpty()) {
                    tokens.add(unkToken)
                    break
                } else {
                    tokens.add(longestMatch)
                    chars = chars.substring(matchLength)
                    isFirst = false
                }
            }
        }

        tokens.add(sepToken) // 문장의 끝을 알림

        // 3. 변환된 토큰들을 모델이 요구하는 길이(128)의 숫자 배열(LongArray)로 변환
        val inputIds = LongArray(maxLength) { vocab[padToken]!! }
        for (i in 0 until minOf(tokens.size, maxLength)) {
            inputIds[i] = vocab[tokens[i]] ?: vocab[unkToken]!!
        }

        return inputIds
    }
}