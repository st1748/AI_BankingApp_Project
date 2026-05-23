package com.example.bankintentdemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private sealed interface MainDestination {
    data object Home : MainDestination
    data object Menu : MainDestination
    data object Wallet : MainDestination
    data object Benefits : MainDestination
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
            onBottomMenuClick = { title ->
                destination = when (title) {
                    "지갑" -> MainDestination.Wallet
                    "혜택" -> MainDestination.Benefits
                    else -> MainDestination.Pending(title)
                }
            }
        )

        MainDestination.Menu -> MenuScreen(
            onClose = { destination = MainDestination.Home },
            onItemClick = { _, item -> destination = MainDestination.Pending(item.title) }
        )

        MainDestination.Wallet -> WalletScreen(
            onClose = { destination = MainDestination.Home },
            onItemClick = { title -> destination = MainDestination.Pending(title) }
        )

        MainDestination.Benefits -> BenefitsScreen(
            onBackClick = { destination = MainDestination.Home },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu },
            onItemClick = { title -> destination = MainDestination.Pending(title) }
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
private fun BenefitsScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(horizontal = 24.dp, vertical = 22.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "<  혜택",
                    modifier = Modifier.clickable(onClick = onBackClick),
                    color = Color(0xFF25272B),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.weight(1f))
                Text("☺", color = Color(0xFF25272B), fontSize = 26.sp)
                Spacer(Modifier.width(20.dp))
                Text(
                    text = "⌂",
                    modifier = Modifier.clickable(onClick = onHomeClick),
                    color = Color(0xFF25272B),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(20.dp))
                Text(
                    text = "☰",
                    modifier = Modifier.clickable(onClick = onMenuClick),
                    color = Color(0xFF25272B),
                    fontSize = 32.sp
                )
            }

            Spacer(Modifier.height(44.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFF2C8)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("P", color = Color(0xFFFFB800), fontSize = 28.sp, fontWeight = FontWeight.Black)
                }
                Spacer(Modifier.width(14.dp))
                Text("스타포인트", color = Color(0xFF25272B), fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.weight(1f))
                Text("0P", color = Color(0xFF25272B), fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(8.dp))
                Text(">", color = Color(0xFF8D939B), fontSize = 30.sp)
            }

            Spacer(Modifier.height(38.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(158.dp),
                color = Color(0xFFCFF8CE),
                shape = RoundedCornerShape(18.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 22.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Surface(
                            color = Color(0xFFEFFFBE),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Text(
                                text = "👑 오늘의 혜택",
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                                color = Color(0xFF5A6E44),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(Modifier.height(18.dp))
                        Text(
                            text = "이벤트와 쿠폰을\n한번에 챙겨보세요",
                            color = Color(0xFF25272B),
                            fontSize = 24.sp,
                            lineHeight = 31.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text("🌳", fontSize = 62.sp)
                }
            }

            Spacer(Modifier.height(48.dp))

            Text(
                text = "놓치기 아까운 혜택",
                color = Color(0xFF25272B),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(22.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Surface(
                    color = Color(0xFF4A4E54),
                    shape = RoundedCornerShape(26.dp)
                ) {
                    Text(
                        text = "혜택 모아보기",
                        modifier = Modifier.padding(horizontal = 22.dp, vertical = 12.dp),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(26.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFD8DCE0))
                ) {
                    Text(
                        text = "🎁 이벤트",
                        modifier = Modifier.padding(horizontal = 22.dp, vertical = 12.dp),
                        color = Color(0xFF6F747B),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(30.dp))

            BenefitListItem(
                icon = "🎉",
                eyebrow = "지금 참여 가능한 혜택",
                title = "이벤트",
                badge = "OPEN",
                onClick = { onItemClick("이벤트") }
            )
            BenefitListItem(
                icon = "🎟",
                eyebrow = "내 쿠폰함 바로가기",
                title = "쿠폰함",
                badge = "확인하기",
                onClick = { onItemClick("쿠폰함") }
            )
        }
    }
}

@Composable
private fun BenefitListItem(
    icon: String,
    eyebrow: String,
    title: String,
    badge: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFFF5F6F8)),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, fontSize = 34.sp)
        }
        Spacer(Modifier.width(20.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(eyebrow, color = Color(0xFF7A7F87), fontSize = 17.sp)
            Spacer(Modifier.height(5.dp))
            Text(title, color = Color(0xFF25272B), fontSize = 23.sp, fontWeight = FontWeight.Bold)
        }
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(24.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDDE1E5))
        ) {
            Text(
                text = badge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 9.dp),
                color = Color(0xFF4A4E54),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun WalletScreen(
    onClose: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1F2430)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(start = 20.dp, end = 20.dp, top = 18.dp, bottom = 28.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "×",
                    modifier = Modifier.clickable(onClick = onClose),
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Light
                )
                Spacer(Modifier.width(18.dp))
                Text("국민지갑 전체보기 >", color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(24.dp))

            WalletShortcut(
                title = "모바일 신분증",
                description = "주민등록증, 운전면허증을 안전하게 보관해요.",
                accent = Color(0xFFFFD73D),
                icon = "🪪",
                onClick = { onItemClick("모바일 신분증") }
            )
            Spacer(Modifier.height(10.dp))
            WalletShortcut(
                title = "결제",
                description = "계좌 기반 간편결제와 포인트 사용을 준비했어요.",
                accent = Color(0xFFFFD73D),
                icon = "💳",
                onClick = { onItemClick("결제") }
            )

            Spacer(Modifier.height(14.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color.White,
                shape = RoundedCornerShape(22.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    WalletCardSection(
                        title = "NFT",
                        tabLabels = listOf("아트", "티켓"),
                        icon = "🎟",
                        headline = "가지고 있는 NFT가 없어요",
                        body = "KB국민은행이 진행하는 다양한 이벤트로 NFT를 받을 수 있어요.",
                        buttonText = "NFT 더 알아보기",
                        onClick = { onItemClick("NFT") }
                    )

                    Spacer(Modifier.height(22.dp))

                    WalletCardSection(
                        title = "공공알리미",
                        tabLabels = listOf("국민비서", "전자문서"),
                        icon = "📬",
                        headline = "놓치기 쉬운 안내를 모아드려요",
                        body = "생활에 필요한 공공 알림과 전자문서를 이곳에서 확인할 수 있어요.",
                        buttonText = "공공알리미 보기",
                        onClick = { onItemClick("공공알리미") }
                    )
                }
            }
        }
    }
}

