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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.ConfirmationNumber
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
fun CouponBoxScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CouponTopBar(
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
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF8F2EA),
                            Color(0xFFFFECEC),
                            Color(0xFFFFF9EA),
                            Color.White
                        )
                    )
                )
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            WalletMainButton()

            Spacer(modifier = Modifier.height(64.dp))

            CouponCountRow()

            Spacer(modifier = Modifier.height(84.dp))

            EmptyCouponState()

            Spacer(modifier = Modifier.height(92.dp))

            AddCouponButton()

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun CouponTopBar(
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
            text = "쿠폰함",
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
private fun WalletMainButton() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(28.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFC5CDD4), RoundedCornerShape(28.dp))
                .padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "국민지갑 메인",
                fontSize = 20.sp,
                color = Color(0xFF30343A)
            )
        }
    }
}

@Composable
private fun CouponCountRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "사용 가능한 쿠폰",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )

        Text(
            text = "전체 ",
            fontSize = 19.sp,
            color = Color(0xFF20242A)
        )

        Text(
            text = "0",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3D8BFF)
        )

        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF20242A)
        )
    }
}

@Composable
private fun EmptyCouponState() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(width = 96.dp, height = 58.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFEFD1CF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(42.dp),
                imageVector = Icons.Outlined.ConfirmationNumber,
                contentDescription = null,
                tint = Color(0xFFD0AAA6)
            )
        }

        Spacer(modifier = Modifier.height(42.dp))

        Text(
            text = "보유하신 쿠폰이 없어요",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "나만의 쿠폰을 채워보세요!",
            fontSize = 22.sp,
            color = Color(0xFF20242A)
        )
    }
}

@Composable
private fun AddCouponButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(34.dp),
            imageVector = Icons.Outlined.ConfirmationNumber,
            contentDescription = null,
            tint = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.size(14.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = "내 쿠폰 추가하기",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )

        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF9AA2AA)
        )
    }
}
