package com.example.bankintentdemo.ui.screens.membership

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.HelpOutline
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun SalaryClubScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SalaryClubTopBar(
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
                .padding(horizontal = 26.dp)
        ) {
            Spacer(modifier = Modifier.height(74.dp))
            SalaryGuideCard()
            Spacer(modifier = Modifier.height(34.dp))
            SalaryBanner()
            Spacer(modifier = Modifier.height(44.dp))
            ChallengeSection()
            Spacer(modifier = Modifier.height(28.dp))
            DrawEventCard()
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Composable
private fun SalaryClubTopBar(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
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
            text = "급여클럽",
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
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
private fun SalaryGuideCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFE3E7EB), RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(horizontal = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "급여클럽과의 첫 급여를\n기다리고 있어요!",
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF25282D)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "서비스 가이드 확인하기",
                    fontSize = 16.sp,
                    color = Color(0xFF737A83)
                )
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = Color(0xFF737A83)
                )
            }
        }

        Box(
            modifier = Modifier
                .size(width = 110.dp, height = 86.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFFE3F5FF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(54.dp),
                imageVector = Icons.Default.Savings,
                contentDescription = null,
                tint = Color(0xFFFFB724)
            )
        }
    }
}

@Composable
private fun SalaryBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFFAFFD5))
            .padding(horizontal = 22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFFE8F99C)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(42.dp),
                imageVector = Icons.Default.Savings,
                contentDescription = null,
                tint = Color(0xFF63BA1D)
            )
        }

        Spacer(modifier = Modifier.width(24.dp))

        Text(
            text = "급여 관리부터 지출 관리까지\n이번 달 남은 정기지출로 한번에!",
            fontSize = 16.sp,
            lineHeight = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF555B62)
        )
    }
}

@Composable
private fun ChallengeSection() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "급여클럽 혜택 참여하기",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF25282D)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Outlined.HelpOutline,
            contentDescription = null,
            tint = Color(0xFF8F969E)
        )
    }

    Spacer(modifier = Modifier.height(24.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ChallengeCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.CardGiftcard,
            iconColor = Color(0xFF72B8FF),
            title = "따박따박 추첨",
            subtitle = "(매월)"
        )
        ChallengeCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.Casino,
            iconColor = Color(0xFF9D7BFF),
            title = "룰렛 369",
            subtitle = "(연속 x3개월)"
        )
        ChallengeCard(
            modifier = Modifier.weight(1f),
            icon = Icons.Default.ConfirmationNumber,
            iconColor = Color(0xFFFF7797),
            title = "미션 6",
            subtitle = "(총+6개월)"
        )
    }
}

@Composable
private fun ChallengeCard(
    modifier: Modifier,
    icon: ImageVector,
    iconColor: Color,
    title: String,
    subtitle: String
) {
    Column(
        modifier = modifier
            .height(150.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFE3E7EB), RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = icon,
            contentDescription = null,
            tint = iconColor
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3B3F45),
            textAlign = TextAlign.Center
        )
        Text(
            text = subtitle,
            fontSize = 13.sp,
            color = Color(0xFF555B62),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .border(1.dp, Color(0xFFC8CDD2), RoundedCornerShape(20.dp))
                .padding(horizontal = 13.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "도전중",
                fontSize = 14.sp,
                color = Color(0xFF25282D)
            )
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF8F969E)
            )
        }
    }
}

@Composable
private fun DrawEventCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(306.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFDCEBFF))
            .padding(start = 32.dp, top = 34.dp, end = 32.dp),
    ) {
        Text(
            text = "따박따박 추첨",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0B5B9D)
        )

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "매월 급여를 받으시면\n매월 특별한 선물 기회가 플러스!",
            fontSize = 22.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF25282D)
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(112.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(width = 156.dp, height = 92.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFFF2D08E)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "100만원",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF9A6B12)
                )
            }
        }
    }
}
