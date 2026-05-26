package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute

@Composable
fun BottomNavBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp) // 살짝 높여서 입체감 확보
            .background(Color.Transparent)
    ) {
        // 실제 하단바 베이스 블록
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .align(Alignment.BottomCenter)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(title = "상품", icon = Icons.Default.CardGiftcard) {
                navController.navigate(AppRoute.ProductRecommend.route)
            }
            BottomNavItem(title = "자산", icon = Icons.Default.PieChart) {
                navController.navigate(AppRoute.AssetExpense.route)
            }

            // 가운데 지갑 자리는 자리 (아래 대형 볼륨 버튼이 올라올 자리)
            Spacer(modifier = Modifier.width(64.dp))

            BottomNavItem(title = "혜택", icon = Icons.Default.Redeem) {
                navController.navigate(AppRoute.BenefitEvent.route)
            }
            BottomNavItem(title = "테마", icon = Icons.Default.Assignment) {
                /* 디자인 전용 */
            }
        }

        // 지갑 서클 버튼
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 2.dp)
                .clickable { navController.navigate(AppRoute.WalletPayment.route) },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFBCA264)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBalanceWallet,
                    contentDescription = "지갑",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "지갑",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFBCA264)
            )
        }
    }
}

@Composable
fun RowScope.BottomNavItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .weight(1f)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = icon, contentDescription = title, tint = Color.Gray, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = title, fontSize = 11.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
    }
}