package com.example.bankintentdemo.ui.screens.transfer

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Home
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
fun AutomaticTransferScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AutoTransferTopBar(
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

        Column(modifier = Modifier.weight(1f)) {
            AutoTransferHeader()
            AutoTransferEmptyState(modifier = Modifier.weight(1f))
            ProcessHistoryHeader()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .background(Color(0xFFFFD23F)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "등록하기",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
        }
    }
}

@Composable
private fun AutoTransferTopBar(
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
            text = "자동이체",
            fontSize = 20.sp,
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
private fun AutoTransferHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 46.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(28.dp))
                    .border(1.dp, Color(0xFF9AA2AA), RoundedCornerShape(28.dp))
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                text = "지난달",
                fontSize = 13.sp,
                color = Color(0xFF8B939B)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color(0xFF4D535A))
                    .padding(horizontal = 28.dp, vertical = 12.dp),
                text = "이번달",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "자동이체 관리",
                fontSize = 15.sp,
                color = Color(0xFF6C737A)
            )
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF6C737A)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Box(
            modifier = Modifier
                .align(Alignment.End)
                .clip(RoundedCornerShape(28.dp))
                .border(1.dp, Color(0xFFD0D5DA), RoundedCornerShape(28.dp))
                .padding(horizontal = 22.dp, vertical = 10.dp)
        ) {
            Text(
                text = "등록 조회·변경·해지 가능!",
                fontSize = 13.sp,
                color = Color(0xFF2F80ED)
            )
        }
    }
}

@Composable
private fun AutoTransferEmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape)
                .background(Color(0xFFC5CBD1)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.Outlined.Error,
                contentDescription = null,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "예정된 자동이체 내역이 없어요",
            fontSize = 17.sp,
            color = Color(0xFF30343A)
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = "자동이체를 등록해보세요.",
            fontSize = 15.sp,
            color = Color(0xFF7B8288)
        )
    }
}

@Composable
private fun ProcessHistoryHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(76.dp)
            .background(Color(0xFFF5F6F7))
            .padding(horizontal = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "자동이체 처리 내역",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Outlined.ExpandMore,
            contentDescription = null,
            tint = Color(0xFF30343A)
        )
    }
}
