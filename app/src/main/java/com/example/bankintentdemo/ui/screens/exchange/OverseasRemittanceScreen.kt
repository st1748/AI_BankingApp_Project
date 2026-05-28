package com.example.bankintentdemo.ui.screens.exchange

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun OverseasRemittanceScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    ExchangeMenuScaffold(
        navController = navController,
        viewModel = viewModel,
        title = "해외송금",
        menus = listOf(
            "해외송금보내기",
            "웨스턴유니온송금보내기",
            "보낸송금 내용변경/반환신청",
            "보낸내역조회",
            "무증빙 해외송금 내역조회",
            "해외송금받기",
            "거래외국환은행지정"
        )
    )
}
