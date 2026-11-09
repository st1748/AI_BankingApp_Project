package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Brand = Color(0xFF0075FF)

/**
 * 어절별 기여도를 색 농도로 표시 (사용자 모드).
 * 기여도가 threshold 이상인 어절만 파란색으로 강조하고, 나머지는 회색으로 둔다.
 * (INT8 양자화 때문에 작은 기여도는 흔들릴 수 있어 노이즈를 숨기려는 목적, 노트북 8-3 참고)
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContributionHighlights(
    words: List<String>,
    phi: FloatArray,
    modifier: Modifier = Modifier,
    threshold: Float = 0.1f,
) {
    val maxPos = maxOf(phi.maxOrNull() ?: 0f, threshold)
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        words.forEachIndexed { i, word ->
            val v = phi.getOrElse(i) { 0f }
            val strong = v >= threshold
            val alpha = if (strong) (0.3f + 0.7f * (v / maxPos)).coerceIn(0.3f, 1f) else 0f
            val bg = if (strong) Brand.copy(alpha = alpha) else Color(0xFFF0F2F5)
            val fg = when {
                strong && alpha > 0.6f -> Color.White
                strong -> Color(0xFF0B0B0B)
                else -> Color(0xFF6B6B6B)
            }
            Text(
                text = word,
                color = fg,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(bg, RoundedCornerShape(6.dp))
                    .padding(horizontal = 7.dp, vertical = 2.dp),
            )
        }
    }
}
