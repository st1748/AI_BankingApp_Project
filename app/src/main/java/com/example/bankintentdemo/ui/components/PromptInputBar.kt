package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun PromptInputBar(
    onSendClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // 입력된 텍스트 상태 관리
    var inputText by remember { mutableStateOf("") }

    // 키보드 및 포커스 제어 매니저
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 텍스트 입력 창
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            modifier = Modifier.weight(1f),
            placeholder = { Text("원하시는 업무를 입력해주세요") },
            shape = RoundedCornerShape(24.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF0075FF),
                unfocusedBorderColor = Color.LightGray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            // 키보드 엔터키를 '전송(Send)' 아이콘으로 변경
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            // 키보드 엔터키를 눌렀을 때의 동작
            keyboardActions = KeyboardActions(
                onSend = {
                    if (inputText.isNotBlank()) {
                        onSendClick(inputText)
                        keyboardController?.hide() // 키보드 강제 숨김
                        focusManager.clearFocus()  // 커서 포커스 해제
                    }
                }
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        // 전송 버튼
        IconButton(
            onClick = {
                if (inputText.isNotBlank()) {
                    onSendClick(inputText)
                    keyboardController?.hide() // 키보드 강제 숨김
                    focusManager.clearFocus()  // 커서 포커스 해제
                }
            },
            modifier = Modifier
                .size(48.dp)
                .background(color = Color(0xFF0075FF), shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "전송",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}