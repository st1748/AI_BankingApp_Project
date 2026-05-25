package com.example.bankintentdemo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.components.*

@Composable
fun AIHomeScreen(navController: NavController, viewModel: MainViewModel) {
    // 사용자가 입력한 프롬프트를 임시 저장할 상태
    var currentPrompt by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBar(
                isAiMode = true, // AI 홈이므로 true
                onAiModeToggle = { if (!it) navController.navigate(AppRoute.NormalHome.route) {
                    popUpTo(AppRoute.AIHome.route) { inclusive = true }
                } },
                onMenuClick = { navController.navigate(AppRoute.MainMenu.route) }
            )
        },
        bottomBar = {
            PromptInputBar(
                onSendClick = { input -> currentPrompt = input }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MainAccountSection(navController) // 일반홈과 동일한 컴포넌트 재사용!

            // 프롬프트가 입력되었을 때만 Top3 결과를 보여줌
            if (currentPrompt.isNotEmpty()) {
                Top3ResultCard(navController = navController, prompt = currentPrompt)
            }
        }
    }
}