@Composable
private fun WalletShortcut(
    title: String,
    description: String,
    accent: Color,
    icon: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(76.dp)
            .clickable(onClick = onClick),
        color = accent,
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(icon, fontSize = 27.sp)
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = Color(0xFF25272B), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(3.dp))
                Text(description, color = Color(0xFF5E5A36), fontSize = 13.sp)
            }
            Text("›", color = Color(0xFF5E5A36), fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun WalletCardSection(
    title: String,
    tabLabels: List<String>,
    icon: String,
    headline: String,
    body: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, color = Color(0xFF25272B), fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = Color(0xFFF1F3F6)
            ) {
                Row(modifier = Modifier.padding(3.dp)) {
                    tabLabels.forEachIndexed { index, label ->
                        Surface(
                            color = if (index == 0) Color.White else Color.Transparent,
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(
                                text = label,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 7.dp),
                                color = Color(0xFF6F747B),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(22.dp))
        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        listOf(Color(0xFFFFF3B0), Color(0xFFE6ECFF))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, fontSize = 25.sp)
        }
        Spacer(Modifier.height(16.dp))
        Text(headline, color = Color(0xFF25272B), fontSize = 19.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text(body, color = Color(0xFF7A7F87), fontSize = 14.sp, lineHeight = 20.sp)
        Spacer(Modifier.height(16.dp))
        Surface(
            color = Color(0xFFFFD337),
            shape = RoundedCornerShape(3.dp)
        ) {
            Text(
                text = buttonText,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 9.dp),
                color = Color(0xFF25272B),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun SimpleListScreen(
    title: String,
    items: List<String>,
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(horizontal = 28.dp, vertical = 24.dp)
        ) {
            Text(
                text = "<  $title",
                modifier = Modifier.clickable(onClick = onBackClick),
                color = Color(0xFF25272B),
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(26.dp))
            items.forEach { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onItemClick(item) }
                        .padding(vertical = 18.dp, horizontal = 8.dp),
                    color = Color(0xFF25272B),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
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
