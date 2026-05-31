package com.example.bankintentdemo.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankintentdemo.ui.screens.product.components.*

@Composable
fun CheckingAccountScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // 1. 공통 배너 조립
        TopBannerAdCard(
            title = "KB모임통장 서비스",
            subtitle = "모일 땐 즐거움만,\n회비관리는\nKB모임통장 서비스로~",
            backgroundColor = Color(0xFFFFF6D7), // 연한 노란색 배경
            illustratorBoxColor = Color(0xFFFFCC00) // 진한 노란색
        )

        Spacer(modifier = Modifier.height(24.dp))
        JoinManageToggle()
        SubCategoryChips(listOf("전체", "입출금", "증권계좌"))

        // 2. 입출금 리스트
        Text("입출금 10", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("KB GS Pay통장", "GS25와의 만남으로 더 특별해진 혜택", "", "연 0.10~2.00%")
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("KB모임금고", "모임 여유자금을 연 2.0%(최대 1천만원)로", "", "연 0.10~2.00%")
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("KB스타통장", "Digital KB의 대표 통장", "", "")

        Spacer(modifier = Modifier.height(24.dp))

        // 3. 증권계좌 리스트
        Text("증권계좌 3", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("KB able Plus 통장", "은행과 증권거래를 한번에!", "", "내 통장 전환하기 >", Color(0xFF6B7279))

        Spacer(modifier = Modifier.height(40.dp))
    }
}