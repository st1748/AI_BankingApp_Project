package com.example.bankintentdemo.ui.screens.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.VerifiedUser
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun MobileIdScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MobileIdTopBar(
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
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            WalletMainButtonArea()
            MobileIdHeroSection()
            MobileIdIntroSection()
            MobileIdUsageSection()
            MobileIdIssueSection()
        }

        BottomUseButton()
    }
}

@Composable
private fun MobileIdTopBar(
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
            text = "모바일 신분증",
            fontSize = 25.sp,
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
private fun WalletMainButtonArea() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .border(width = 1.dp, color = Color(0xFFE1E4E8))
            .padding(horizontal = 28.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(28.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFC5CDD4), RoundedCornerShape(28.dp))
                .padding(horizontal = 22.dp, vertical = 11.dp),
            text = "국민지갑 메인",
            fontSize = 18.sp,
            color = Color(0xFF30343A)
        )
    }
}

@Composable
private fun MobileIdHeroSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEAF6FF))
            .padding(top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "신분증이 필요한 순간\n모바일 신분증 하나로",
            fontSize = 30.sp,
            lineHeight = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "안전하고 간편한 모바일 신분증\n바로 발급해보세요",
            fontSize = 20.sp,
            lineHeight = 29.sp,
            color = Color(0xFF555C63),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(50.dp))

        MobileIdCardStack()

        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Composable
private fun MobileIdCardStack() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(430.dp),
        contentAlignment = Alignment.Center
    ) {
        IdCard(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 4.dp)
                .graphicsLayer {
                    rotationZ = -90f
                    alpha = 0.68f
                },
            width = 250.dp,
            height = 156.dp,
            backgroundColor = Color(0xFFDFF3FF),
            title = "자동차운전면허증",
            badgeColor = Color(0xFFE34A5F)
        )

        Box(
            modifier = Modifier
                .size(width = 290.dp, height = 360.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF9BDCF1), Color(0xFFA9A6FF), Color(0xFFC99AEA))
                    )
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "‹ 운전면허증", fontSize = 15.sp, color = Color(0xFF59616A))
                Text(text = "주민등록증 ›", fontSize = 15.sp, color = Color(0xFF59616A))
            }

            IdCard(
                modifier = Modifier.graphicsLayer { rotationZ = 90f },
                width = 270.dp,
                height = 170.dp,
                backgroundColor = Color(0xFFF8FFF8),
                title = "국가보훈부장관",
                badgeColor = Color(0xFFD5483E)
            )

            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 74.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .padding(horizontal = 20.dp, vertical = 9.dp),
                text = "눌러서 정보 확인",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
        }

        IdCard(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 4.dp)
                .graphicsLayer {
                    rotationZ = 90f
                    alpha = 0.78f
                },
            width = 250.dp,
            height = 156.dp,
            backgroundColor = Color(0xFFFFF4C8),
            title = "주민등록증",
            badgeColor = Color(0xFF4BA340)
        )
    }
}

@Composable
private fun IdCard(
    modifier: Modifier,
    width: androidx.compose.ui.unit.Dp,
    height: androidx.compose.ui.unit.Dp,
    backgroundColor: Color,
    title: String,
    badgeColor: Color
) {
    Box(
        modifier = modifier
            .size(width = width, height = height)
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .border(1.dp, Color(0xFFE4E8EC), RoundedCornerShape(14.dp))
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.TopEnd),
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(width = 70.dp, height = 86.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(44.dp),
                imageVector = Icons.Outlined.Person,
                contentDescription = null,
                tint = Color(0xFFE4A35A)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 6.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = "김국민", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "123456-1234567", fontSize = 13.sp, color = Color(0xFF30343A))
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(34.dp)
                .clip(CircleShape)
                .background(badgeColor)
        )
    }
}

@Composable
private fun MobileIdIntroSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEAF6FF))
            .padding(horizontal = 28.dp, vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "모바일 신분증이란?",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "사용자의 스마트폰에 저장되어,\n실물 신분증과 동일한 효력을 갖는\n디지털 신분증 이에요",
            fontSize = 21.sp,
            lineHeight = 31.sp,
            color = Color(0xFF555C63),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(44.dp))

        IcIdGuideCard()
    }
}

@Composable
private fun IcIdGuideCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 42.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "IC칩이 내장된 신분증이 필요해요",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "IC 신분증을 스마트폰에 접촉하여\n모바일 신분증을 발급할 수 있으며,\n신분증 하단에 모바일 신분증 표시가 있어요",
            fontSize = 20.sp,
            lineHeight = 31.sp,
            color = Color(0xFF555C63),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(38.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFF4FAFF))
                .padding(horizontal = 22.dp, vertical = 38.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IdCard(
                modifier = Modifier,
                width = 300.dp,
                height = 190.dp,
                backgroundColor = Color(0xFFE5F6FF),
                title = "자동차운전면허증",
                badgeColor = Color(0xFF4D9DE0)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "예시. IC 운전면허증",
                fontSize = 19.sp,
                color = Color(0xFF6C737A)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "(출처 : 도로교통공단)",
                fontSize = 17.sp,
                color = Color(0xFF8B939B)
            )
        }
    }
}

@Composable
private fun MobileIdUsageSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 28.dp, vertical = 72.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "모바일 신분증\n어디에 사용하나요?",
            fontSize = 30.sp,
            lineHeight = 39.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "온/오프라인 실물 신분증이 필요한 모든 순간에\n동일하게 사용할 수 있어요",
            fontSize = 20.sp,
            lineHeight = 30.sp,
            color = Color(0xFF555C63),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(44.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            UsageCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.AccountBox,
                title = "본인확인",
                subtitle = "은행, 관공서",
                backgroundColor = Color(0xFFEAF8F3)
            )
            UsageCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.CheckCircle,
                title = "성인인증",
                subtitle = "편의점, 음식점",
                backgroundColor = Color(0xFFFFF0F1)
            )
            UsageCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.VerifiedUser,
                title = "본인인증",
                subtitle = "모바일, PC",
                backgroundColor = Color(0xFFEAF6FF)
            )
        }
    }
}

@Composable
private fun UsageCard(
    modifier: Modifier,
    icon: ImageVector,
    title: String,
    subtitle: String,
    backgroundColor: Color
) {
    Column(
        modifier = modifier
            .height(196.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(42.dp),
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = subtitle,
            fontSize = 17.sp,
            color = Color(0xFF555C63),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun MobileIdIssueSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F7F7))
            .padding(horizontal = 28.dp, vertical = 72.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "모바일 신분증\n어떻게 발급받나요?",
            fontSize = 30.sp,
            lineHeight = 39.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "IC 신분증을 가지고 계시다면\n모바일 신분증을 발급받을 수 있어요",
            fontSize = 20.sp,
            lineHeight = 30.sp,
            color = Color(0xFF555C63),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(58.dp))

        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFEAF6FF))
                .padding(horizontal = 28.dp, vertical = 11.dp),
            text = "01",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2F80ED)
        )

        Spacer(modifier = Modifier.height(44.dp))
    }
}

@Composable
private fun BottomUseButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(82.dp)
            .background(Color(0xFF343D63)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "모바일 신분증 이용하기",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}
