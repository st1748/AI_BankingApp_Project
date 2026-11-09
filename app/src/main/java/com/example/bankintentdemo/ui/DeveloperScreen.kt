package com.example.bankintentdemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.model.Explanation
import com.example.bankintentdemo.model.SlmModelManager
import com.example.bankintentdemo.ui.components.PromptInputBar
import com.example.bankintentdemo.ui.components.intentTitle
import kotlin.math.abs

private val Brand = Color(0xFF0075FF)
private val Negative = Color(0xFFEB7828)
private val Track = Color(0xFFF0F2F5)
private val SubText = Color(0xFF52514E)

private const val MODEL_LABEL = "KD-Small INT8"

/**
 * XAI 개발자 모드: 입력 토큰, 어절별 기여도(Top-3 후보별), Top-3 확률, 추론 시간, 속도 측정.
 */
@Composable
fun DeveloperScreen(
    navController: NavController,
    viewModel: MainViewModel,
    modelManager: SlmModelManager
) {
    val explanation by viewModel.lastExplanation.collectAsState()
    val loading by viewModel.devLoading.collectAsState()
    val benchmarkMs by viewModel.benchmarkMs.collectAsState()

    Scaffold(
        containerColor = Color(0xFFF5F7FA),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로")
                }
                Text("개발자 모드", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.weight(1f))
                Text(
                    text = "XAI",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .background(Brand, RoundedCornerShape(999.dp))
                        .padding(horizontal = 10.dp, vertical = 3.dp)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // key: 다른 문장을 분석하면 입력창도 그 문장으로 갱신
            key(explanation?.text) {
                PromptInputBar(
                    onSendClick = { viewModel.analyzeForDeveloper(it, modelManager) },
                    initialText = explanation?.text ?: ""
                )
            }

            if (loading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    color = Brand
                )
            }

            val e = explanation
            if (e == null) {
                Text(
                    text = "문장을 입력하면 토큰, 어절별 기여도, 추론 시간을 보여줍니다.",
                    color = SubText,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                DeveloperCard(
                    explanation = e,
                    benchmarkMs = benchmarkMs,
                    benchmarkEnabled = !loading,
                    onBenchmark = { viewModel.runBenchmark(e.text, modelManager) }
                )
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DeveloperCard(
    explanation: Explanation,
    benchmarkMs: Double?,
    benchmarkEnabled: Boolean,
    onBenchmark: () -> Unit
) {
    // 어떤 Top-3 후보의 근거를 볼지 (다른 문장이 오면 1순위로 초기화)
    var selected by remember(explanation) { mutableIntStateOf(0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // 1. 입력 토큰
            SectionLabel("입력 토큰 (${explanation.tokens.size}개)")
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                explanation.tokens.forEach { token -> TokenChip(token) }
            }

            // 2. 어절별 기여도 φ (Top-3 후보별 탭)
            SectionLabel("어절 기여도 φ")
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                explanation.top3.forEachIndexed { i, r ->
                    val on = i == selected
                    Text(
                        text = "${i + 1}. ${intentTitle(r.intentIndex)}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (on) Color.White else SubText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .background(if (on) Brand else Track, RoundedCornerShape(999.dp))
                            .clickable { selected = i }
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            val phi = explanation.contributions.getOrNull(selected) ?: FloatArray(0)
            val maxAbs = maxOf(phi.maxOfOrNull { abs(it) } ?: 0f, 1e-6f)
            explanation.words.forEachIndexed { i, word ->
                val v = phi.getOrElse(i) { 0f }
                BarRow(
                    label = word,
                    fraction = abs(v) / maxAbs,
                    color = if (v >= 0f) Brand else Negative,
                    value = "%+.2f".format(v)
                )
            }

            // 3. Top-3 확률
            SectionLabel("Top-3 확률")
            explanation.top3.forEach { r ->
                BarRow(
                    label = intentTitle(r.intentIndex),
                    fraction = r.confidence,
                    color = Color(0xFF8A8984),
                    value = "%.1f%%".format(r.confidence * 100)
                )
            }

            // 4. 모델 정보와 추론 시간
            HorizontalDivider(color = Track, modifier = Modifier.padding(top = 12.dp, bottom = 8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                MetaText("모델", MODEL_LABEL)
                MetaText("추론 (XAI 포함)", "%.2f ms".format(explanation.elapsedMs))
            }

            // 5. 속도 측정 (팜플렛용 기기 추론 시간)
            Spacer(Modifier.height(10.dp))
            OutlinedButton(
                onClick = onBenchmark,
                enabled = benchmarkEnabled,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("속도 측정 (20회 평균)", color = if (benchmarkEnabled) Brand else Color.Gray)
            }
            if (benchmarkMs != null) {
                Text(
                    text = "평균 %.2f ms · 20회 (워밍업 3회 제외) · 어절 %d개".format(benchmarkMs, explanation.words.size),
                    fontSize = 12.sp,
                    color = SubText,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight.ExtraBold,
        color = SubText,
        modifier = Modifier.padding(top = 12.dp, bottom = 6.dp)
    )
}

@Composable
private fun TokenChip(token: String) {
    val special = token == "[CLS]" || token == "[SEP]"
    val unknown = token == "[UNK]"
    Text(
        text = token,
        fontSize = 11.sp,
        fontFamily = FontFamily.Monospace,
        color = when {
            unknown -> Color(0xFFD03B3B)
            special -> Color(0xFF8A8984)
            else -> Color(0xFF0B0B0B)
        },
        modifier = Modifier
            .background(if (unknown) Color(0xFFFDECEC) else Color(0xFFFAFAF9), RoundedCornerShape(4.dp))
            .border(1.dp, if (unknown) Color(0xFFD03B3B) else Color(0xFFD6D5D0), RoundedCornerShape(4.dp))
            .padding(horizontal = 5.dp, vertical = 1.dp)
    )
}

@Composable
private fun BarRow(label: String, fraction: Float, color: Color, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(88.dp)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(9.dp)
                .background(Track, RoundedCornerShape(5.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction.coerceIn(0f, 1f))
                    .background(color, RoundedCornerShape(5.dp))
            )
        }
        Text(
            text = value,
            fontSize = 11.sp,
            fontFamily = FontFamily.Monospace,
            color = SubText,
            modifier = Modifier
                .width(56.dp)
                .padding(start = 6.dp)
        )
    }
}

@Composable
private fun MetaText(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("$label ", fontSize = 11.sp, color = SubText)
        Text(value, fontSize = 11.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
    }
}
