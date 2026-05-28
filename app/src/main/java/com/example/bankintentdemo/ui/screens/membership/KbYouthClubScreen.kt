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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Home
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
fun KbYouthClubScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        YouthClubTopBar(
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
            Spacer(modifier = Modifier.height(42.dp))
            YouthClubTabs()
            Spacer(modifier = Modifier.height(34.dp))
            MilitaryClubBanner()
            Spacer(modifier = Modifier.height(42.dp))
            GradeSummary()
            Spacer(modifier = Modifier.height(34.dp))
            MissionIntro()
            Spacer(modifier = Modifier.height(28.dp))
            MissionItem()
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
private fun YouthClubTopBar(
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
            text = "KB Youth Club 메인",
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
private fun YouthClubTabs() {
    val tabs = listOf("유스클럽", "모아요", "즐겨요", "체크카드")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tabs.forEachIndexed { index, tab ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(if (index == 0) Color(0xFF20242A) else Color.White)
                    .border(1.5.dp, Color(0xFF20242A), RoundedCornerShape(22.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tab,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (index == 0) Color.White else Color(0xFF20242A),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun MilitaryClubBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SoldierFigure(Color(0xFF233D79), "공")
            SoldierFigure(Color(0xFF1F2630), "해")
            SoldierFigure(Color(0xFF4E6F37), "육")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFFE3F5D7), Color(0xFFBDF1D6))
                    )
                )
                .padding(horizontal = 26.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "밀리터리 클럽",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF25282D)
                )
                Icon(
                    modifier = Modifier.size(26.dp),
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = Color(0xFF25282D)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "만 18~29세 현역 병사만 최대 15만원 상당 쿠폰!",
                fontSize = 16.sp,
                color = Color(0xFF25282D),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun SoldierFigure(color: Color, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFD3B6)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5F341E)
            )
        }
        Box(
            modifier = Modifier
                .size(width = 58.dp, height = 76.dp)
                .clip(RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp))
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(26.dp),
                imageVector = Icons.Default.Shield,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.75f)
            )
        }
    }
}

@Composable
private fun GradeSummary() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "이번달은 ",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF25282D)
        )
        GradeBadge()
        Text(
            text = " 루키 등급이네요",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF25282D)
        )
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF25282D)
        )
    }

    Spacer(modifier = Modifier.height(24.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SmallStatCard(
            modifier = Modifier.weight(1f),
            label = "스탬프",
            value = "10"
        )
        SmallStatCard(
            modifier = Modifier.weight(1f),
            label = "행운의 뽑기권",
            value = "1"
        )
    }

    Spacer(modifier = Modifier.height(14.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = "모바일쿠폰",
                fontSize = 17.sp,
                color = Color(0xFF555B62)
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "제휴쿠폰",
                fontSize = 17.sp,
                color = Color(0xFF555B62)
            )
            Text(
                text = "0",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF25282D)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFE0E4E8))
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = "스타포인트",
                fontSize = 17.sp,
                color = Color(0xFF555B62)
            )
            PointBadge()
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "0",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF25282D)
            )
        }
    }
}

@Composable
private fun GradeBadge() {
    Box(
        modifier = Modifier
            .size(34.dp)
            .clip(CircleShape)
            .background(Color(0xFFDFFFE4)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF16BE2F))
        )
    }
}

@Composable
private fun SmallStatCard(
    modifier: Modifier,
    label: String,
    value: String
) {
    Row(
        modifier = modifier
            .height(72.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = label,
            fontSize = 16.sp,
            color = Color(0xFF555B62)
        )
        Text(
            text = value,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF25282D)
        )
    }
}

@Composable
private fun PointBadge() {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color(0xFFFFC928)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "P",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
private fun MissionIntro() {
    Text(
        text = "참여만 해도 스탬프 지급!\n매일 미션하고 스탬프 모아요",
        fontSize = 24.sp,
        lineHeight = 34.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF25282D)
    )
}

@Composable
private fun MissionItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(62.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Default.WaterDrop,
                contentDescription = null,
                tint = Color(0xFFFFB241)
            )
        }

        Spacer(modifier = Modifier.width(18.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "비율 맞추기",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF25282D)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "찾습니다 황금비율 장인",
                fontSize = 15.sp,
                color = Color(0xFF737A83)
            )
        }

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFF4F4F4))
                .padding(horizontal = 14.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Default.MilitaryTech,
                contentDescription = null,
                tint = Color(0xFFFFC928)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "+5",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF25282D)
            )
        }
    }
}
