package com.example.bankintentdemo.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankintentdemo.ui.screens.product.components.*

@Composable
fun InsuranceScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // 1. 공통 배너 (보라색 톤)
        TopBannerAdCard(
            title = "우리 모두의\n걱정이 된 간병",
            subtitle = "내 상황에 꼭 맞는\n간병보험 알아보기",
            backgroundColor = Color(0xFFEBEBF5),
            illustratorBoxColor = Color(0xFF9EA3D5)
        )

        Spacer(modifier = Modifier.height(24.dp))
        JoinManageToggle()
        Spacer(modifier = Modifier.height(24.dp))

        Text("절세와 투자", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        SubCategoryChips(listOf("연금", "연금저축", "변액", "외화", "저축"))

        // 2. 보험 상품 리스트
        ProductListItem(
            title = "10년 확정금리 적용으로 노후를 든든하게!!",
            desc = "(무)보너스주는하이브리드연금보험(적립식)(모바일)",
            extraInfo = "ABL생명",
            rate = "",
            rateColor = Color.Transparent
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem(
            title = "장기유지 고객에 대한 높은 수준의 환급률",
            desc = "(무)KB세번의약속e연금보험",
            extraInfo = "KB라이프생명 | 보험차익 비과세",
            rate = "",
            rateColor = Color.Transparent
        )

        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("더보기 >", fontSize = 14.sp, color = Color(0xFF8A9199))
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}