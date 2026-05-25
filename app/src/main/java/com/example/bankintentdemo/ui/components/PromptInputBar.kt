package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromptInputBar(onSendClick: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFF0F2F5), RoundedCornerShape(24.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* 음성 인식 기능 (추후 구현) */ }) {
            Icon(imageVector = Icons.Default.Mic, contentDescription = "음성 입력", tint = Color.Gray)
        }

        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("예: 엄마한테 5만원 보내줘") },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        IconButton(
            onClick = {
                if (text.isNotBlank()) {
                    onSendClick(text)
                    text = "" // 전송 후 초기화
                }
            }
        ) {
            Icon(imageVector = Icons.Default.Send, contentDescription = "전송", tint = Color(0xFF0075FF))
        }
    }
}