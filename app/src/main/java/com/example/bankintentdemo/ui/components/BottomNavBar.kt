package com.example.bankintentdemo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        // 1. 상품 (금융상품/추천상품 연결)
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "상품") },
            label = { Text("상품", fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { navController.navigate(AppRoute.ProductRecommend.route) },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        // 2. 자산 (자산관리/지출 연결)
        NavigationBarItem(
            icon = { Icon(Icons.Default.PieChart, contentDescription = "자산") },
            label = { Text("자산", fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { navController.navigate(AppRoute.AssetExpense.route) },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        // 3. 테마 (디자인만, 기능 없음)
        NavigationBarItem(
            icon = { Icon(Icons.Default.Star, contentDescription = "테마") },
            label = { Text("테마", fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { /* 아무 기능 없음 */ },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        // 4. 지갑 (임시로 지갑/결제 페이지 연결 - 필요시 수정 가능)
        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountBox, contentDescription = "지갑") },
            label = { Text("지갑", fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { navController.navigate(AppRoute.WalletPayment.route) },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )

        // 5. 혜택 (임시로 혜택/이벤트 페이지 연결 - 필요시 수정 가능)
        NavigationBarItem(
            icon = { Icon(Icons.Default.CardGiftcard, contentDescription = "혜택") },
            label = { Text("혜택", fontWeight = FontWeight.Bold) },
            selected = false,
            onClick = { navController.navigate(AppRoute.BenefitEvent.route) },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}