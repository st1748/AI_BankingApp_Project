package com.example.bankintentdemo.ui.screens.exchange

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun CurrencyExchangeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    ExchangeMenuScaffold(
        navController = navController,
        viewModel = viewModel,
        title = "환전",
        menus = listOf(
            "환전신청",
            "환전조회/관리",
            "비로그인 환전 내역 조회"
        )
    )
}
