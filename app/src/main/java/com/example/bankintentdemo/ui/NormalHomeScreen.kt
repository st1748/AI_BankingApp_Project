package com.example.bankintentdemo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.components.*

@Composable
fun NormalHomeScreen(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopBar(
                isAiMode = false, // 일반 홈이므로 false
                onAiModeToggle = { if (it) navController.navigate(AppRoute.AIHome.route) {
                    popUpTo(AppRoute.NormalHome.route) { inclusive = true }
                } },
                onMenuClick = { navController.navigate(AppRoute.MainMenu.route) }
            )
        },
        bottomBar = { BottomNavBar(navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item { MainAccountSection(navController) }
            item { AssetSummarySection(navController) }
        }
    }
}