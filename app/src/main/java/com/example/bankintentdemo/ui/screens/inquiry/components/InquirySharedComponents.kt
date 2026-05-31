package com.example.bankintentdemo.ui.screens.inquiry.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute

@Composable
fun InquiryTopBar(
    title: String,
    navController: NavController,
    isAiMode: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.ChevronLeft,
            contentDescription = "뒤로가기",
            modifier = Modifier
                .size(32.dp)
                .clickable { navController.popBackStack() },
            tint = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            fontSize = 20.sp,
            color = Color(0xFF20242A),
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Outlined.SupportAgent,
            contentDescription = "챗봇",
            modifier = Modifier.size(28.dp),
            tint = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = "홈",
            modifier = Modifier
                .size(28.dp)
                .clickable {
                    // 백스택 청소
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
            tint = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = Icons.Outlined.Menu,
            contentDescription = "메뉴",
            modifier = Modifier
                .size(28.dp)
                .clickable { navController.navigate(AppRoute.MainMenu.route) },
            tint = Color(0xFF20242A)
        )
    }
}