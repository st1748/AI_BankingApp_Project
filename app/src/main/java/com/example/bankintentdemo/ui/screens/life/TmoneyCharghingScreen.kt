package com.example.bankintentdemo.ui.screens.life

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun TmoneyCharghingScreen(
    navController: NavController,

    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F4F4))
    ) {
        TmoneyTopBar(
            onBackClick = { navController.popBackStack() },
            onHomeClick = {
                navController.navigate(
                    if (isAiMode) AppRoute.AIHome.route else AppRoute.NormalHome.route
                ) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = false
                    }
                    launchSingleTop = true
                }
            },
            onMenuClick = { navController.navigate(AppRoute.MainMenu.route) }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 22.dp)
        ) {
            MainChargeCard()

            Spacer(modifier = Modifier.height(22.dp))

            QuizCard()

            Spacer(modifier = Modifier.height(32.dp))

            ServiceInfoCard()

            Spacer(modifier = Modifier.height(28.dp))

            ChargeHistoryCard()

            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Composable
private fun TmoneyTopBar(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.White)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(34.dp)
                .clickable(onClick = onBackClick),
            imageVector = Icons.Outlined.ChevronLeft,
            contentDescription = "Back",
            tint = Color(0xFF20242A)
        )

        Text(
            modifier = Modifier.weight(1f),
            text = "티머니 교통카드 충전",
            fontSize = 19.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF20242A)
        )

        Icon(
            modifier = Modifier
                .size(34.dp)
                .clickable(onClick = onHomeClick),
            imageVector = Icons.Outlined.Home,
            contentDescription = "Home",
            tint = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.size(26.dp))

        Icon(
            modifier = Modifier
                .size(36.dp)
                .clickable(onClick = onMenuClick),
            imageVector = Icons.Outlined.Menu,
            contentDescription = "Menu",
            tint = Color(0xFF20242A)
        )
    }
}

@Composable
private fun MainChargeCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .padding(horizontal = 28.dp, vertical = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "편의점 갈 필요 없이",
            fontSize = 17.sp,
            color = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "티머니 교통카드 충전하기!",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.height(34.dp))

        TmoneyIllustration()

        Spacer(modifier = Modifier.height(34.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFFFD43B)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "충전하기",
                fontSize = 17.sp,
                color = Color(0xFF20242A)
            )
        }
    }
}

@Composable
private fun TmoneyIllustration() {
    Box(
        modifier = Modifier.size(width = 190.dp, height = 120.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 88.dp, height = 118.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF9FCFD))
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "T",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF9C1BA5)
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(width = 90.dp, height = 70.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFFFF7B69), Color(0xFFFFB53F))
                    )
                )
        )

        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(30.dp),
            imageVector = Icons.Outlined.Star,
            contentDescription = null,
            tint = Color(0xFFFFD43B)
        )
    }
}

@Composable
private fun QuizCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(horizontal = 28.dp, vertical = 22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "더 알뜰하게 충전하려면?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "퀴즈 풀고 스타포인트 받기",
                fontSize = 16.sp,
                color = Color(0xFF20242A)
            )
        }

        Box(
            modifier = Modifier
                .size(58.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF6F7F8)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(36.dp),
                imageVector = Icons.Outlined.Quiz,
                contentDescription = null,
                tint = Color(0xFF6A9AFB)
            )
        }
    }
}

@Composable
private fun ServiceInfoCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(horizontal = 28.dp, vertical = 22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "티머니 카드 충전 서비스 안내",
            fontSize = 17.sp,
            color = Color(0xFF20242A)
        )

        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF20242A)
        )
    }
}

@Composable
private fun ChargeHistoryCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(horizontal = 28.dp, vertical = 28.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFD849A9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Outlined.Bolt,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.size(14.dp))

            Text(
                text = "충전 내역",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
        }

        Spacer(modifier = Modifier.height(42.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "충전 내역이 없습니다.",
            fontSize = 17.sp,
            color = Color(0xFF20242A),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
