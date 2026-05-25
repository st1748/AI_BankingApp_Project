package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AIModeSwitch(
    isAiMode: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // 스위치 컴포넌트
        Switch(
            checked = isAiMode,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF0075FF), // 파란색 활성화
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.LightGray
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        // 텍스트 라벨 (AI Mode On / Off)
        Text(
            text = if (isAiMode) "AI 모드" else "일반 모드",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = if (isAiMode) Color(0xFF0075FF) else Color.DarkGray
        )
    }
}