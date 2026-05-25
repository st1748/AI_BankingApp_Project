package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromptInputBar(onSendClick: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 좌측 + 버튼
        Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(28.dp))

        Spacer(modifier = Modifier.width(12.dp))

        // 중앙 입력창
        Row(
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .background(Color(0xFFF3F4F6), RoundedCornerShape(25.dp))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f),
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) Text("무엇을 도와드릴까요?", color = Color.Gray, fontSize = 15.sp)
                    innerTextField()
                }
            )
            // 우측 마이크 버튼
            IconButton(onClick = { /* 음성인식 */ }, modifier = Modifier.size(24.dp)) {
                Icon(imageVector = Icons.Default.Mic, contentDescription = null, tint = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // 우측 음성/전송 버튼
        IconButton(
            onClick = {
                if (text.isNotEmpty()) { onSendClick(text); text = "" }
            },
            modifier = Modifier.size(40.dp).background(Color(0xFFF3F4F6), RoundedCornerShape(12.dp))
        ) {
            Icon(imageVector = Icons.Default.GraphicEq, contentDescription = null, tint = Color.Gray)
        }
    }
}

// TextField 대신 더 자유로운 커스텀을 위한 BasicTextField 사용
@Composable
fun BasicTextField(value: String, onValueChange: (String) -> Unit, modifier: Modifier, decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit) {
    androidx.compose.foundation.text.BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        decorationBox = decorationBox
    )
}