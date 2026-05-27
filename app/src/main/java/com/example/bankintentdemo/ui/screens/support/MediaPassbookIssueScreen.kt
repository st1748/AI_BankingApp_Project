package com.example.bankintentdemo.ui.screens.support

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun MediaPassbookIssueScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ReissueTopBar(
            onBackClick = { navController.popBackStack() },
            onHomeClick = {
                if (isAiMode) {
                    navController.navigate(AppRoute.AIHome.route) {
                        // AIHome으로 가면서 그 사이의 백스택 밀기
                        popUpTo(AppRoute.AIHome.route) { inclusive = true }
                        launchSingleTop = true
                    }
                } else {
                    navController.navigate(AppRoute.NormalHome.route) {
                        // NormalHome으로 가면서 그 사이의 백스택을 싹 밀기
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
                .padding(horizontal = 28.dp, vertical = 26.dp)
        ) {
            ReissueMenuRow("통장/인감분실 재발급")
            ReissueMenuRow("보안카드 재발급")
            ReissueMenuRow("OTP 발급")
            ReissueMenuRow("OTP 재발급")

            Spacer(modifier = Modifier.height(42.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5), RoundedCornerShape(10.dp))
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "발급/배송내역 조회하기",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF20242A)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "등기우편부터 지점에서 받은 발급 내역까지",
                        fontSize = 15.sp,
                        color = Color(0xFF5E646B)
                    )
                }

                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = Color(0xFF8F989F)
                )
            }
        }
    }
}

@Composable
private fun ReissueTopBar(
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
            text = "통장/보안매체 재발급",
            fontSize = 19.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF20242A)
        )

        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = Icons.Outlined.Face,
            contentDescription = "Counselor",
            tint = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.size(18.dp))

        Icon(
            modifier = Modifier
                .size(34.dp)
                .clickable(onClick = onHomeClick),
            imageVector = Icons.Outlined.Home,
            contentDescription = "Home",
            tint = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.size(22.dp))

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
private fun ReissueMenuRow(title: String) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )

            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF8F989F)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFE7EAED))
        )
    }
}
