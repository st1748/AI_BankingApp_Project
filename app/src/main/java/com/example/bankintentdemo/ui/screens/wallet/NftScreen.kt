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
import androidx.compose.material.icons.outlined.Celebration
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.WorkspacePremium
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
fun NftScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        NftTopBar(
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
            NftCollectionSection()
            NftEventSection()
        }
    }
}

@Composable
private fun NftTopBar(
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
            text = "NFT 지갑",
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
private fun NftCollectionSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFEAF6FF), Color(0xFFF2E9FF))
                )
            )
            .padding(horizontal = 28.dp, vertical = 28.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFC5CDD4), RoundedCornerShape(28.dp))
                    .padding(horizontal = 22.dp, vertical = 11.dp),
                text = "국민지갑 메인",
                fontSize = 16.sp,
                color = Color(0xFF30343A)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "나만의 NFT 컬렉션",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.height(22.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFFE2E9F8))
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                text = "물색설경운치4818 〉",
                fontSize = 19.sp,
                color = Color(0xFF30343A)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "전체 보기 ", fontSize = 17.sp, color = Color(0xFF555C63))
            Text(text = "0", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2F80ED))
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF555C63)
            )
        }

        Spacer(modifier = Modifier.height(150.dp))

        EmptyNftState()

        Spacer(modifier = Modifier.height(76.dp))

        NftGuideBanner()

        Spacer(modifier = Modifier.height(38.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "거래 내역",
            fontSize = 21.sp,
            color = Color(0xFF555C63),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(58.dp))
    }
}

@Composable
private fun EmptyNftState() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(78.dp),
            imageVector = Icons.Outlined.WorkspacePremium,
            contentDescription = null,
            tint = Color(0xFFD8C8F5)
        )
        Spacer(modifier = Modifier.height(52.dp))
        Text(
            text = "보유한 NFT가 없어요.",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun NftGuideBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFD9C6FF))
            .padding(horizontal = 24.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "NFT는 어떻게 모으나요?",
                fontSize = 16.sp,
                color = Color(0xFF20242A)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "NFT 소장 방법 알아보기",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
        }
        NftBadge()
    }
}

@Composable
private fun NftBadge() {
    Box(
        modifier = Modifier.size(82.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(3.dp, Color(0xFF20242A), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "NFT",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7A56D6)
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(32.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFE16A))
                .border(3.dp, Color(0xFF20242A), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "✓", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        }
    }
}

@Composable
private fun NftEventSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 28.dp, vertical = 52.dp)
    ) {
        Text(
            text = "NFT 지갑 이벤트 안내",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.height(52.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(34.dp),
                imageVector = Icons.Outlined.Celebration,
                contentDescription = null,
                tint = Color(0xFFFFB800)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = "안녕 나는 웰컴이야~!!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF20242A)
            )
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}
