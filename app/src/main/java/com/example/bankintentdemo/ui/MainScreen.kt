package com.example.bankintentdemo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private sealed interface MainDestination {
    data object Home : MainDestination
    data object Menu : MainDestination
    data class Pending(val title: String) : MainDestination
}

@Composable
fun MainScreen() {
    var destination by remember { mutableStateOf<MainDestination>(MainDestination.Home) }

    when (val current = destination) {
        MainDestination.Home -> NormalHomeScreen(
            onAiToggleClick = { destination = MainDestination.Pending("AI 모드") },
            onMenuClick = { destination = MainDestination.Menu },
            onAccountClick = { destination = MainDestination.Pending("거래내역 조회") },
            onTransferClick = { destination = MainDestination.Pending("이체") },
            onAllAccountsClick = { destination = MainDestination.Pending("전체계좌 조회") },
            onYouthClubClick = { destination = MainDestination.Pending("KB youth club") },
            onBottomMenuClick = { title -> destination = MainDestination.Pending(title) }
        )

        MainDestination.Menu -> MenuScreen(
            onClose = { destination = MainDestination.Home },
            onItemClick = { _, item ->
                destination = MainDestination.Pending(item.title)
            }
        )

        is MainDestination.Pending -> PlaceholderScreen(
            title = current.title,
            message = "${current.title} 화면은 나중에 구현하면 됩니다.",
            buttonText = "홈으로 돌아가기",
            onButtonClick = { destination = MainDestination.Home }
        )
    }
}

@Composable
private fun PlaceholderScreen(
    title: String,
    message: String,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = Color(0xFF25272B),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = message,
                color = Color(0xFF73777F),
                fontSize = 18.sp,
                lineHeight = 26.sp
            )
            Spacer(Modifier.height(28.dp))
            Surface(
                modifier = Modifier
                    .height(52.dp)
                    .clickable(onClick = onButtonClick),
                color = Color(0xFF4A4E54),
                shape = RoundedCornerShape(28.dp)
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 28.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = buttonText,
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
