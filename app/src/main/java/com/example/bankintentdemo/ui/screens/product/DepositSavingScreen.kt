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
fun DepositSavingScreen() {
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
            title = "아이를 위한 목돈\n오늘부터 준비해요",
            subtitle = "아이와 함께 키워가는\n우리 아이 적금 만들기",
            backgroundColor = Color(0xFFE8F2E3), // 연한 연두색 배경
            illustratorBoxColor = Color(0xFF4CB173) // 녹색
        )

        Spacer(modifier = Modifier.height(24.dp))
        JoinManageToggle() // 가입/관리 토글
        SubCategoryChips(listOf("전체", "적금", "정기예금", "지수연동"))

        // 2. 적금 리스트
        Text("적금 17", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("KB장병내일준비적금", "국군장병 미래준비 맞춤적금", "24개월 기준, ", "연 5.00~10.50%")
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("KB아이사랑적금", "아이 키우는 가정을 응원합니다.", "12개월 기준, ", "연 2.00~10.00%")
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("KB달리자적금", "달린 거리만큼 올라가는 우대이율", "6개월 기준, ", "연 1.00~6.00%")

        Spacer(modifier = Modifier.height(24.dp))

        // 3. 정기예금 리스트
        Text("정기예금 1", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("KB Star 정기예금", "Digital KB의 대표 정기예금", "1~36개월, ", "연 2.40~2.90%")

        Spacer(modifier = Modifier.height(40.dp))
    }
}