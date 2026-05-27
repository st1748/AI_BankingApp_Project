package com.example.bankintentdemo.ui.screens.wallet

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
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalDining
import androidx.compose.material.icons.outlined.LocalMall
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Percent
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.Storefront
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun PaymentScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F5F7))
    ) {
        WalletTopBar(
            title = "스타뱅킹 결제",
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
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            PaymentCard()
            MerchantCard()
            HistoryCard()
        }
    }
}

@Composable
private fun WalletTopBar(
    title: String,
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
            text = title,
            fontSize = 23.sp,
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
private fun PaymentCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            PaymentSegment()
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = null,
                tint = Color(0xFF4D555E)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(146.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF1F3F5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(70.dp),
                imageVector = Icons.Outlined.AccountBalanceWallet,
                contentDescription = null,
                tint = Color(0xFFB5BDC5)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))
        DividerLine()
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier
                .border(1.dp, Color(0xFF5F666D), RoundedCornerShape(2.dp))
                .padding(horizontal = 42.dp, vertical = 14.dp),
            text = "QR 코드 스캔",
            fontSize = 19.sp,
            color = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.height(34.dp))

        AccountBox()

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "스타포인트",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Box(
                modifier = Modifier
                    .size(width = 50.dp, height = 40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFE1E4E8))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(width = 150.dp, height = 40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFE1E4E8))
            )
        }
    }
}

@Composable
private fun PaymentSegment() {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFFE0E3E6))
            .padding(2.dp)
    ) {
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFF7F8F9))
                .padding(horizontal = 32.dp, vertical = 10.dp),
            text = "QR결제",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Text(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 10.dp),
            text = "제로페이",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF727981)
        )
    }
}

@Composable
private fun AccountBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 42.dp)
            .clip(RoundedCornerShape(14.dp))
            .border(1.dp, Color(0xFFC5CBD1), RoundedCornerShape(14.dp))
            .padding(horizontal = 20.dp, vertical = 26.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = "출금계좌",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Outlined.Percent,
                contentDescription = null,
                tint = Color(0xFFC08418)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "쿠폰함", fontSize = 16.sp, color = Color(0xFF20242A))
        }

        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFE7EAED))
        )
        Spacer(modifier = Modifier.height(22.dp))
        DividerLine()
        Spacer(modifier = Modifier.height(22.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(width = 88.dp, height = 36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFE7EAED))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                modifier = Modifier
                    .border(1.dp, Color(0xFFC5CBD1), RoundedCornerShape(6.dp))
                    .padding(horizontal = 18.dp, vertical = 8.dp),
                text = "보기",
                fontSize = 14.sp,
                color = Color(0xFF5F666D)
            )
        }
    }
}

@Composable
private fun MerchantCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Outlined.Storefront,
                contentDescription = null,
                tint = Color(0xFF2F80ED)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = "결제 가능 가맹점",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Text(text = "더보기", fontSize = 15.sp, color = Color(0xFF5F666D))
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF5F666D)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            MerchantIcon(Icons.Outlined.ShoppingBasket)
            MerchantIcon(Icons.Outlined.LocalMall)
            MerchantIcon(Icons.Outlined.Coffee)
            MerchantIcon(Icons.Outlined.LocalDining)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "모든 편의점 등 전국 가맹점에서\n결제 가능합니다.",
            fontSize = 17.sp,
            lineHeight = 27.sp,
            color = Color(0xFF30343A),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun MerchantIcon(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(Color(0xFFE7EAED)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(34.dp),
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF7A828A)
        )
    }
}

@Composable
private fun HistoryCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = Icons.Outlined.QrCodeScanner,
            contentDescription = null,
            tint = Color(0xFF168CA8)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = "결제 내역",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Text(text = "전체보기", fontSize = 15.sp, color = Color(0xFF5F666D))
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF5F666D)
        )
    }
}

@Composable
private fun DividerLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE1E4E8))
    )
}
