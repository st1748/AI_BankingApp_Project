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
fun FundScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // 상단 테마 수익률 배너 (가상 대체)
        TopBannerAdCard("테마 수익률 TOP 10", "1위 통신 ▲41.38%", Color(0xFFEDF2F8), Color(0xFF3D8BFF))

        Spacer(modifier = Modifier.height(24.dp))
        JoinManageToggle()
        Spacer(modifier = Modifier.height(24.dp))

        SearchBarBox("펀드 상품을 검색해 보세요")
        Spacer(modifier = Modifier.height(16.dp))
        InfoActionCard("", "투자성향 분석하고 나에게 맞는 상품보기")

        SubCategoryChips(listOf("판매량 Best", "수익률 Best", "추천펀드", "신상품"))

        // 펀드 리스트
        FundListItem(1, "매우높은위험", "경쟁력 있는 국내 IT관련 기업에 주로 투자", "미래에셋 코어테크 증권자투자신탁(주식) C-e", "51.31%")
        Spacer(modifier = Modifier.height(12.dp))
        FundListItem(2, "낮은위험", "짧은 환매주기의 초단기 채권 투자 펀드", "KB 내일드림 초단기채 증권 투자신탁(채권) C-E", "0.70%")
        Spacer(modifier = Modifier.height(12.dp))
        FundListItem(3, "높은위험", "국내 밸류업 관련 종목에 투자!", "하나 파이팅코리아 증권투자신탁(주식) C-E", "45.19%")

        Spacer(modifier = Modifier.height(40.dp))
    }
}