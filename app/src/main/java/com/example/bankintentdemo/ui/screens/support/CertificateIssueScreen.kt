package com.example.bankintentdemo.ui.screens.support

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
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
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
fun CertificateIssueScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CertificateTopBar(
            onBackClick = { navController.popBackStack() },
            onHomeClick = {
                navController.navigate(
                    if (isAiMode) AppRoute.AIHome.route else AppRoute.NormalHome.route
                ) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = false
                    }
                    launchSingleTop = true
                }
            },
            onMenuClick = { navController.navigate(AppRoute.MainMenu.route) }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            PopularCertificateSection()
            IssueListSection()
            SubmitSectionLabel()
        }
    }
}

@Composable
private fun CertificateTopBar(
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
            text = "증명서 발급/제출",
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
private fun PopularCertificateSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F6F6))
            .padding(horizontal = 28.dp, vertical = 34.dp)
    ) {
        Text(
            text = "가장 많이 찾는\n금융거래 관련 증명서에요",
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.height(26.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, Color(0xFFE1E4E8), RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 18.dp)
        ) {
            PopularCertificateRow("예금잔액증명서")
            PopularCertificateRow("계좌개설확인서(통장사본)")
            PopularCertificateRow("연말정산증명서")
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF9BA3AA)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = "최근 3개월 기준, 최대 5건까지만 보여드려요.",
                fontSize = 15.sp,
                color = Color(0xFF7B8288)
            )
        }
    }
}

@Composable
private fun PopularCertificateRow(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            fontSize = 19.sp,
            color = Color(0xFF4A4F55)
        )

        Text(
            text = "발급",
            fontSize = 17.sp,
            color = Color(0xFF4A4F55)
        )

        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF4A4F55)
        )
    }
}

@Composable
private fun IssueListSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 28.dp, vertical = 34.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = "증명서 발급",
                fontSize = 20.sp,
                color = Color(0xFF20242A)
            )

            Text(
                text = "발급/배송내역 조회",
                fontSize = 17.sp,
                color = Color(0xFF7B8288)
            )

            Icon(
                modifier = Modifier.size(26.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF20242A)
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        DividerLine()
        CertificateCategoryRow(
            title = "금융거래 관련 증명서",
            description = "예금잔액증명서, 계좌개설확인서, 통장사본 등"
        )
        DividerLine()
        CertificateCategoryRow(
            title = "정부24 전자증명서",
            description = "주민등록표등/초본, 소득금액증명 등"
        )
        DividerLine()
        CertificateCategoryRow(
            title = "금융거래종합보고서",
            description = "전자금융 이용현황, 여신/수신거래현황 등"
        )
        DividerLine()
    }
}

@Composable
private fun CertificateCategoryRow(
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                fontSize = 15.sp,
                color = Color(0xFF5E646B)
            )
        }

        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF8F989F)
        )
    }
}

@Composable
private fun DividerLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE7EAED))
    )
}

@Composable
private fun SubmitSectionLabel() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF3F5F7))
            .padding(horizontal = 28.dp, vertical = 34.dp)
    ) {
        Text(
            text = "증명서 제출",
            fontSize = 20.sp,
            color = Color(0xFF20242A)
        )
    }
}
