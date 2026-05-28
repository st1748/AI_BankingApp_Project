package com.example.bankintentdemo.ui.screens.business

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun BossPlusScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BossTopBar(
            onBackClick = { navController.popBackStack() },
            onHomeClick = {
                if (isAiMode) {
                    navController.navigate(AppRoute.AIHome.route) {
                        popUpTo(AppRoute.AIHome.route) { inclusive = true }
                        launchSingleTop = true
                    }
                } else {
                    navController.navigate(AppRoute.NormalHome.route) {
                        popUpTo(AppRoute.NormalHome.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            },
            onMenuClick = { navController.navigate(AppRoute.MainMenu.route) }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            BossHeader()
            CategoryTabs()
            AccountSection()
            SalesSection()
            ProductSection()
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Composable
private fun BossTopBar(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(34.dp)
                .clickable(onClick = onBackClick),
            imageVector = Icons.Outlined.ChevronLeft,
            contentDescription = "뒤로가기",
            tint = Color(0xFF25282D)
        )

        Text(
            modifier = Modifier.weight(1f),
            text = "사장님 홈",
            fontSize = 22.sp,
            color = Color(0xFF25282D)
        )

        Icon(
            modifier = Modifier
                .size(34.dp)
                .clickable(onClick = onHomeClick),
            imageVector = Icons.Outlined.Home,
            contentDescription = "홈",
            tint = Color(0xFF25282D)
        )

        Spacer(modifier = Modifier.width(24.dp))

        Icon(
            modifier = Modifier
                .size(36.dp)
                .clickable(onClick = onMenuClick),
            imageVector = Icons.Default.Menu,
            contentDescription = "전체메뉴",
            tint = Color(0xFF25282D)
        )
    }
}

@Composable
private fun BossHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 26.dp, end = 26.dp, top = 58.dp, bottom = 34.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "이성탁 사장님",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF25282D)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(23.dp),
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = Color(0xFFFFCC2F)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "사장님+ 알림",
                fontSize = 17.sp,
                color = Color(0xFF25282D)
            )
        }
    }
}

@Composable
private fun CategoryTabs() {
    val tabs = listOf("계좌", "매출", "상품", "정책자금", "혜택")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(start = 26.dp, end = 26.dp, bottom = 34.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        tabs.forEachIndexed { index, label ->
            Box(
                modifier = Modifier
                    .height(42.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(if (index == 0) Color(0xFFFFD43B) else Color.White)
                    .border(
                        width = if (index == 0) 0.dp else 1.dp,
                        color = Color(0xFF9AA2AA),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    fontWeight = if (index == 0) FontWeight.Bold else FontWeight.Normal,
                    color = if (index == 0) Color(0xFF25282D) else Color(0xFF8B939B)
                )
            }
        }
    }
}

@Composable
private fun AccountSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEAF3FF))
            .padding(horizontal = 26.dp, vertical = 32.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "사장님을 위한 계좌",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF25282D)
                )
                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = "사업용 계좌를 모아서 보고\n매일 잔액변동 알림도 받으세요",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = Color(0xFF555B62)
                )
            }

            MiniIllustration(
                background = Color(0xFF9EC0FF),
                icon = Icons.Default.Notifications,
                iconColor = Color(0xFFFFC929)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        ActionButton(text = "사업용계좌 설정")
    }
}

@Composable
private fun SalesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8F8F8))
            .padding(horizontal = 26.dp, vertical = 34.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "우리가게 카드매출",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF25282D)
                )
                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = "카드매출액과 입금액을\n매일 알려드려요",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = Color(0xFF555B62)
                )
            }

            MiniIllustration(
                background = Color(0xFFCFE6FF),
                icon = Icons.Default.Storefront,
                iconColor = Color(0xFF5C9DEB)
            )
        }

        Spacer(modifier = Modifier.height(34.dp))

        ActionButton(text = "서비스 가입")
    }
}

@Composable
private fun ProductSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 26.dp, vertical = 34.dp)
    ) {
        Text(
            text = "사업자 금융상품",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF25282D)
        )

        Spacer(modifier = Modifier.height(26.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(126.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFFFF5E8))
                .padding(horizontal = 28.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "개인사업자 대출 갈아타기",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF25282D)
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = "부담되는 이자를 줄여보세요!",
                    fontSize = 15.sp,
                    color = Color(0xFF555B62)
                )
            }

            MiniIllustration(
                background = Color(0xFFF1D5B2),
                icon = Icons.Default.CreditCard,
                iconColor = Color(0xFF9670E8)
            )
        }
    }
}

@Composable
private fun ActionButton(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFBFD9FF)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color(0xFF25282D)
        )
    }
}

@Composable
private fun MiniIllustration(
    background: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color
) {
    Box(
        modifier = Modifier
            .size(width = 112.dp, height = 78.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.75f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = icon,
                contentDescription = null,
                tint = iconColor
            )
        }
    }
}
