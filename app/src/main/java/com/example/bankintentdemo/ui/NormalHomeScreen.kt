package com.example.bankintentdemo.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NormalHomeScreen(
    onAiToggleClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onTransferClick: () -> Unit = {},
    onAllAccountsClick: () -> Unit = {},
    onYouthClubClick: () -> Unit = {},
    onBottomMenuClick: (String) -> Unit = {}
) {
    var isAiOn by remember { mutableStateOf(false) }
    var accountPage by remember { mutableIntStateOf(0) }
    var accountMoveDirection by remember { mutableIntStateOf(1) }

    fun moveAccountPage(direction: Int) {
        accountMoveDirection = direction
        accountPage = (accountPage + direction + 3) % 3
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HomeBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(start = 22.dp, end = 22.dp, top = 22.dp, bottom = 122.dp)
        ) {
            HomeTopBar(
                isAiOn = isAiOn,
                onAiToggleClick = {
                    isAiOn = !isAiOn
                    if (isAiOn) onAiToggleClick()
                },
                onMenuClick = onMenuClick
            )
            Spacer(Modifier.height(20.dp))
            PromotionBanner()
            Spacer(Modifier.height(20.dp))
            AccountPager(
                page = accountPage,
                moveDirection = accountMoveDirection,
                onPreviousPage = { moveAccountPage(-1) },
                onNextPage = { moveAccountPage(1) },
                onAccountClick = onAccountClick,
                onTransferClick = onTransferClick
            )
            Spacer(Modifier.height(18.dp))
            AccountPagerFooter(
                onAllAccountsClick = onAllAccountsClick
            )
            Spacer(Modifier.height(22.dp))
            YouthClubBanner(onClick = onYouthClubClick)
            Spacer(Modifier.height(18.dp))
            AssetCard()
            Spacer(Modifier.height(18.dp))
            SmallInfoCard(
                title = "이번주 정기지출",
                subtitle = "예정된 자동이체와 카드 결제일을 확인해보세요."
            )
            Spacer(Modifier.height(14.dp))
            SmallInfoCard(
                title = "오늘의 지출",
                subtitle = "오늘 사용한 금액을 한눈에 확인해보세요."
            )
        }

        BottomNavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            onItemClick = onBottomMenuClick
        )
    }
}

@Composable
private fun HomeTopBar(
    isAiOn: Boolean,
    onAiToggleClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 74.dp, height = 44.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(if (isAiOn) Color(0xFF50639E) else Color(0xFF5E6FAD))
                .clickable(onClick = onAiToggleClick),
            contentAlignment = if (isAiOn) Alignment.CenterEnd else Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }
        Spacer(Modifier.width(12.dp))
        Text(
            text = "회원 이름 >",
            color = HomeText,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.weight(1f))
        Text("♟", color = Color.Black, fontSize = 22.sp)
        Spacer(Modifier.width(24.dp))
        Text("⌕", color = Color.Black, fontSize = 34.sp)
        Spacer(Modifier.width(22.dp))
        Text(
            text = "≡",
            modifier = Modifier.clickable(onClick = onMenuClick),
            color = Color.Black,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun PromotionBanner() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(102.dp),
        shape = RoundedCornerShape(22.dp),
        color = Color(0xFFF1F1F3)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 26.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "KB달리자 적금 가입하면",
                    color = HomeText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "러닝꿀템 VS 전원지급 경품선택",
                    color = HomeText,
                    fontSize = 18.sp
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "• ▬ • • • Ⅱ",
                    color = Color(0xFF858B92),
                    fontSize = 16.sp
                )
            }
            Text("⌚🏃", fontSize = 36.sp)
        }
    }
}

@Composable
private fun AccountPager(
    page: Int,
    moveDirection: Int,
    onPreviousPage: () -> Unit,
    onNextPage: () -> Unit,
    onAccountClick: () -> Unit,
    onTransferClick: () -> Unit
) {
    var dragAmount by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(302.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        if (dragAmount > 45) onPreviousPage()
                        if (dragAmount < -45) onNextPage()
                        dragAmount = 0
                    }
                ) { _, amount ->
                    dragAmount += amount.toInt()
                }
            }
            .clickable(onClick = onAccountClick),
        shape = RoundedCornerShape(26.dp),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(30.dp)) {
            AnimatedContent(
                modifier = Modifier.weight(1f),
                targetState = page,
                transitionSpec = {
                    val direction = if (moveDirection >= 0) 1 else -1
                    slideInHorizontally(animationSpec = tween(260)) { fullWidth -> direction * fullWidth } togetherWith
                        slideOutHorizontally(animationSpec = tween(260)) { fullWidth -> -direction * fullWidth } using
                        SizeTransform(clip = false)
                },
                label = "AccountPager"
            ) { targetPage ->
                when (targetPage) {
                    0 -> AccountCard(onTransferClick = onTransferClick)
                    1 -> PointCard()
                    else -> PrimaryAccountCard(onTransferClick = onTransferClick)
                }
            }
            AccountPageIndicator(
                page = page,
                onPreviousPage = onPreviousPage,
                onNextPage = onNextPage
            )
        }
    }
}

