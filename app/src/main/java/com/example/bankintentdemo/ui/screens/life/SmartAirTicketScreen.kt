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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AirplanemodeActive
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Luggage
import androidx.compose.material.icons.outlined.Menu
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
fun SmartAirTicketScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SmartAirTopBar(
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
                .padding(horizontal = 28.dp)
        ) {
            Spacer(modifier = Modifier.height(18.dp))

            WalletMainButton()

            Spacer(modifier = Modifier.height(36.dp))

            TicketTabs()

            Spacer(modifier = Modifier.height(128.dp))

            EmptyTicketState()

            Spacer(modifier = Modifier.height(64.dp))

            CheckInButton()
        }
    }
}

@Composable
private fun SmartAirTopBar(
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
            text = "스마트항공권",
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
private fun TicketTabs() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TabLabel(text = "탑승항공권", selected = true)
        TabLabel(text = "예정항공권", selected = false)
        TabLabel(text = "지난항공권", selected = false)
    }
}

@Composable
private fun TabLabel(
    text: String,
    selected: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = if (selected) Color(0xFF20242A) else Color(0xFF4D5157)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .size(width = 116.dp, height = 3.dp)
                .background(if (selected) Color(0xFF20242A) else Color.Transparent)
        )
    }
}

@Composable
private fun EmptyTicketState() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(width = 188.dp, height = 150.dp)
                .clip(RoundedCornerShape(80.dp))
                .background(Color(0xFFE5F1FB)),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                Icon(
                    modifier = Modifier.size(78.dp),
                    imageVector = Icons.Outlined.AirplanemodeActive,
                    contentDescription = null,
                    tint = Color(0xFF20242A)
                )

                Icon(
                    modifier = Modifier.size(54.dp),
                    imageVector = Icons.Outlined.Luggage,
                    contentDescription = null,
                    tint = Color(0xFFB58A1B)
                )
            }
        }

        Spacer(modifier = Modifier.height(58.dp))

        Text(
            text = "체크인 완료된 항공권이",
            fontSize = 16.sp,
            color = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "출발 24시간 전부터 조회됩니다",
            fontSize = 16.sp,
            color = Color(0xFF20242A)
        )
    }
}

@Composable
private fun CheckInButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFF202436)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "체크인 하러가기",
            fontSize = 15.sp,
            color = Color.White
        )
    }
}
