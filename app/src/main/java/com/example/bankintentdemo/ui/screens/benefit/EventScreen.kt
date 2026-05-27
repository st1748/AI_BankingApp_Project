package com.example.bankintentdemo.ui.screens.benefit

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
import androidx.compose.material.icons.outlined.Celebration
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.outlined.VolunteerActivism
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun EventScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        EventTopBar(
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
            EventTabs()
            FilterChips()
            RecommendedTitle()
            EventCardList()
            MoreButton()
            Spacer(modifier = Modifier.height(180.dp))
        }
    }
}

@Composable
private fun EventTopBar(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(74.dp)
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
            text = "이벤트",
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
private fun EventTabs() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .border(width = 0.dp, color = Color.Transparent),
        verticalAlignment = Alignment.Bottom
    ) {
        EventTabText(
            modifier = Modifier.weight(1f),
            text = "진행중이벤트",
            selected = true
        )
        EventTabText(
            modifier = Modifier.weight(1f),
            text = "응모/당첨확인",
            selected = false
        )
        EventTabText(
            modifier = Modifier.weight(0.72f),
            text = "설문",
            selected = false
        )
        Box(
            modifier = Modifier
                .weight(0.5f)
                .height(64.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search",
                tint = Color(0xFF363A40)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFDADDE1))
    )
}

@Composable
private fun EventTabText(
    modifier: Modifier,
    text: String,
    selected: Boolean
) {
    Column(
        modifier = modifier.height(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = text,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = if (selected) Color(0xFF20242A) else Color(0xFF7C8289)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(if (selected) Color(0xFF20242A) else Color.Transparent)
        )
    }
}

@Composable
private fun FilterChips() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 22.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        EventChip(text = "전체", selected = false)
        EventChip(text = "마감임박", selected = false)
        EventChip(text = "맞춤추천", selected = true)
    }
}

@Composable
private fun EventChip(
    text: String,
    selected: Boolean
) {
    Text(
        modifier = Modifier
            .clip(RoundedCornerShape(28.dp))
            .background(if (selected) Color(0xFFFFD84D) else Color.White)
            .border(
                width = if (selected) 0.dp else 1.dp,
                color = Color(0xFF9AA2AA),
                shape = RoundedCornerShape(28.dp)
            )
            .padding(horizontal = 24.dp, vertical = 12.dp),
        text = text,
        fontSize = 20.sp,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
        color = if (selected) Color(0xFF20242A) else Color(0xFF8B939B)
    )
}

@Composable
private fun RecommendedTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = 22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "추천해요",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Icon(
            modifier = Modifier.size(34.dp),
            imageVector = Icons.Outlined.ThumbUp,
            contentDescription = null,
            tint = Color(0xFF78BFFF)
        )
    }
}

@Composable
private fun EventCardList() {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        EventCard(
            title = "자취지원금 총 2천만원 쏜다",
            description = "20대라면 누구나 OK!",
            period = "2026.05.12 ~ 2026.06.30",
            backgroundColor = Color(0xFFF0EFFD),
            icon = Icons.Outlined.VolunteerActivism,
            badgeText = "돈다발",
            iconBackground = Color(0xFFFFF7D8)
        )
        EventCard(
            title = "KB GS Pay통장 만들고",
            description = "스페셜 카드박스 응모하기",
            period = "2026.05.25 ~ 2026.05.31",
            backgroundColor = Color(0xFFFFEEF1),
            icon = Icons.Outlined.Celebration,
            badgeText = "STAR",
            iconBackground = Color(0xFFFFFFFF)
        )
        EventCard(
            title = "밀크 Day 5월 30일 도착!",
            description = "선물 하나만 고르시지 말입니다",
            period = "2026.05.12 ~ 2026.05.29",
            backgroundColor = Color(0xFFEAF7E9),
            icon = Icons.Outlined.Pets,
            badgeText = "호요",
            iconBackground = Color(0xFFE1F0CE)
        )
    }
}

@Composable
private fun EventCard(
    title: String,
    description: String,
    period: String,
    backgroundColor: Color,
    icon: ImageVector,
    badgeText: String,
    iconBackground: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(134.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .padding(start = 20.dp, end = 22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = description,
                fontSize = 19.sp,
                color = Color(0xFF3D4147)
            )
            Spacer(modifier = Modifier.height(17.dp))
            Text(
                text = period,
                fontSize = 18.sp,
                color = Color(0xFF727981)
            )
        }

        EventIllustration(
            icon = icon,
            badgeText = badgeText,
            iconBackground = iconBackground
        )
    }
}

@Composable
private fun EventIllustration(
    icon: ImageVector,
    badgeText: String,
    iconBackground: Color
) {
    Box(
        modifier = Modifier.size(width = 132.dp, height = 104.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(98.dp)
                .clip(CircleShape)
                .background(iconBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(58.dp),
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF394047)
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .clip(CircleShape)
                .background(Color(0xFFFF5B4F))
                .padding(horizontal = 8.dp, vertical = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = badgeText,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
private fun MoreButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 34.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "+ 더보기",
            fontSize = 24.sp,
            color = Color(0xFF20242A)
        )
    }
}