@Composable
private fun AccountCard(onTransferClick: () -> Unit) {
    Column {
        Text(
            text = "KB나라사랑우대통장",
            color = HomeText,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Text("12345-67-891011", color = HomeText, fontSize = 18.sp)
        Spacer(Modifier.height(26.dp))
        Text("잔액 숨김", color = HomeText, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(34.dp))
        TransferButtons(onTransferClick = onTransferClick)
    }
}

@Composable
private fun PointCard() {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text("포인트 적립", color = HomeText, fontSize = 23.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("이번 달 모을 수 있는 포인트 혜택을 확인해보세요.", color = HomeSubText, fontSize = 17.sp)
        Spacer(Modifier.height(30.dp))
        Text("0 P", color = Color(0xFFFFCC33), fontSize = 34.sp, fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
private fun PrimaryAccountCard(onTransferClick: () -> Unit) {
    Column {
        Text("대표계좌", color = HomeText, fontSize = 23.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("KB나라사랑우대통장", color = HomeText, fontSize = 18.sp)
        Spacer(Modifier.height(26.dp))
        Text("잔액 숨김", color = HomeText, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(34.dp))
        TransferButtons(onTransferClick = onTransferClick)
    }
}

@Composable
private fun TransferButtons(onTransferClick: () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        ActionButton(
            modifier = Modifier.weight(1f),
            text = "계좌이체",
            color = Color(0xFFFFD337),
            onClick = onTransferClick
        )
        ActionButton(
            modifier = Modifier.weight(1f),
            text = "연락처 이체",
            color = Color(0xFFE9ECEF),
            onClick = onTransferClick
        )
    }
}

@Composable
private fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(56.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(28.dp),
        color = color
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = text, color = Color.Black, fontSize = 17.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun AccountPageIndicator(
    page: Int,
    onPreviousPage: () -> Unit,
    onNextPage: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "‹",
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = onPreviousPage)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            color = HomeText,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${page + 1} / 3",
            modifier = Modifier.padding(horizontal = 8.dp),
            color = HomeText,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "›",
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = onNextPage)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            color = HomeText,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun AccountPagerFooter(
    onAllAccountsClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.weight(1f))
        Surface(
            modifier = Modifier.clickable(onClick = onAllAccountsClick),
            shape = RoundedCornerShape(28.dp),
            color = Color(0xFF6E737B)
        ) {
            Text(
                text = "전체계좌 보기",
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 11.dp),
                color = Color.White,
                fontSize = 17.sp
            )
        }
    }
}

@Composable
private fun YouthClubBanner(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(22.dp),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("KB Youth Club", color = HomeText, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "OPEN",
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color(0xFF8B5CF6))
                            .padding(horizontal = 5.dp, vertical = 2.dp),
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(9.dp))
                Text("20대만 입장 가능한 비밀공간!", color = HomeSubText, fontSize = 18.sp)
            }
            Text("Youth", color = Color(0xFFFFC83D), fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
private fun AssetCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(138.dp),
        shape = RoundedCornerShape(22.dp),
        color = Color.White
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFFFFFFF),
                                Color(0xFF61D9B6),
                                Color(0xFFFF7A7A)
                            )
                        )
                    )
            )
            Spacer(Modifier.height(12.dp))
            Text("지금까지 모은 자산은 얼마일까요? >", color = HomeSubText, fontSize = 20.sp)
        }
    }
}

@Composable
private fun SmallInfoCard(title: String, subtitle: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.dp),
        shape = RoundedCornerShape(22.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(title, color = HomeText, fontSize = 21.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text(subtitle, color = HomeSubText, fontSize = 15.sp)
        }
    }
}

@Composable
private fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(WindowInsets.navigationBars.asPaddingValues()),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp)
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem("♧", "상품") { onItemClick("추천 상품") }
            BottomNavItem("◔", "지출") {}
            WalletNavItem(onClick = { onItemClick("지갑") })
            BottomNavItem("□", "혜택") { onItemClick("혜택") }
            BottomNavItem("▤", "테마") {}
        }
    }
}

@Composable
private fun BottomNavItem(icon: String, label: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(58.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(icon, color = HomeIcon, fontSize = 29.sp, textAlign = TextAlign.Center)
        Spacer(Modifier.height(5.dp))
        Text(label, color = HomeIcon, fontSize = 14.sp)
    }
}

@Composable
private fun WalletNavItem(onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color(0xFFC5A052)),
            contentAlignment = Alignment.Center
        ) {
            Text("▣", color = Color.White, fontSize = 32.sp)
        }
        Spacer(Modifier.height(4.dp))
        Text("지갑", color = HomeIcon, fontSize = 14.sp)
    }
}

private val HomeBackground = Color(0xFFF4F4F5)
private val HomeText = Color(0xFF2B2E33)
private val HomeSubText = Color(0xFF6F747B)
private val HomeIcon = Color(0xFF4B5158)

@Preview(showBackground = true)
@Composable
private fun NormalHomeScreenPreview() {
    NormalHomeScreen()
}
