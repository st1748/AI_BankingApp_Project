package com.example.bankintentdemo.ui.screens.exchange

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun ExchangeRateScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    ExchangeMenuScaffold(
        navController = navController,
        viewModel = viewModel,
        title = "환율",
        menus = listOf(
            "환율조회",
            "환율동향정보"
        )
    )
}
