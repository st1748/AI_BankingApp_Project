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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
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
fun TransferManagementScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TransferManagementTopBar(
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
                .background(Color(0xFFF2F3F5))
        ) {
            TransferLimitSection()
            SectionSpacer()

            TransferAccountSection()
            SectionSpacer()

            TransferSettingSection()
            SectionSpacer()

            TransferRestrictionSection()
            SectionSpacer()

            BalanceGatheringSection()
            Spacer(modifier = Modifier.height(26.dp))
        }
    }
}

@Composable
private fun TransferManagementTopBar(
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
            text = "이체관리",
            fontSize = 21.sp,
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
private fun TransferLimitSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF2F3F5))
            .padding(horizontal = 22.dp, vertical = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "나의 이체한도",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "상세조회/변경",
                    fontSize = 14.sp,
                    color = Color(0xFF4F555C)
                )
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = Color(0xFF4F555C)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            LimitRow(label = "1일", amount = "50,000,000원")
            Spacer(modifier = Modifier.height(16.dp))
            LimitRow(label = "1회", amount = "20,000,000원")
        }
    }
}

@Composable
private fun LimitRow(label: String, amount: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = label,
            fontSize = 16.sp,
            color = Color(0xFF4F555C)
        )
        Text(
            text = amount,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF20242A)
        )
    }
}

@Composable
private fun TransferAccountSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 22.dp, vertical = 22.dp)
    ) {
        SectionTitle("이체용 출금계좌 관리")
        SettingDivider()
        SettingRow(title = "출금계좌 등록/관리")
        SettingDivider()
        SettingRow(
            title = "출금계좌 등록방법 변경",
            rightText = "지점",
            rightTextColor = Color(0xFF2F80ED)
        )
        SettingDivider()
        SettingRow(title = "출금계좌 순서 변경")
    }
}

@Composable
private fun TransferSettingSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 22.dp, vertical = 22.dp)
    ) {
        SectionTitle("이체 설정")
        SettingDivider()
        SettingRow(title = "자주쓰는계좌 등록/관리", rightText = "미등록")
        SettingDivider()
        SettingRow(
            title = "빠른이체",
            subtitle = "비밀번호, 보안매체, 전자서명 없이 이체 가능",
            rightText = "미등록"
        )
        SettingDivider()
        SettingRow(
            title = "단축이체",
            subtitle = "계좌번호, 금액, 통장표시 미리 등록",
            rightText = "미등록"
        )
        SettingDivider()
        SettingRow(
            title = "오픈뱅킹 설정",
            subtitle = "오픈뱅킹 자산 등록/해제 및 계좌 관리",
            showStatusArrowOnly = true
        )
    }
}

@Composable
private fun TransferRestrictionSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 22.dp, vertical = 22.dp)
    ) {
        SectionTitle("이체 제한/오류 해제")
        SettingDivider()
        SettingRow(title = "장기미사용 이체 제한 해제")
        SettingDivider()
        SettingRow(title = "전화승인비밀번호 오류 해제")
    }
}

@Composable
private fun BalanceGatheringSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 22.dp, vertical = 22.dp)
    ) {
        SectionTitle("잔액모으기")
        SettingDivider()
        SettingRow(title = "잔액모으기 예약")
        SettingDivider()
        SettingRow(title = "잔액모으기 예약관리")

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF3F4F6))
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "계좌관리",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF20242A)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "계좌 비밀번호 변경 및 계좌 숨기기 설정",
                    fontSize = 13.sp,
                    color = Color(0xFF646B72)
                )
            }
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF8D949C)
            )
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = Color(0xFF30343A)
    )
}

@Composable
private fun SettingRow(
    title: String,
    subtitle: String? = null,
    rightText: String? = null,
    rightTextColor: Color = Color(0xFF8D949C),
    showStatusArrowOnly: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            if (subtitle != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    color = Color(0xFF646B72)
                )
            }
        }

        if (rightText != null) {
            Text(
                text = rightText,
                fontSize = 16.sp,
                color = rightTextColor
            )
            Spacer(modifier = Modifier.width(2.dp))
        }

        if (rightText != null || showStatusArrowOnly) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = if (rightText != null) rightTextColor else Color(0xFF8D949C)
            )
        } else {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF8D949C)
            )
        }
    }
}

@Composable
private fun SettingDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE7E9EC))
    )
}

@Composable
private fun SectionSpacer() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(Color(0xFFE2E4E8))
    )
}
