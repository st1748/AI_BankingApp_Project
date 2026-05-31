package com.example.bankintentdemo.ui.screens.product

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel
import com.example.bankintentdemo.ui.screens.product.components.*

val productCategories = listOf(
    "추천", "예적금", "대출", "입출금", "퇴직연금", "펀드",
    "청약/채권", "ISA", "외화예금", "보험", "신탁", "골드/실버"
)

@Composable
fun ProductContainerScreen(
    navController: NavController,
    viewModel: MainViewModel,
    initialTab: Int = 0
) {
    var selectedTabIndex by remember { mutableStateOf(initialTab) }
    var showDropdown by remember { mutableStateOf(false) }
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 공통 상단 액션바
        ProductTopBar(
            onBackClick = { navController.popBackStack() },
            onHomeClick = {
                if (isAiMode) {
                    navController.navigate(AppRoute.AIHome.route) {
                        popUpTo(AppRoute.AIHome.route) { inclusive = true }
                        launchSingleTop = true
                    }
                } else {
                    navController.navigate(AppRoute.NormalHome.route) {
                        popUpTo(AppRoute.NormalHome.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            },
            onMenuClick = { navController.navigate(AppRoute.MainMenu.route) }
        )

        // 하단 영역 레이어(Box)
        Box(modifier = Modifier.weight(1f)) {

            // 1층 레이어 : 스와이프 탭 + 12개 상세 본문 화면
            Column(modifier = Modifier.fillMaxSize()) {
                ProductScrollableTabs(
                    selectedTabIndex = selectedTabIndex,
                    onTabSelected = { selectedTabIndex = it },
                    onOpenBottomSheet = { showDropdown = true }
                )

                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFF0F2F5)))

                // 본문 스위칭 영역
                Box(modifier = Modifier.weight(1f)) {
                    when (selectedTabIndex) {
                        0 -> RecommendProductScreen()      // 추천
                        1 -> DepositSavingScreen()         // 예적금
                        2 -> LoanScreen()                  // 대출
                        3 -> CheckingAccountScreen()       // 입출금
                        4 -> RetirementPensionScreen()     // 퇴직연금
                        5 -> FundScreen()                  // 펀드
                        6 -> SubscriptionBondScreen()      // 청약/채권
                        7 -> IsaScreen()                   // ISA
                        8 -> ForeignDepositScreen()        // 외화예금
                        9 -> InsuranceScreen()             // 보험
                        10 -> TrustScreen()                // 신탁
                        11 -> GoldSilverScreen()           // 골드/실버
                        else -> RecommendProductScreen()   // 안전 장치 (예외 발생 시 기본 화면)
                    }
                }
            }

            // 2층 레이어 : 어두운 딤(Dim) 배경
            if (showDropdown) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.6f))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { showDropdown = false }
                        )
                )
            }

            // 3층 레이어 : 위에서 내려오는 전체 카테고리 패널
            androidx.compose.animation.AnimatedVisibility(
                visible = showDropdown,
                enter = expandVertically(expandFrom = Alignment.Top),
                exit = shrinkVertically(shrinkTowards = Alignment.Top),
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                FullCategoryDropdownPanel(
                    onDismiss = { showDropdown = false },
                    onCategorySelected = { index ->
                        selectedTabIndex = index
                        showDropdown = false
                    }
                )
            }
        }
    }
}