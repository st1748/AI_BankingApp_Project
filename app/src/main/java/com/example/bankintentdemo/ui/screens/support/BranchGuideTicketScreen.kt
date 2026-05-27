package com.example.bankintentdemo.ui.screens.support

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
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Store
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun BranchGuideTicketScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BranchTopBar(
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
        ) {
            TopInfoSection()
            SearchSection()
            MockMapSection()
            FilterSection()
        }
    }
}

@Composable
private fun BranchTopBar(
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
            text = "지점안내/번호표발행",
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
private fun TopInfoSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 32.dp)
    ) {
        InfoCard(
            icon = Icons.Outlined.AccessTime,
            text = "영업시간 특화/점심시간 집중 점포 안내"
        )

        Spacer(modifier = Modifier.height(12.dp))

        InfoCard(
            icon = Icons.Outlined.AccountBalance,
            text = "은행-증권 복합 점포 안내"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "편의점 제휴ATM 찾기",
                fontSize = 16.sp,
                color = Color(0xFF4D5157)
            )

            Icon(
                modifier = Modifier.size(22.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF4D5157)
            )
        }
    }
}

@Composable
private fun InfoCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, Color(0xFFDADDE1), RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = text,
            fontSize = 17.sp,
            color = Color(0xFF20242A)
        )

        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF8F989F)
        )
    }
}

@Composable
private fun SearchSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF3F5F7))
            .padding(top = 50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 28.dp, vertical = 26.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "지점명/주소",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF8B8F95)
            )

            Icon(
                modifier = Modifier.size(36.dp),
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = Color(0xFF20242A)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(Color(0xFF20242A))
        )
    }
}

@Composable
private fun MockMapSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(330.dp)
            .background(Color(0xFFF1EFE4))
    ) {
        MapGrid()

        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(20.dp),
            text = "역삼동\n강남구\n차백로",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7C6D5A)
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(44.dp)
                .clip(CircleShape)
                .background(Color(0xFF2F80ED))
                .border(6.dp, Color(0x663287FF), CircleShape)
        )

        MapControls(
            modifier = Modifier.align(Alignment.CenterEnd)
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp),
            text = "국민은행 100m",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
    }
}

@Composable
private fun MapGrid() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(7) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color(0xFFE1DCCF))
            )
        }
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(5) {
            Box(
                modifier = Modifier
                    .height(330.dp)
                    .size(width = 2.dp, height = 330.dp)
                    .background(Color(0xFFE1DCCF))
            )
        }
    }
}

@Composable
private fun MapControls(modifier: Modifier) {
    Column(
        modifier = modifier.padding(end = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundMapButton(icon = Icons.Outlined.LocationOn)
        Spacer(modifier = Modifier.height(14.dp))
        RoundMapButton(icon = Icons.Outlined.MyLocation)
        Spacer(modifier = Modifier.height(14.dp))
        MapZoomButton(text = "+")
        Spacer(modifier = Modifier.height(8.dp))
        MapZoomButton(text = "-")
    }
}

@Composable
private fun RoundMapButton(icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Box(
        modifier = Modifier
            .size(54.dp)
            .clip(CircleShape)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF59616A)
        )
    }
}

@Composable
private fun MapZoomButton(text: String) {
    Box(
        modifier = Modifier
            .size(54.dp)
            .clip(CircleShape)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF59616A),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun FilterSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp, vertical = 26.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "가까운 거리",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )

            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .border(1.dp, Color(0xFFC8CDD2), RoundedCornerShape(24.dp))
                    .padding(horizontal = 30.dp, vertical = 12.dp),
                text = "즐겨찾기",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7D858C)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF3F5F7))
                .padding(horizontal = 28.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "전체",
                fontSize = 17.sp,
                color = Color(0xFF20242A)
            )

            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Outlined.FilterAlt,
                contentDescription = null,
                tint = Color(0xFF8F989F)
            )
        }
    }
}
