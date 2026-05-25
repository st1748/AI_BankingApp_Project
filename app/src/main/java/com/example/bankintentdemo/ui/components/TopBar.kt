package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    isAiMode: Boolean,
    onAiModeToggle: (Boolean) -> Unit,
    onMenuClick: () -> Unit // 햄버거 버튼 클릭 시 메뉴 화면으로 이동하기 위한 함수
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 좌측: AI 모드 On/Off 스위치
        AIModeSwitch(
            isAiMode = isAiMode,
            onCheckedChange = onAiModeToggle
        )

        // 우측: 알림, 검색, 햄버거 메뉴 버튼
        Row(verticalAlignment = Alignment.CenterVertically) {
            // 디자인 전용 (기능 없음)
            IconButton(onClick = { /* 기능 없음 */ }) {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "알림", tint = Color.DarkGray)
            }
            // 디자인 전용 (기능 없음)
            IconButton(onClick = { /* 기능 없음 */ }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "검색", tint = Color.DarkGray)
            }
            // 햄버거 버튼 (메뉴 스크린 연결용)
            IconButton(onClick = onMenuClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "전체메뉴", tint = Color.Black)
            }
        }
    }
}