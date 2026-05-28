package com.example.bankintentdemo.ui.screens.exchange

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun DomesticForeignTransferScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    ExchangeMenuScaffold(
        navController = navController,
        viewModel = viewModel,
        title = "국내외화이체/예금입출금",
        menus = listOf(
            "외화이체/예금입출금",
            "외화자동이체",
            "외화이체 내역조회"
        )
    )
}
