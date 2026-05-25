package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute

@Composable
fun AssetSummarySection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 1. KB Youth Club 배너
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate(AppRoute.MembershipKbYouthClub.route) },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("KB Youth Club", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Surface(
                            color = Color(0xFF818CF8), // 보라색 OPEN 뱃지
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text("OPEN", color = Color.White, fontSize = 10.sp, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp), fontWeight = FontWeight.Bold)
                        }
                    }
                    Text("20대만 입장 가능한 비밀공간!", fontSize = 13.sp, color = Color.Gray)
                }
                // 우측 캐릭터 아이콘 (임시 박스)
                Box(modifier = Modifier.size(50.dp).background(Color(0xFFF3F4F6), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                    Text("🐥", fontSize = 24.sp)
                }
            }
        }

        // 2. 자산 정보 카드들 (나의 총자산, 이번 주 카드결제, 오늘한 지출)
        SummaryInfoCard(title = "나의 총자산", amount = "??원")
        SummaryInfoCard(title = "이번 주 카드결제", amount = "??원")
        SummaryInfoCard(title = "오늘한 지출", amount = "??원")

        Spacer(modifier = Modifier.height(100.dp)) // 하단바에 가리지 않게 여백 추가
    }
}

@Composable
fun SummaryInfoCard(title: String, amount: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = amount, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(20.dp))
            }
        }
    }
}