
package com.example.bankintentdemo.ui.screens.support

import androidx.compose.foundation.background
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun EReceiptScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        EReceiptTopBar(
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
        ) {
            WalletButtonArea()
            ReceiptTabs()
            PointToggleArea()
            ReceiptListArea()
            NoticeArea()
        }
    }
}

@Composable
private fun EReceiptTopBar(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(76.dp)
            .background(Color.White)
            .padding(start = 24.dp, end = 24.dp, top = 12.dp),
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
            text = "전자영수증",
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
        Spacer(modifier = Modifier.size(24.dp))
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
private fun WalletButtonArea() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            modifier = Modifier
                .background(Color.White, CircleShape)
                .padding(horizontal = 22.dp, vertical = 10.dp),
            text = "국민지갑 메인",
            fontSize = 15.sp,
            color = Color(0xFF20242A)
        )
    }
}

@Composable
private fun ReceiptTabs() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 20.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        TabText("은행영수증", true)
        Spacer(modifier = Modifier.size(38.dp))
        TabText("구매영수증", false)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE1E4E8))
    )
}

@Composable
private fun TabText(text: String, selected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = if (selected) Color(0xFF20242A) else Color(0xFF8B939B)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .size(width = 82.dp, height = 4.dp)
                .background(if (selected) Color(0xFF20242A) else Color.Transparent)
        )
    }
}

@Composable
private fun PointToggleArea() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 28.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "창구 거래 전자영수증 받기",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = "1건 당 탄소중립포인트 ",
                    fontSize = 15.sp,
                    color = Color(0xFF777F87)
                )
                Text(
                    text = "10P",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2F80ED)
                )
                Text(
                    text = " 적립",
                    fontSize = 15.sp,
                    color = Color(0xFF777F87)
                )
            }
        }
        Switch(checked = false, onCheckedChange = {})
    }
}

@Composable
private fun ReceiptListArea() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 28.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(modifier = Modifier.weight(1f)) {
                Text(
                    text = "총 ",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF20242A)
                )
                Text(
                    text = "0",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2F80ED)
                )
                Text(
                    text = "개",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF20242A)
                )
            }
            Text(
                text = "1개월·전체",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Icon(
                modifier = Modifier.size(26.dp),
                imageVector = Icons.Outlined.ExpandMore,
                contentDescription = null,
                tint = Color(0xFF20242A)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFE1E4E8))
        )
        Spacer(modifier = Modifier.height(100.dp))
        EmptyReceiptState()
        Spacer(modifier = Modifier.height(88.dp))
    }
}

@Composable
private fun EmptyReceiptState() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(78.dp),
            imageVector = Icons.AutoMirrored.Outlined.ReceiptLong,
            contentDescription = null,
            tint = Color(0xFFDDE1E5)
        )
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "아직 발급된 영수증이 없어요",
            fontSize = 18.sp,
            color = Color(0xFF777F87),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "'창구 거래 거래 건 전자영수증 받기'를 켜고\n탄소중립포인트를 모아보세요",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = Color(0xFF777F87),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun NoticeArea() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 28.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(Color(0xFF4E555C), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("!", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = "알려드려요",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Outlined.ExpandLess,
                contentDescription = null,
                tint = Color(0xFF20242A)
            )
        }
        Spacer(modifier = Modifier.height(22.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFE1E4E8))
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = "· 창구영수증은 최근 1년 이내의 내역만 조회가 가능합니다.\n\n· 종이영수증이 필요하신 경우 영업점에 요청시 제공해드립니다.",
            fontSize = 16.sp,
            lineHeight = 27.sp,
            color = Color(0xFF30343A)
        )
    }
}
