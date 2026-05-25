package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute

@Composable
fun AssetSummarySection(navController: NavController) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        // KB Youth Club 배너 (클릭 시 이동)
        Button(
            onClick = { navController.navigate(AppRoute.MembershipKbYouthClub.route) },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("KB Youth Club 혜택 보기", color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 나의 총 자산 (디자인 전용)
        InfoCard(title = "나의 총 자산", amount = "0 원")
        Spacer(modifier = Modifier.height(12.dp))

        // 이번 주 카드 결제 (디자인 전용)
        InfoCard(title = "이번 주 카드 결제", amount = "0 원")
        Spacer(modifier = Modifier.height(12.dp))

        // 오늘 한 지출 (디자인 전용)
        InfoCard(title = "오늘 한 지출", amount = "0 원")
        Spacer(modifier = Modifier.height(32.dp)) // 하단바에 가리지 않도록 여백 추가
    }
}

@Composable
fun InfoCard(title: String, amount: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F6F8)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, fontSize = 16.sp, color = Color.DarkGray)
            Text(text = amount, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}