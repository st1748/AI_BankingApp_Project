package com.example.bankintentdemo.ui.screens.life

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
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.VerifiedUser
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
fun PassportRenewalScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        PassportTopBar(
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
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            WalletMainButton()

            Spacer(modifier = Modifier.height(82.dp))

            HeroMessage()

            Spacer(modifier = Modifier.height(52.dp))

            PassportIllustration()

            Spacer(modifier = Modifier.height(78.dp))

            ServiceGuide()

            Spacer(modifier = Modifier.height(32.dp))
        }

        BottomActionBar()
    }
}

@Composable
private fun PassportTopBar(
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
            text = "여권 재발급 신청",
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
private fun WalletMainButton() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(28.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFC5CDD4), RoundedCornerShape(28.dp))
                .padding(horizontal = 22.dp, vertical = 12.dp),
            text = "국민지갑 메인",
            fontSize = 15.sp,
            color = Color(0xFF30343A)
        )
    }
}

@Composable
private fun HeroMessage() {
    Column {
        Text(
            text = "여권 만료일 확인하고\n바로 재발급 신청해보세요",
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "실시간 신청 상태 조회와\n수령기관 가는 길도 알려드려요",
            fontSize = 16.sp,
            lineHeight = 23.sp,
            color = Color(0xFF30343A)
        )
    }
}

@Composable
private fun PassportIllustration() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 360.dp, height = 210.dp)
                .clip(RoundedCornerShape(90.dp))
                .background(Color(0xFFFADBF5))
        )

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(width = 128.dp, height = 150.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(2.dp, Color(0xFF20242A), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(62.dp),
                    imageVector = Icons.Outlined.VerifiedUser,
                    contentDescription = null,
                    tint = Color(0xFF4E6E8E)
                )
            }

            Spacer(modifier = Modifier.size(12.dp))

            Box(
                modifier = Modifier
                    .size(width = 118.dp, height = 156.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF6EA8FF))
                    .border(2.dp, Color(0xFF20242A), RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "PASSPORT",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Icon(
                        modifier = Modifier.size(58.dp),
                        imageVector = Icons.Outlined.Public,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun ServiceGuide() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFF20242A))
                .padding(vertical = 22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "서비스안내",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )

            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Outlined.ExpandLess,
                contentDescription = null,
                tint = Color(0xFF20242A)
            )
        }

        Spacer(modifier = Modifier.height(42.dp))

        Text(
            text = "여권 재발급 신청 방법을 알려드릴게요!",
            fontSize = 18.sp,
            lineHeight = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.height(32.dp))

        GuideStepCard()

        Spacer(modifier = Modifier.height(22.dp))

        Icon(
            modifier = Modifier
                .size(34.dp)
                .align(Alignment.CenterHorizontally),
            imageVector = Icons.Outlined.ExpandMore,
            contentDescription = null,
            tint = Color(0xFF59616A)
        )
    }
}

@Composable
private fun GuideStepCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFF6F6F6))
            .padding(horizontal = 34.dp, vertical = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(54.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterStart),
                imageVector = Icons.Outlined.Public,
                contentDescription = null,
                tint = Color(0xFF20242A)
            )

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD9F1FF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    tint = Color(0xFF256C8A)
                )
            }
        }

        Spacer(modifier = Modifier.size(32.dp))

        Text(
            text = "여권 소지 여부 확인",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
    }
}

@Composable
private fun BottomActionBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(82.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(0.42f)
                .fillMaxSize()
                .background(Color(0xFFE9EEF2)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "취소",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
        }

        Box(
            modifier = Modifier
                .weight(0.58f)
                .fillMaxSize()
                .background(Color(0xFF343D63)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "여권 만료일 확인하기",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
