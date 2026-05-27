package com.example.bankintentdemo.model

import android.content.Context
import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import java.nio.LongBuffer
import kotlin.math.exp

class SlmModelManager(context: Context) {
    private val environment = OrtEnvironment.getEnvironment()
    private var session: OrtSession? = null
    private val tokenizer = ElectraTokenizer(context, "vocab.txt")

    init {
        val modelBytes = context.assets.open("model_quantized.onnx").readBytes()
        session = environment.createSession(modelBytes, OrtSession.SessionOptions())
    }

    // 결과를 3개 묶음(List)으로 반환
    fun predict(text: String): List<PredictionResult> {
        val inputIds = tokenizer.tokenize(text, 128)
        val attentionMask = LongArray(128) { if (inputIds[it] != 0L) 1L else 0L }
        val tokenTypeIds = LongArray(128) { 0L }

        val shape = longArrayOf(1, 128)
        val inputIdsTensor = OnnxTensor.createTensor(environment, LongBuffer.wrap(inputIds), shape)
        val attentionMaskTensor = OnnxTensor.createTensor(environment, LongBuffer.wrap(attentionMask), shape)
        val tokenTypeIdsTensor = OnnxTensor.createTensor(environment, LongBuffer.wrap(tokenTypeIds), shape)

        val inputs = mapOf("input_ids" to inputIdsTensor, "attention_mask" to attentionMaskTensor, "token_type_ids" to tokenTypeIdsTensor)
        val result = session?.run(inputs)
        val logits = (result?.get(0)?.value as Array<FloatArray>)[0]

        val expLogits = logits.map { exp(it.toDouble()) }
        val sumExp = expLogits.sum()
        val probabilities = expLogits.map { (it / sumExp).toFloat() }

        // 확률 높은 순으로 3개 뽑기
        val top3Results = probabilities.indices
            .map { PredictionResult(it, probabilities[it]) }
            .sortedByDescending { it.confidence }
            .take(3)

        inputIdsTensor.close()
        attentionMaskTensor.close()
        tokenTypeIdsTensor.close()
        result?.close()

        return top3Results
    }

    fun close() {
        session?.close()
        environment.close()
    }
}

data class PredictionResult(val intentIndex: Int, val confidence: Float)