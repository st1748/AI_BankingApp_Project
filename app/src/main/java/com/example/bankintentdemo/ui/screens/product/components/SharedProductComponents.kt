package com.example.bankintentdemo.ui.screens.product.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. 상단 광고 배너 컴포넌트
@Composable
fun TopBannerAdCard(
    title: String,
    subtitle: String,
    backgroundColor: Color,
    illustratorBoxColor: Color, // 우측 일러스트를 대체할 임시 색상 박스
    showPagination: Boolean = true // 페이지네이션 on/off 옵션
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(24.dp)
    ) {
        // 페이지네이션 on/off 제어
        if (showPagination) {
            Row(
                modifier = Modifier.align(Alignment.TopEnd),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color.Black.copy(alpha = 0.3f)))
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color.Black.copy(alpha = 0.8f)))
                Box(modifier = Modifier.width(12.dp).height(6.dp).clip(RoundedCornerShape(3.dp)).background(Color.Black.copy(alpha = 0.3f)))
                Spacer(modifier = Modifier.width(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    Box(modifier = Modifier.width(3.dp).height(8.dp).background(Color.Black.copy(alpha = 0.5f)))
                    Box(modifier = Modifier.width(3.dp).height(8.dp).background(Color.Black.copy(alpha = 0.5f)))
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = if (showPagination) 20.dp else 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A), lineHeight = 28.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(subtitle, fontSize = 13.sp, color = Color(0xFF6B7279), lineHeight = 18.sp)
            }

            // 우측 3D 에셋을 대체할 컬러 박스
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(illustratorBoxColor)
            )
        }
    }
}

// 2. 가입 / 관리 탭 토글 컴포넌트
@Composable
fun JoinManageToggle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF7F8FA))
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(6.dp)).background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text("가입", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        }
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text("관리", fontSize = 15.sp, color = Color(0xFF6B7279))
        }
    }
}

// 3. 하트 찜이 있는 공통 상품 리스트 아이템
@Composable
fun ProductListItem(
    title: String,
    desc: String,
    extraInfo: String, // "12개월 기준, " 같은 앞부분
    rate: String,      // "연 2.00~10.00%" 같은 강조 금리
    rateColor: Color = Color(0xFF3D8BFF)
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFEBECEF), RoundedCornerShape(12.dp))
            .padding(20.dp)
    ) {
        Column {
            Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
            Spacer(modifier = Modifier.height(4.dp))
            Text(desc, fontSize = 13.sp, color = Color(0xFF6B7279))
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(extraInfo, fontSize = 13.sp, color = Color(0xFF8A9199))
                Text(rate, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = rateColor)
            }
        }

        // 우측 하트 아이콘
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "찜하기",
            modifier = Modifier.align(Alignment.TopEnd).size(24.dp),
            tint = Color(0xFF9AA2AA)
        )
    }
}

// 4. 서브 카테고리 칩 (전체, 적금, 정기예금 등)
@Composable
fun SubCategoryChips(chips: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        chips.forEachIndexed { index, text ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (index == 0) Color(0xFFFFCC00) else Color.White)
                    .border(if (index == 0) 0.dp else 1.dp, if (index == 0) Color.Transparent else Color(0xFFEBECEF), RoundedCornerShape(20.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text, fontSize = 14.sp, fontWeight = if (index == 0) FontWeight.Bold else FontWeight.Normal, color = Color(0xFF20242A))
            }
        }
    }
}

// 5. 검색 바 컴포넌트 (펀드 화면 등)
@Composable
fun SearchBarBox(placeholder: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF20242A))
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = placeholder, fontSize = 15.sp, color = Color(0xFF8A9199))
        Icon(imageVector = Icons.Outlined.Search, contentDescription = "검색", tint = Color(0xFF20242A))
    }
}

// 6. 좌측 아이콘 + 텍스트 액션 카드 (환전, 대출갈아타기, 국민주택채권 등)
@Composable
fun IconActionCard(title: String, desc: String, iconColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF7F8FA))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(40.dp).clip(CircleShape).background(iconColor.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.size(20.dp).clip(CircleShape).background(iconColor)) // 임시 아이콘
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
            Spacer(modifier = Modifier.height(4.dp))
            Text(desc, fontSize = 13.sp, color = Color(0xFF6B7279))
        }
    }
}

// 7. 우측 화살표가 있는 심플 안내 카드 (TDF 가이드, 상담 연결 등)
@Composable
fun InfoActionCard(topText: String, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFEBECEF), RoundedCornerShape(12.dp))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            if (topText.isNotEmpty()) {
                Text(topText, fontSize = 12.sp, color = Color(0xFF8A9199))
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        }
        Icon(imageVector = Icons.Outlined.ChevronRight, contentDescription = null, tint = Color(0xFF8A9199))
    }
}

// 8. 펀드 전용 리스트 아이템 (랭킹, 태그 포함)
@Composable
fun FundListItem(rank: Int, risk: String, title: String, subtitle: String, returnRate: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFEBECEF), RoundedCornerShape(12.dp))
            .padding(20.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("👑 ${rank}위", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD97757))
                Spacer(modifier = Modifier.width(8.dp))
                Text(risk, fontSize = 11.sp, color = if(risk.contains("위험")) Color(0xFFE55D5D) else Color(0xFF3D8BFF))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
            Spacer(modifier = Modifier.height(4.dp))
            Text(subtitle, fontSize = 13.sp, color = Color(0xFF6B7279))
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("3개월 수익률 ", fontSize = 13.sp, color = Color(0xFF8A9199))
                Text("▲ $returnRate", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE55D5D))
            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color(0xFFF0F2F5))
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                Text("보유종목 Top 10", fontSize = 13.sp, color = Color(0xFF6B7279))
                Icon(imageVector = Icons.Outlined.ChevronRight, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color(0xFF6B7279))
            }
        }
    }
}