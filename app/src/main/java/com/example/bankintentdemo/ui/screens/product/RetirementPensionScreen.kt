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
fun RetirementPensionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F8FA)) // 전체 배경이 약간 회색톤
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.background(Color.White).padding(horizontal = 24.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            // 상단 미니 배너
            InfoActionCard(topText = "퇴직연금의 모든 것!", title = "「KB스타연금」 영상으로 쉽게 알아보세요")

            Spacer(modifier = Modifier.height(32.dp))
            Text("개인형IRP와 함께\n행복한 노후를 준비해보세요", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
            Spacer(modifier = Modifier.height(8.dp))
            Text("최대 148.5만원의 절세혜택도 받을 수 있어요", fontSize = 14.sp, color = Color(0xFF6B7279))

            Spacer(modifier = Modifier.height(140.dp)) // 일러스트 영역 여백

            ProductListItem("개인형IRP 가입하기", "절세혜택 받으며 은퇴를 준비해요", "", "")
            Spacer(modifier = Modifier.height(12.dp))
            ProductListItem("가입과 가져오기를 한번에", "IRP 가입 후 다른 금융사 연금을 가져와요", "", "")
            Spacer(modifier = Modifier.height(32.dp))
        }

        // 하단 회색 영역
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp)) {
            InfoActionCard("TDF의 개념부터 함께 알아볼까요?", "알기 쉬운 TDF 가이드")
            Spacer(modifier = Modifier.height(12.dp))
            InfoActionCard("전문가 상담이 필요하다면", "퇴직연금 전용 전화상담 연결하기")
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}