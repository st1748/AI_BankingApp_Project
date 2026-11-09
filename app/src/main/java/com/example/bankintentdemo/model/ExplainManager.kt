package com.example.bankintentdemo.model

import kotlin.math.exp

/**
 * 예측 결과 + 판단 근거(어절별 기여도).
 * - words: 사용자가 입력한 어절(띄어쓰기 단위)
 * - tokens: 모델에 들어간 실제 토큰 ([CLS], [UNK] 포함, 개발자 모드 표시용)
 * - top3: 확률 상위 3개 의도
 * - contributions: top3와 같은 순서. 각 배열은 어절별 기여도 φ (words와 같은 길이)
 * - elapsedMs: 토큰화 + 추론 + 기여도 계산에 걸린 시간
 */
data class Explanation(
    val text: String,
    val words: List<String>,
    val tokens: List<String>,
    val top3: List<PredictionResult>,
    val contributions: List<FloatArray>,
    val elapsedMs: Double,
)

/**
 * 어절 단위 Feature Ablation (mask_tokens 방식, 노트북 8-2에서 결정).
 *
 *   φ_i(c) = p(c | 원문) − p(c | 어절 i의 토큰을 모두 [MASK]로 바꾼 문장)
 *
 * 원문 1개 + 어절별로 가린 문장 N개를 같은 길이로 만들어 한 배치로 추론한다.
 * 길이가 그대로라 패딩이 필요 없고, 노트북 8-3에서 검증한 조건과 같다.
 */
class ExplainManager(
    private val tokenizer: ElectraTokenizer,
    private val runLogits: (Array<LongArray>) -> Array<FloatArray>,
) {
    constructor(model: SlmModelManager) : this(model.tokenizer, model::runLogits)

    fun explain(text: String, topK: Int = 3): Explanation {
        val start = System.nanoTime()
        val enc = tokenizer.encode(text)
        val present = enc.wordIds.filter { it >= 0 }.distinct()   // 토큰이 있는 어절 번호

        val batch = ArrayList<LongArray>(present.size + 1)
        batch.add(enc.ids)
        for (w in present) {
            batch.add(LongArray(enc.ids.size) { i -> if (enc.wordIds[i] == w) tokenizer.maskId else enc.ids[i] })
        }

        val probs = runLogits(batch.toTypedArray()).map { softmax(it) }
        val p0 = probs[0]
        val top = p0.indices.sortedByDescending { p0[it] }.take(topK)

        // 한 번의 추론 결과로 Top-3 각각의 기여도를 모두 계산 (추가 비용 없음)
        val contributions = top.map { c ->
            FloatArray(enc.words.size).also { phi ->
                present.forEachIndexed { k, w -> phi[w] = p0[c] - probs[k + 1][c] }
            }
        }
        val elapsedMs = (System.nanoTime() - start) / 1_000_000.0
        return Explanation(
            text = text,
            words = enc.words,
            tokens = enc.tokens,
            top3 = top.map { PredictionResult(it, p0[it]) },
            contributions = contributions,
            elapsedMs = elapsedMs,
        )
    }

    /** 기기 추론 시간 측정: 워밍업 후 runs회 평균 (ms) */
    fun benchmark(text: String, runs: Int = 20, warmup: Int = 3): Double {
        repeat(warmup) { explain(text) }
        var total = 0.0
        repeat(runs) { total += explain(text).elapsedMs }
        return total / runs
    }

    private fun softmax(logits: FloatArray): FloatArray {
        val max = logits.maxOrNull() ?: 0f
        val exps = DoubleArray(logits.size) { exp((logits[it] - max).toDouble()) }
        val sum = exps.sum()
        return FloatArray(logits.size) { (exps[it] / sum).toFloat() }
    }
}
