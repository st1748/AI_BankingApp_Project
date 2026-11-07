package com.example.bankintentdemo.model

import android.content.Context
import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import java.nio.LongBuffer
import kotlin.math.exp

class SlmModelManager(context: Context) {
    private val environment = OrtEnvironment.getEnvironment()
    private val session: OrtSession
    val tokenizer = ElectraTokenizer.fromAssets(context, "vocab.txt")

    init {
        val modelBytes = context.assets.open("model_quantized.onnx").use { it.readBytes() }
        session = environment.createSession(modelBytes, OrtSession.SessionOptions())
    }

    /** 문장 하나를 분류해 확률 상위 3개를 반환 */
    fun predict(text: String): List<PredictionResult> {
        val encoding = tokenizer.encode(text)
        val probs = softmax(runLogits(arrayOf(encoding.ids))[0])
        return probs.indices
            .map { PredictionResult(it, probs[it]) }
            .sortedByDescending { it.confidence }
            .take(3)
    }

    /**
     * 같은 길이의 토큰 열 여러 개를 한 번에 추론해 logits를 반환.
     * 패딩 없이 실제 길이로 넣으므로 attention_mask는 모두 1, token_type_ids는 모두 0.
     * (XAI 어블레이션처럼 길이가 같은 문장 묶음을 한 배치로 돌릴 때 사용)
     */
    fun runLogits(batch: Array<LongArray>): Array<FloatArray> {
        require(batch.isNotEmpty())
        val seqLen = batch[0].size
        require(batch.all { it.size == seqLen }) { "배치 안의 문장 길이가 모두 같아야 합니다." }

        val n = batch.size
        val shape = longArrayOf(n.toLong(), seqLen.toLong())
        val flatIds = LongArray(n * seqLen).also { flat ->
            batch.forEachIndexed { b, ids -> ids.copyInto(flat, b * seqLen) }
        }

        OnnxTensor.createTensor(environment, LongBuffer.wrap(flatIds), shape).use { inputIds ->
            OnnxTensor.createTensor(environment, LongBuffer.wrap(LongArray(n * seqLen) { 1L }), shape).use { mask ->
                OnnxTensor.createTensor(environment, LongBuffer.wrap(LongArray(n * seqLen)), shape).use { typeIds ->
                    val inputs = mapOf(
                        "input_ids" to inputIds,
                        "attention_mask" to mask,
                        "token_type_ids" to typeIds,
                    )
                    session.run(inputs).use { result ->
                        @Suppress("UNCHECKED_CAST")
                        val logits = result.get(0).value as Array<FloatArray>
                        return logits.map { it.copyOf() }.toTypedArray()
                    }
                }
            }
        }
    }

    fun softmax(logits: FloatArray): FloatArray {
        val max = logits.maxOrNull() ?: 0f
        val exps = DoubleArray(logits.size) { exp((logits[it] - max).toDouble()) }
        val sum = exps.sum()
        return FloatArray(logits.size) { (exps[it] / sum).toFloat() }
    }

    fun close() {
        session.close()
    }
}

data class PredictionResult(val intentIndex: Int, val confidence: Float)
