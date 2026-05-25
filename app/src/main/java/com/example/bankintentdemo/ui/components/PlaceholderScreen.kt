package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun PlaceholderScreen(category: String, menuName: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // "큰메뉴 / 메뉴" 형태로 화면 중앙에 텍스트 출력
        Text(
            text = "$category / $menuName",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}