package com.example.bankintentdemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.components.*

@Composable
fun NormalHomeScreen(navController: NavController, viewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        viewModel.toggleAiMode(false)
    }

    Scaffold(
        topBar = {
            TopBar(
                isAiMode = false,
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
            item { AdBannerSection() }                  // 1. 광고 배너 블록
            item { MainAccountSection(navController) }  // 2. 메인 통장 블록
            item { AssetSummarySection(navController) } // 3. 자산 요약 블록
        }
    }
}
