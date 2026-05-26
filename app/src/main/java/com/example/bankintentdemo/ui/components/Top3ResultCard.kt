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
import com.example.bankintentdemo.model.PredictionResult

@Composable
fun Top3ResultCard(navController: NavController, top3List: List<PredictionResult>) {
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
                text = "분석결과!",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0075FF)
            )
            Spacer(modifier = Modifier.height(12.dp))

            // AI 3개의 결과
            top3List.forEachIndexed { index, result ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        // 추후 여기에 result.intentIndex 에 따른 네비게이션 로직 필요!!!!!
                        .clickable { /* navController.navigate(...) */ }
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "${index + 1}. 인텐트 ID: ${result.intentIndex}", fontWeight = FontWeight.Medium)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${(result.confidence * 100).toInt()}%",
                            color = Color(0xFF0075FF),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "이동", tint = Color.LightGray)
                    }
                }
                if (index < top3List.size - 1) {
                    HorizontalDivider(color = Color(0xFFF0F2F5)) // 구분선
                }
            }
        }
    }
}