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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Train
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
fun TrainTicketScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TrainTopBar(
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
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(18.dp))

            WalletRow()

            Spacer(modifier = Modifier.height(24.dp))

            NoticeBar()

            Spacer(modifier = Modifier.height(38.dp))

            HeroSection()

            Spacer(modifier = Modifier.height(34.dp))

            StationRow()

            Spacer(modifier = Modifier.height(28.dp))

            TrainInfoRow(
                label = "가는날",
                value = "2026.05.26 (화) · 21시 이후 출발",
                trailing = {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        imageVector = Icons.Outlined.CalendarMonth,
                        contentDescription = null,
                        tint = Color(0xFFA8B0B8)
                    )
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    tint = Color(0xFF8F989F)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "왕복",
                    fontSize = 14.sp,
                    color = Color(0xFF8F989F)
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            TrainInfoRow(
                label = "인원",
                value = "총 1명 (어른 1명)",
                trailing = {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null,
                        tint = Color(0xFFA8B0B8)
                    )
                }
            )

            Spacer(modifier = Modifier.height(44.dp))

            PetBanner()

            Spacer(modifier = Modifier.height(56.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE2E6EA))
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "기차 조회하기",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFADB5BE)
            )
        }
    }
}

@Composable
private fun TrainTopBar(
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
            text = "기차예매",
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
private fun WalletRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            imageVector = Icons.Outlined.Train,
            contentDescription = null,
            tint = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.size(22.dp))

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
private fun NoticeBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF3F5F7))
            .padding(horizontal = 4.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(Color(0xFFD4D9DE)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "!",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = "! 및 지연안내 서소문 고가도로 붕괴로 일부 열차 운행 변경",
            fontSize = 14.sp,
            color = Color(0xFF5F666D),
            maxLines = 1
        )

        Text(
            text = "×",
            fontSize = 18.sp,
            color = Color(0xFF9AA2AA)
        )
    }
}

@Composable
private fun HeroSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "기차예매\n국민지갑과 함께해요",
            fontSize = 22.sp,
            lineHeight = 29.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )

        Box(
            modifier = Modifier
                .size(width = 170.dp, height = 130.dp)
                .clip(RoundedCornerShape(40.dp))
                .background(Color(0xFFCDEEB2)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "KTX",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF35633C)
            )
        }
    }
}

@Composable
private fun StationRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(42.dp)
    ) {
        StationField(
            modifier = Modifier.weight(1f),
            label = "출발역",
            value = "서울역",
            active = true
        )

        StationField(
            modifier = Modifier.weight(1f),
            label = "도착역",
            value = "선택",
            active = false
        )
    }
}

@Composable
private fun StationField(
    modifier: Modifier,
    label: String,
    value: String,
    active: Boolean
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 15.sp,
            color = Color(0xFF70777E)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (active) Color(0xFF20242A) else Color(0xFF9AA2AA)
            )

            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = null,
                tint = Color(0xFFA8B0B8)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color(0xFFC8CDD2))
        )
    }
}

@Composable
private fun TrainInfoRow(
    label: String,
    value: String,
    trailing: @Composable () -> Unit
) {
    Column {
        Text(
            text = label,
            fontSize = 15.sp,
            color = Color(0xFF70777E)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = value,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )

            trailing()
        }

        Spacer(modifier = Modifier.height(14.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color(0xFFC8CDD2))
        )
    }
}

@Composable
private fun PetBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFFFF4B8))
            .padding(horizontal = 24.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "반려동물과 함께\n여행할 계획이세요?",
            fontSize = 15.sp,
            lineHeight = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )

        Box(
            modifier = Modifier
                .size(width = 118.dp, height = 78.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFBEE7C4)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "PET",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF35633C)
            )
        }
    }
}
