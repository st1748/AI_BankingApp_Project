package com.example.bankintentdemo.ui.screens.transfer

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.Contacts
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
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
fun TransferScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TransferTopBar(
            title = "이체",
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

        Column(modifier = Modifier.fillMaxSize()) {
            TransferInputSection()
            Spacer(modifier = Modifier.weight(1f))
            RecentTransferPanel()
        }
    }
}

@Composable
private fun TransferTopBar(
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
private fun TransferInputSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 54.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = "누구에게 보낼까요?",
                fontSize = 20.sp,
                color = Color(0xFF20242A)
            )
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color.White)
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                text = "여러 건 이체",
                fontSize = 14.sp,
                color = Color(0xFF30343A)
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "계좌번호",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF9AA2AA)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color(0xFFB7A27C))
        )
        Spacer(modifier = Modifier.height(26.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Outlined.CameraAlt,
                contentDescription = null,
                tint = Color(0xFF30343A)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "촬영이체", fontSize = 14.sp, color = Color(0xFF30343A))
            Spacer(modifier = Modifier.width(18.dp))
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(24.dp)
                    .background(Color(0xFFDADDE1))
            )
            Spacer(modifier = Modifier.width(18.dp))
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Outlined.Contacts,
                contentDescription = null,
                tint = Color(0xFF30343A)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "연락처이체", fontSize = 14.sp, color = Color(0xFF30343A))
        }
    }
}

@Composable
private fun RecentTransferPanel() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(86.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            TransferTab("최근", true, Modifier.weight(0.8f))
            TransferTab("자주쓰는", false, Modifier.weight(1.1f))
            TransferTab("빠른", false, Modifier.weight(0.9f))
            TransferTab("내계좌", false, Modifier.weight(1.1f))
            Box(
                modifier = Modifier
                    .weight(0.8f)
                    .height(70.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(38.dp),
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = Color(0xFF30343A)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFE1E4E8))
        )
        EmptyTransferState(text = "최근 이체내역이 없습니다.")
    }
}

@Composable
private fun TransferTab(text: String, selected: Boolean, modifier: Modifier) {
    Column(
        modifier = modifier.height(70.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = text,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = if (selected) Color(0xFF20242A) else Color(0xFF7B8288)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.72f)
                .height(4.dp)
                .background(if (selected) Color(0xFF20242A) else Color.Transparent)
        )
    }
}

@Composable
private fun EmptyTransferState(text: String, subText: String? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp),
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
        Text(text = text, fontSize = 17.sp, color = Color(0xFF30343A))
        if (subText != null) {
            Spacer(modifier = Modifier.height(18.dp))
            Text(text = subText, fontSize = 15.sp, color = Color(0xFF7B8288))
        }
    }
}
