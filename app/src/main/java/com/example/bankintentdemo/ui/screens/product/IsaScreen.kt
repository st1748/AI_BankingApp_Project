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
fun IsaScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        TopBannerAdCard("투자와 절세를\n한번에 챙기는 방법", "개인종합자산관리계좌(ISA)\n가이드", Color(0xFFEAF1F9), Color(0xFF6B9DE8))

        Spacer(modifier = Modifier.height(24.dp))
        JoinManageToggle()
        Spacer(modifier = Modifier.height(24.dp))

        Text("ISA 가입하기", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("일임형 ISA", "내가 선택한 포트폴리오를 투자전문가가 운용", "", "")
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("신탁형 ISA", "예금, ETF 등 다양한 상품을 내가 직접 운용", "", "")

        Spacer(modifier = Modifier.height(24.dp))
        IconActionCard("KB 일임형ISA로 이전하기", "다른 금융기관에 개설하신 ISA 또는\nKB 신탁형ISA를 KB 일임형ISA로 이전", Color(0xFF8C95CD))

        Spacer(modifier = Modifier.height(40.dp))
        InfoActionCard("전문가와 상담이 필요하다면", "지점상담 예약하기 (09:00 ~ 17:00)")
        Spacer(modifier = Modifier.height(12.dp))
        IconActionCard("세제혜택 관련 유의사항 안내", "국내시장복귀계좌(RIA)", Color(0xFFE55D5D))

        Spacer(modifier = Modifier.height(40.dp))
    }
}