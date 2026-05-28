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
fun SubscriptionBondScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        TopBannerAdCard(
            title = "아이의 미래 보금자리\n지금부터 준비해요",
            subtitle = "미래를 위한 든든한 선택\n우리 아이 청약통장 만들기",
            backgroundColor = Color(0xFFE8F3EB),
            illustratorBoxColor = Color(0xFF8CD2A7)
        )

        Spacer(modifier = Modifier.height(24.dp))
        JoinManageToggle()
        Spacer(modifier = Modifier.height(24.dp))

        Text("청약저축 2", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("주택청약종합저축", "내 집 마련의 시작", "", "")
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("청년 주택드림 청약통장", "가입자격 및 무주택 조건 등을 충족하는 경우 최대 4.5%의 이율 및 비과세 혜택 추가", "", "")

        Spacer(modifier = Modifier.height(24.dp))
        IconActionCard("국민주택채권", "등기, 허가 등 사유 발생 시 의무적으로 매입해야 하는 국채", Color(0xFF2EBD59))

        Spacer(modifier = Modifier.height(40.dp))
    }
}