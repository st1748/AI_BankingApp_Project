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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalAtm
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun KbStarClubScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        KbStarClubTopBar(
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
            MembershipSummary()
            BenefitSection()
            SectionDivider()
            EventSection()
            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
private fun KbStarClubTopBar(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
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
            text = "KB스타클럽",
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
private fun MembershipSummary() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 26.dp, end = 26.dp, top = 24.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Text(
                modifier = Modifier.weight(1f),
                text = "이성탁님은\n패밀리 고객입니다",
                fontSize = 22.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF25282D)
            )

            Icon(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(30.dp),
                imageVector = Icons.Default.Settings,
                contentDescription = "설정",
                tint = Color(0xFF25282D)
            )
        }

        Spacer(modifier = Modifier.height(38.dp))

        GradeProgress()

        Spacer(modifier = Modifier.height(28.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF25282D)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "스타클럽 별을 ",
                fontSize = 17.sp,
                color = Color(0xFF25282D)
            )
            Text(
                text = "0개",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF25282D)
            )
            Text(
                text = " 모았어요",
                fontSize = 17.sp,
                color = Color(0xFF25282D)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF25282D)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        JoinBenefitBanner()
    }
}

@Composable
private fun GradeProgress() {
    val labels = listOf("패밀리", "베스트", "그랜드", "VIP", "VVIP")

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            labels.forEachIndexed { index, _ ->
                if (index == 0) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF43B02A)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "F",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .size(9.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFB9B9B9))
                    )
                }

                if (index < labels.lastIndex) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .padding(horizontal = 9.dp)
                            .background(Color(0xFFE0E0D6))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            labels.forEachIndexed { index, label ->
                Text(
                    modifier = Modifier.weight(1f),
                    text = label,
                    fontSize = 13.sp,
                    fontWeight = if (index == 0) FontWeight.Bold else FontWeight.Normal,
                    color = if (index == 0) Color(0xFF43B02A) else Color(0xFF25282D)
                )
            }
        }
    }
}

@Composable
private fun JoinBenefitBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(104.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(Color(0xFFFFE66B), Color(0xFFFFBD45))
                )
            )
            .padding(start = 26.dp, end = 22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "회원가입하고 내 등급 혜택누리기",
            fontSize = 17.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF25282D)
        )

        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(Color(0x26A76D00)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(25.dp),
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color(0xFF25282D)
            )
        }
    }
}

@Composable
private fun BenefitSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp, vertical = 38.dp)
    ) {
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                modifier = Modifier.weight(1f),
                text = "고객님께 드리는\n특별한 모든 혜택",
                fontSize = 22.sp,
                lineHeight = 31.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF25282D)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "전체보기",
                    fontSize = 14.sp,
                    color = Color(0xFF4F5359)
                )
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = Color(0xFF9AA0A6)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color(0xFF8F969E), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "등급별 혜택 확인하기",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF25282D)
            )
        }
    }
}

@Composable
private fun SectionDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(12.dp)
            .background(Color(0xFFF1F3F5))
    )
}

@Composable
private fun EventSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp, vertical = 34.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = "이벤트",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF25282D)
            )

            Text(
                text = "전체보기",
                fontSize = 14.sp,
                color = Color(0xFF4F5359)
            )
            Icon(
                modifier = Modifier.size(22.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF9AA0A6)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(116.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFEAF2FF))
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "KB손해보험",
                    fontSize = 14.sp,
                    color = Color(0xFF7A8088)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "보험 상담만 해도 1만 포인트\n증정!",
                    fontSize = 18.sp,
                    lineHeight = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF25282D)
                )
            }

            EventPointIcon()
        }
    }
}

@Composable
private fun EventPointIcon() {
    Box(
        modifier = Modifier.size(width = 92.dp, height = 84.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 54.dp, height = 64.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFE86FC3)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Outlined.LocalAtm,
                contentDescription = null,
                tint = Color(0xFFFFC5ED)
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(34.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFA629)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "P",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
