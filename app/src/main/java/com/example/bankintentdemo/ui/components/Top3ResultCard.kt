package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute

@Composable
fun Top3ResultCard(navController: NavController, prompt: String) {
    // 실제 모델이 연동되기 전 임시로 보여줄 가짜 Top 3 결과
    val dummyResults = listOf(
        Pair("이체 / 연락처 이체", AppRoute.TransferMain.route),
        Pair("조회 / 전체계좌조회", AppRoute.InquiryAllAccounts.route),
        Pair("자산관리 / 지출", AppRoute.AssetExpense.route)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "'$prompt' 분석 결과",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0075FF)
            )
            Spacer(modifier = Modifier.height(12.dp))

            dummyResults.forEachIndexed { index, result ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate(result.second) }
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "${index + 1}. ${result.first}", fontWeight = FontWeight.Medium)
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "이동", tint = Color.LightGray)
                }
                if (index < dummyResults.size - 1) {
                    Divider(color = Color(0xFFF0F2F5))
                }
            }
        }
    }
}