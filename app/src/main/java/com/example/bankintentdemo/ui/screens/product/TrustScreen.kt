package com.example.bankintentdemo.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankintentdemo.ui.screens.product.components.*

@Composable
fun TrustScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        TopBannerAdCard(
            title = "신탁의 개념부터\n상품 유형과\n자산 운용 방법까지",
            subtitle = "알기 쉬운 신탁 가이드",
            backgroundColor = Color(0xFFEBF1F8),
            illustratorBoxColor = Color(0xFF8DA8D3)
        )

        Spacer(modifier = Modifier.height(24.dp))
        JoinManageToggle()
        Spacer(modifier = Modifier.height(16.dp))
        SearchBarBox("나의 투자성향 분석하기")

        Spacer(modifier = Modifier.height(32.dp))
        Text("신탁 가입하기", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        Spacer(modifier = Modifier.height(16.dp))

        // 신탁 전용 리스트
        TrustListItem("ETF", "주식처럼 자유롭게 매매하고 펀드처럼 분산 투자할 수 있어요.", Color(0xFF3D8BFF))
        TrustListItem("금현물", "한국거래소(KRX)가 운영하는 금현물시장을 통해 주식처럼 매매할 수 있어요.", Color(0xFFFFCC00))
        TrustListItem("ELS", "주가지수 움직임에 연계하여 사전에 정해진 조건에 따라 투자수익을 기대할 수 있어요.", Color(0xFF8DA8D3), isHighRisk = true)
        TrustListItem("유언대용신탁", "생전에는 원하는 대로, 유고 시에는 지정한 수익자에게 승계할 수 있어요.", Color(0xFF8A9199))

        Spacer(modifier = Modifier.height(32.dp))
        IconActionCard("세제혜택 관련 유의사항 안내", "국내시장복귀계좌(RIA)", Color(0xFFE55D5D))

        Spacer(modifier = Modifier.height(40.dp))
    }
}

// 신탁 전용 리스트 컴포넌트
@Composable
private fun TrustListItem(title: String, desc: String, iconColor: Color, isHighRisk: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier.size(40.dp).clip(CircleShape).background(iconColor.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.size(20.dp).clip(CircleShape).background(iconColor))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                if (isHighRisk) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(Color(0xFFE55D5D)).padding(horizontal = 6.dp, vertical = 2.dp)) {
                        Text("고난도 금융투자상품", fontSize = 10.sp, color = Color.White)
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(desc, fontSize = 13.sp, color = Color(0xFF6B7279), lineHeight = 18.sp)
        }
    }
}