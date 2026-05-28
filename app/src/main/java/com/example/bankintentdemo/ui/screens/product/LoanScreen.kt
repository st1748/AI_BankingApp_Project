package com.example.bankintentdemo.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bankintentdemo.ui.screens.product.components.*

@Composable
fun LoanScreen() {
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
            title = "금리인하요구권\n대행 서비스",
            subtitle = "금융사 상관 없이 내 대출의\n금리 인하를 신청할 수 있어요",
            backgroundColor = Color(0xFFF1E9F6), // 연한 보라색 배경
            illustratorBoxColor = Color(0xFF7E84D3) // 보라색
        )

        Spacer(modifier = Modifier.height(24.dp))
        JoinManageToggle()
        SubCategoryChips(listOf("신용", "전월세", "부동산", "예금담보", "자동차"))

        // 2. 대출 리스트
        ProductListItem("하정빈 님을 위한 맞춤 신용대출", "신청하기 >", "", "", Color.Transparent)
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("KB스타 신용대출(신규)", "대출한도 3.5억원", "금리 ", "4.03% ~ 4.56%")
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("KB 비상금 대출", "대출한도 300만원", "금리 ", "5.46% ~ 5.86%")

        Spacer(modifier = Modifier.height(40.dp))
    }
}