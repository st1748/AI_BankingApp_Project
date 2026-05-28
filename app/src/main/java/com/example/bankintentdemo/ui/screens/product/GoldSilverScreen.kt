package com.example.bankintentdemo.ui.screens.product

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
fun GoldSilverScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // 1. 오늘의 시세 전용 커스텀 배너
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF7F8FA))
                .padding(24.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(Color.White).padding(horizontal = 8.dp, vertical = 4.dp)) {
                        Text("오늘의 시세", fontSize = 11.sp, color = Color(0xFF6B7279))
                    }
                    Text("기준가격(원/g) ↻", fontSize = 11.sp, color = Color(0xFF8A9199))
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(modifier = Modifier.size(24.dp).clip(RoundedCornerShape(4.dp)).background(Color(0xFFFFCC00)))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("골드", fontSize = 13.sp, color = Color(0xFF6B7279))
                        Text("214,051.76", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(modifier = Modifier.size(24.dp).clip(RoundedCornerShape(4.dp)).background(Color(0xFF9AA2AA)))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("실버", fontSize = 13.sp, color = Color(0xFF6B7279))
                        Text("3,552.50", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text("2026.05.28 22:26:33 / 2026.05.28 17:00:08", fontSize = 11.sp, color = Color(0xFF8A9199))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        JoinManageToggle()
        SubCategoryChips(listOf("전체", "골드 입출금", "실물"))

        // 2. 골드 입출금
        Text("골드 입출금 1", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        Spacer(modifier = Modifier.height(12.dp))
        ProductListItem(
            title = "KB골드투자통장",
            desc = "금 실물을 은행간 인수·인도하는 방식으로 자유롭게 입출금이 가능한 금 투자상품",
            extraInfo = "", rate = ""
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 3. 실물 (심플 리스트)
        Text("실물 3", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
        Spacer(modifier = Modifier.height(12.dp))
        SimpleGoldItem("골드바", "골드바 모양 실물 구매")
        Spacer(modifier = Modifier.height(8.dp))
        SimpleGoldItem("골드바(지점 구매 전용)", "골드바 모양 실물 구매")
        Spacer(modifier = Modifier.height(8.dp))
        SimpleGoldItem("실버바", "실버바 모양 실물 구매")

        Spacer(modifier = Modifier.height(32.dp))

        // 4. 하단 배너
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFFFF6D7))
                .padding(20.dp)
        ) {
            Column {
                Text("우리아이 KB골드투자통장 만들기", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                Spacer(modifier = Modifier.height(4.dp))
                Text("자녀의 골드투자도 KB에서!", fontSize = 13.sp, color = Color(0xFF6B7279))
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

// 실물 전용 심플 리스트 컴포넌트
@Composable
private fun SimpleGoldItem(title: String, desc: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFEBECEF), RoundedCornerShape(12.dp))
            .padding(20.dp)
    ) {
        Column {
            Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
            Spacer(modifier = Modifier.height(4.dp))
            Text(desc, fontSize = 13.sp, color = Color(0xFF8A9199))
        }
    }
}