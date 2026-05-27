package com.example.bankintentdemo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bankintentdemo.model.SlmModelManager
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.IntentUiState
import com.example.bankintentdemo.ui.MainViewModel
import com.example.bankintentdemo.ui.components.*

@Composable
fun AIHomeScreen(
    navController: NavController,
    viewModel: MainViewModel,
    modelManager: SlmModelManager // AI 엔진 주입
) {
    // AI의 상태(Idle, Loading, ShowTop3)를 실시간 관찰
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.toggleAiMode(true)
    }

    LaunchedEffect(Unit) {
        viewModel.toggleAiMode(true)
    }

    Scaffold(
        topBar = {
            TopBar(
                isAiMode = true,
                onAiModeToggle = { enabled ->
                    if (!enabled) {
                        viewModel.toggleAiMode(false)
                        navController.navigate(AppRoute.NormalHome.route) {
                            popUpTo(AppRoute.AIHome.route) { inclusive = true }
                        }
                    }
                },
                onMenuClick = { navController.navigate(AppRoute.MainMenu.route) }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 광고 배너 배치 넣을지 뺄지 결정 못함
            // item { AdBannerSection() }

            // 1. 통장 섹션
            item { MainAccountSection(navController) }

            // 2. 프롬프트 창: 마이크/전송 버튼을 누르면 뷰모델에 분석 요청
            item {
                PromptInputBar(onSendClick = { input ->
                    viewModel.analyzeUserQuery(input, modelManager)
                })
            }

            // 3. AI 분석 상태에 따른 화면 변화
            when (val state = uiState) {
                is IntentUiState.Loading -> {
                    item {
                        Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = androidx.compose.ui.graphics.Color(0xFF0075FF))
                        }
                    }
                }
                is IntentUiState.ShowTop3 -> {
                    item {
                        // 결과가 나오면 리스트를 카드에 넘김
                        Top3ResultCard(navController = navController, top3List = state.top3List)
                    }
                }
                else -> { /* 아무것도 입력 안했을 땐 빈 화면 유지 */ }
            }
        }
    }
}
