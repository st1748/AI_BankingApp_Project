package com.example.bankintentdemo.ui.screens.product.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductTopBar(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.ChevronLeft,
            contentDescription = "뒤로가기",
            modifier = Modifier
                .size(32.dp)
                .clickable(onClick = onBackClick),
            tint = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = "금융상품",
            fontSize = 20.sp,
            color = Color(0xFF20242A),
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Outlined.SupportAgent, // 챗봇 아이콘 대체
            contentDescription = "챗봇",
            modifier = Modifier.size(28.dp),
            tint = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = "홈",
            modifier = Modifier
                .size(28.dp)
                .clickable(onClick = onHomeClick),
            tint = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = Icons.Outlined.Menu,
            contentDescription = "메뉴",
            modifier = Modifier
                .size(28.dp)
                .clickable(onClick = onMenuClick),
            tint = Color(0xFF20242A)
        )
    }
}