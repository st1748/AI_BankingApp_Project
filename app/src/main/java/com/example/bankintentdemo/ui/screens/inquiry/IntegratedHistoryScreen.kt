package com.example.bankintentdemo.ui.screens.inquiry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.border
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.ui.MainViewModel
import com.example.bankintentdemo.ui.screens.inquiry.components.InquiryTopBar

@Composable
fun IntegratedHistoryScreen(navController: NavController, viewModel: MainViewModel) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        InquiryTopBar(title = "통합거래내역조회", navController = navController, isAiMode = isAiMode)

        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            // 상단 탭
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("KB국민은행", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color(0xFF20242A)))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("다른금융", fontSize = 16.sp, color = Color(0xFF8A9199))
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFEBECEF)))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("❕ 최근 1년까지의 거래내역만 조회 가능합니다", fontSize = 13.sp, color = Color(0xFF8A9199), modifier = Modifier.padding(horizontal = 24.dp))

            // 달력 선택
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text("<", fontSize = 16.sp, color = Color(0xFF8A9199))
                Spacer(modifier = Modifier.width(24.dp))
                Text("2026.5", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                Spacer(modifier = Modifier.width(24.dp))
                Text(">", fontSize = 16.sp, color = Color(0xFFEBECEF))
            }

            // 입출금 현황
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text("입출금 현황", fontSize = 14.sp, color = Color(0xFF6B7279))
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp))) {
                    Box(modifier = Modifier.weight(0.56f).fillMaxHeight().background(Color(0xFF8DA8D3))) // 파랑
                    Box(modifier = Modifier.weight(0.44f).fillMaxHeight().background(Color(0xFFE55D5D))) // 빨강
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("🔵 입금 56%", fontSize = 12.sp, color = Color(0xFF6B7279))
                    Text("989,800원", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("🔴 출금 44%", fontSize = 12.sp, color = Color(0xFF6B7279))
                    Text("785,728원", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color(0xFFF0F2F5), thickness = 8.dp)

            // 거래내역 리스트 헤더
            Row(modifier = Modifier.fillMaxWidth().padding(24.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("거래내역", fontSize = 15.sp, color = Color(0xFF6B7279))
                Text("🗓️ 달력보기", fontSize = 14.sp, color = Color(0xFF4A5056))
            }
            Text("2026.05.27", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A), modifier = Modifier.padding(horizontal = 24.dp))

            Spacer(modifier = Modifier.height(16.dp))

            // 타임라인 내역들
            TimelineItem("21:14:58", "씨유(CU)인천공항T21", "6,100", "407,870")
            TimelineItem("18:47:58", "아성다이소", "14,000", "413,970")
            TimelineItem("14:04:48", "카페캠퍼", "7,300", "427,970")
            TimelineItem("14:01:05", "이마트24R홍대기숙", "1,700", "435,270")

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

// 타임라인 그리기 컴포넌트
@Composable
fun TimelineItem(time: String, store: String, outAmount: String, balance: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).height(IntrinsicSize.Min)) {
        // 왼쪽 세로 선 및 점
        Box(modifier = Modifier.width(20.dp).fillMaxHeight(), contentAlignment = Alignment.TopCenter) {
            Box(modifier = Modifier.width(2.dp).fillMaxHeight().background(Color(0xFFEBECEF)))
            Box(modifier = Modifier.padding(top = 4.dp).size(8.dp).clip(CircleShape).border(2.dp, Color(0xFFEBECEF), CircleShape).background(Color.White))
        }

        // 우측 내용
        Column(modifier = Modifier.weight(1f).padding(bottom = 24.dp, start = 8.dp)) {
            Text(time, fontSize = 12.sp, color = Color(0xFF8A9199))
            Spacer(modifier = Modifier.height(8.dp))
            Text("KB나라사랑우대통장", fontSize = 13.sp, color = Color(0xFF6B7279))
            Text("466802-04-274759", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
            Text(store, fontSize = 13.sp, color = Color(0xFF6B7279))
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text("출금 ", fontSize = 14.sp, color = Color(0xFF6B7279))
                Text("${outAmount}원", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE55D5D))
            }
            Text("잔액 ${balance}원", fontSize = 12.sp, color = Color(0xFF8A9199), modifier = Modifier.align(Alignment.End))
        }
    }
}