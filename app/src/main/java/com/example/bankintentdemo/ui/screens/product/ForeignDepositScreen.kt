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
fun ForeignDepositScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        // 상단 실시간 환율 (가상 배너)
        TopBannerAdCard("실시간 환율", "미국 USD 1,500.80\n일본 JPY 941.83", Color(0xFFF7F8FA), Color(0xFF20242A))

        Spacer(modifier = Modifier.height(24.dp))
        JoinManageToggle()
        SubCategoryChips(listOf("전체", "외화입출금", "외화예금", "외화적금"))

        Text("외화입출금 3", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("바로보는 외화통장", "환테크 수익률을 바로 볼 수 있는", "가입금액 ", "제한없음", Color(0xFF20242A))
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem("KB글로벌 외화투자통장", "환테크부터 KB증권 해외주식 거래까지", "가입금액 ", "제한없음", Color(0xFF20242A))

        Spacer(modifier = Modifier.height(32.dp))

        Text("외화서비스", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        Spacer(modifier = Modifier.height(12.dp))
        IconActionCard("환전", "환전도 역시 KB", Color(0xFFFFCC00))
        Spacer(modifier = Modifier.height(12.dp))
        IconActionCard("해외송금", "전 세계 어디든 저렴하게 송금하세요", Color(0xFFE55D5D))
        Spacer(modifier = Modifier.height(12.dp))
        IconActionCard("국내 외화이체", "내 계좌에 있는 외화를 다른 계좌로 이체할 수 있어요", Color(0xFFD362B4))

        Spacer(modifier = Modifier.height(40.dp))
    }
}