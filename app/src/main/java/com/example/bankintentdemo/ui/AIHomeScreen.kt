package com.example.bankintentdemo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.components.*

@Composable
fun AIHomeScreen(navController: NavController, viewModel: MainViewModel) {
    var currentPrompt by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.toggleAiMode(true)
    }

    Scaffold(
        topBar = {
            TopBar(
                isAiMode = true,
                onAiModeToggle = { if (!it) navController.navigate(AppRoute.NormalHome.route) {
                    popUpTo(AppRoute.AIHome.route) { inclusive = true }
                } },
                onMenuClick = { navController.navigate(AppRoute.MainMenu.route) }
            )
        },
        // bottomBar = { BottomNavBar(navController) } // AI 모드에서도 하단바는 그대로 유지 (연속성)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 1. 일반 홈과 동일한 위치를 유지하기 위해 광고 배너 배치
            // item { AdBannerSection() }

            // 2. 일반 홈과 동일한 통장 카드 섹션
            item { MainAccountSection(navController) }

            // 3. 통장 카드 바로 아래 프롬프트창 배치
            item {
                PromptInputBar(onSendClick = { input -> currentPrompt = input })
            }

            // 4. AI 분석 결과 Top 3 (프롬프트 입력 시에만 나타남)
            if (currentPrompt.isNotEmpty()) {
                item {
                    Top3ResultCard(navController = navController, prompt = currentPrompt)
                }
            }
        }
    }
}
