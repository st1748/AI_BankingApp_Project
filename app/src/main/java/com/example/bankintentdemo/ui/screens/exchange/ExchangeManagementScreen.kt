package com.example.bankintentdemo.ui.screens.exchange

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun ExchangeManagementScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    ExchangeMenuScaffold(
        navController = navController,
        viewModel = viewModel,
        title = "외환정보관리",
        menus = listOf(
            "영문정보관리",
            "외화알림서비스",
            "외화송금 국내/해외 주소록",
            "해외송금 수수료 납부/조회",
            "외화수표 수수료 납부/조회"
        )
    )
}
