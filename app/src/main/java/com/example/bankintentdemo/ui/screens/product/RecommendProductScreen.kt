package com.example.bankintentdemo.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecommendProductScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // 1. 인기 상품 섹션
        Text("인기 상품", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        PopularProductItem(iconColor = Color(0xFFFFCC00), title = "KB스타통장", desc = "Digital KB의 대표 통장")
        PopularProductItem(iconColor = Color(0xFF3D8BFF), title = "KB내맘대로적금", desc = "36개월 기준, 연 2.95~3.55%")
        PopularProductItem(iconColor = Color(0xFF2EBD59), title = "KB Star 정기예금", desc = "1~36개월, 연 2.40~2.90%")

        Spacer(modifier = Modifier.height(32.dp))

        // 2. 맞춤 추천 카드
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF5A81CE), Color(0xFF4265AA))
                    )
                )
                .padding(24.dp)
        ) {
            Column {
                Text("하정빈님을 위한 추천", color = Color.White.copy(alpha = 0.8f), fontSize = 13.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Text("#달러투자 #환테크", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                Text("최근 인기 외화 상품", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("더 많은 맞춤추천 보기 >", color = Color.White.copy(alpha = 0.8f), fontSize = 13.sp)

                Spacer(modifier = Modifier.height(24.dp))
                // 하단 화이트 스와이프 카드
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .padding(vertical = 24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("외화 여유자금 굴리고 불리고", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("외화정기예금", fontSize = 13.sp, color = Color(0xFF8A9199))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // 3. 검색 키워드 섹션
        Text("검색 키워드", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            KeywordChip(text = "직접검색", icon = true, isBordered = true)
            KeywordChip(text = "청년")
            KeywordChip(text = "직장인")
            KeywordChip(text = "세테크")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            KeywordChip(text = "\uD83D\uDC76 우리아이 첫 통장 선물하기", textColor = Color(0xFF3D8BFF), bgColor = Color(0xFFF3F7FF))
            KeywordChip(text = "사장님", textColor = Color(0xFF3D8BFF), bgColor = Color(0xFFF3F7FF))
            KeywordChip(text = "노란우산", textColor = Color(0xFF3D8BFF), bgColor = Color(0xFFF3F7FF))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 4. 대출 배너 (회색 박스)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF7F8FA))
                .padding(24.dp)
        ) {
            Text(
                text = "KB로 대출 갈아타고\n이자부담 낮춰보세요",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // 5. 상품 바구니
        Text("상품 바구니", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFEBECEF), RoundedCornerShape(12.dp))
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BasketItem(count = "0", title = "가입 이어하기")
            BasketItem(count = "0", title = "찜한 상품", isGray = true)
            BasketItem(count = "0", title = "최근 본 상품", isGray = true)
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

// 내부 재사용 미니 컴포넌트들
@Composable
fun PopularProductItem(iconColor: Color, title: String, desc: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(12.dp)).background(Color(0xFFF7F8FA)), contentAlignment = Alignment.Center) {
            Box(modifier = Modifier.size(20.dp).clip(RoundedCornerShape(4.dp)).background(iconColor))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
            Spacer(modifier = Modifier.height(2.dp))
            Text(desc, fontSize = 13.sp, color = if (desc.contains("연")) Color(0xFF3D8BFF) else Color(0xFF8A9199))
        }
    }
}

@Composable
fun KeywordChip(text: String, icon: Boolean = false, isBordered: Boolean = false, textColor: Color = Color(0xFF4A5056), bgColor: Color = Color(0xFFF7F8FA)) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isBordered) Color.White else bgColor)
            .border(if (isBordered) 1.dp else 0.dp, if (isBordered) Color(0xFFEBECEF) else Color.Transparent, RoundedCornerShape(20.dp))
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon) {
            Icon(Icons.Outlined.Search, contentDescription = null, modifier = Modifier.size(16.dp), tint = textColor)
            Spacer(modifier = Modifier.width(4.dp))
        }
        Text(text, fontSize = 14.sp, color = textColor)
    }
}

@Composable
fun BasketItem(count: String, title: String, isGray: Boolean = false) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(48.dp).clip(CircleShape).background(if (isGray) Color(0xFFF7F8FA) else Color(0xFFF3F7FF)), contentAlignment = Alignment.Center) {
            Text(count, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = if (isGray) Color(0xFF20242A) else Color(0xFF3D8BFF))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(title, fontSize = 13.sp, color = Color(0xFF6B7279))
    }
}