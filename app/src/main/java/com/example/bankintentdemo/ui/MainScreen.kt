package com.example.bankintentdemo.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bankintentdemo.navigation.AppRoute

// 1. 상품가입관리 (product) 화면 임포트
import com.example.bankintentdemo.ui.screens.product.RecommendProductScreen
import com.example.bankintentdemo.ui.screens.product.DepositSavingScreen
import com.example.bankintentdemo.ui.screens.product.LoanScreen
import com.example.bankintentdemo.ui.screens.product.CheckingAccountScreen
import com.example.bankintentdemo.ui.screens.product.RetirementPensionScreen
import com.example.bankintentdemo.ui.screens.product.FundScreen
import com.example.bankintentdemo.ui.screens.product.SubscriptionBondScreen
import com.example.bankintentdemo.ui.screens.product.IsaScreen
import com.example.bankintentdemo.ui.screens.product.ForeignDepositScreen
import com.example.bankintentdemo.ui.screens.product.InsuranceScreen
import com.example.bankintentdemo.ui.screens.product.TrustScreen
import com.example.bankintentdemo.ui.screens.product.GoldSilverScreen

// 2. 조회 (inquiry) 화면 임포트
import com.example.bankintentdemo.ui.screens.inquiry.AllAccountsScreen
import com.example.bankintentdemo.ui.screens.inquiry.IntegratedHistoryScreen
import com.example.bankintentdemo.ui.screens.inquiry.AccountManagementScreen

// 3. 이체/출금 (transfer) 화면 임포트
import com.example.bankintentdemo.ui.screens.transfer.TransferScreen
import com.example.bankintentdemo.ui.screens.transfer.AutomaticTransferScreen
import com.example.bankintentdemo.ui.screens.transfer.TransferManagementScreen

// 4. 공과금 (utility) 화면 임포트
import com.example.bankintentdemo.ui.screens.utility.UtilityBillScreen

// 5. 자산관리 (asset) 화면 임포트
import com.example.bankintentdemo.ui.screens.asset.ExpenseScreen
import com.example.bankintentdemo.ui.screens.asset.MyDataSettingScreen

// 6. 외환 (exchange) 화면 임포트
import com.example.bankintentdemo.ui.screens.exchange.ExchangeRateScreen
import com.example.bankintentdemo.ui.screens.exchange.CurrencyExchangeScreen
import com.example.bankintentdemo.ui.screens.exchange.OverseasRemittanceScreen
import com.example.bankintentdemo.ui.screens.exchange.DomesticForeignTransferScreen
import com.example.bankintentdemo.ui.screens.exchange.ExchangeManagementScreen

// 7. 지갑 (wallet) 화면 임포트
import com.example.bankintentdemo.ui.screens.wallet.MobileIdScreen
import com.example.bankintentdemo.ui.screens.wallet.PaymentScreen
import com.example.bankintentdemo.ui.screens.wallet.NftScreen
import com.example.bankintentdemo.ui.screens.wallet.PublicAlertScreen

// 8. 혜택 (benefit) 화면 임포트
import com.example.bankintentdemo.ui.screens.benefit.EventScreen
import com.example.bankintentdemo.ui.screens.benefit.CouponBoxScreen

// 9. 생활 (life) 화면 임포트
import com.example.bankintentdemo.ui.screens.life.TrainTicketScreen
import com.example.bankintentdemo.ui.screens.life.PassportRenewalScreen
import com.example.bankintentdemo.ui.screens.life.SmartAirTicketScreen
import com.example.bankintentdemo.ui.screens.life.TmoneyCharghingScreen

// 10. 모바일업무지원 (support) 화면 임포트
import com.example.bankintentdemo.ui.screens.support.BranchGuideTicketScreen
import com.example.bankintentdemo.ui.screens.support.CertificateIssueScreen
import com.example.bankintentdemo.ui.screens.support.MediaPassbookIssueScreen
import com.example.bankintentdemo.ui.screens.support.EReceiptScreen
import com.example.bankintentdemo.ui.screens.support.AccidentReportScreen

// 11. 멤버십 (membership) 화면 임포트
import com.example.bankintentdemo.ui.screens.membership.KbStarClubScreen
import com.example.bankintentdemo.ui.screens.membership.SalaryClubScreen
import com.example.bankintentdemo.ui.screens.membership.KbYouthClubScreen

// 12. 사업자 (business) 화면 임포트
import com.example.bankintentdemo.ui.screens.business.BossPlusScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppRoute.NormalHome.route
    ) {
        // --- 메인 홈 및 전체 메뉴 뷰 ---
        composable(AppRoute.NormalHome.route) {
            NormalHomeScreen(navController = navController, viewModel = mainViewModel)
        }
        composable(AppRoute.AIHome.route) {
            AIHomeScreen(navController = navController, viewModel = mainViewModel)
        }
        composable(AppRoute.MainMenu.route) {
            MenuScreen(navController = navController)
        }

        // --- 상세 메뉴 뷰 (총 45개 분류 라벨 매핑 완료) ---

        // 1. 상품가입관리 (product)
        composable(AppRoute.ProductRecommend.route) { RecommendProductScreen() }
        composable(AppRoute.ProductDepositSaving.route) { DepositSavingScreen() }
        composable(AppRoute.ProductLoan.route) { LoanScreen() }
        composable(AppRoute.ProductCheckingAccount.route) { CheckingAccountScreen() }
        composable(AppRoute.ProductRetirementPension.route) { RetirementPensionScreen() }
        composable(AppRoute.ProductFund.route) { FundScreen() }
        composable(AppRoute.ProductSubscriptionBond.route) { SubscriptionBondScreen() }
        composable(AppRoute.ProductIsa.route) { IsaScreen() }
        composable(AppRoute.ProductForeignDeposit.route) { ForeignDepositScreen() }
        composable(AppRoute.ProductInsurance.route) { InsuranceScreen() }
        composable(AppRoute.ProductTrust.route) { TrustScreen() }
        composable(AppRoute.ProductGoldSilver.route) { GoldSilverScreen() }

        // 2. 조회 (inquiry)
        composable(AppRoute.InquiryAllAccounts.route) { AllAccountsScreen() }
        composable(AppRoute.InquiryIntegratedHistory.route) { IntegratedHistoryScreen() }
        composable(AppRoute.InquiryAccountManagement.route) { AccountManagementScreen() }

        // 3. 이체/출금 (transfer)
        composable(AppRoute.TransferMain.route) { TransferScreen() }
        composable(AppRoute.TransferAutomatic.route) { AutomaticTransferScreen() }
        composable(AppRoute.TransferManagement.route) { TransferManagementScreen() }

        // 4. 공과금 (utility)
        composable(AppRoute.UtilityBill.route) { UtilityBillScreen() }

        // 5. 자산관리 (asset)
        composable(AppRoute.AssetExpense.route) { ExpenseScreen() }
        composable(AppRoute.AssetMyDataSetting.route) { MyDataSettingScreen() }

        // 6. 외환 (exchange)
        composable(AppRoute.ExchangeRate.route) { ExchangeRateScreen() }
        composable(AppRoute.ExchangeCurrency.route) { CurrencyExchangeScreen() }
        composable(AppRoute.ExchangeOverseasRemittance.route) { OverseasRemittanceScreen() }
        composable(AppRoute.ExchangeDomesticForeign.route) { DomesticForeignTransferScreen() }
        composable(AppRoute.ExchangeManagement.route) { ExchangeManagementScreen() }

        // 7. 지갑 (wallet)
        composable(AppRoute.WalletMobileId.route) { MobileIdScreen() }
        composable(AppRoute.WalletPayment.route) { PaymentScreen() }
        composable(AppRoute.WalletNft.route) { NftScreen() }
        composable(AppRoute.WalletPublicAlert.route) { PublicAlertScreen() }

        // 8. 혜택 (benefit)
        composable(AppRoute.BenefitEvent.route) { EventScreen() }
        composable(AppRoute.BenefitCouponBox.route) {
            CouponBoxScreen(navController = navController, viewModel = mainViewModel)
        }

        // 9. 생활 (life)
        composable(AppRoute.LifeTrainTicket.route) { TrainTicketScreen() }
        composable(AppRoute.LifePassportRenewal.route) { PassportRenewalScreen() }
        composable(AppRoute.LifeSmartAirTicket.route) { SmartAirTicketScreen() }
        composable(AppRoute.LifeTmoneyCharging.route) { TmoneyCharghingScreen() }

        // 10. 모바일업무지원 (support)
        composable(AppRoute.SupportBranchGuideTicket.route) { BranchGuideTicketScreen() }
        composable(AppRoute.SupportCertificateIssue.route) { CertificateIssueScreen() }
        composable(AppRoute.SupportMediaPassbookIssue.route) { MediaPassbookIssueScreen() }
        composable(AppRoute.SupportEReceipt.route) { EReceiptScreen() }
        composable(AppRoute.SupportAccidentReport.route) { AccidentReportScreen() }

        // 11. 멤버십 (membership)
        composable(AppRoute.MembershipKbStarClub.route) { KbStarClubScreen() }
        composable(AppRoute.MembershipSalaryClub.route) { SalaryClubScreen() }
        composable(AppRoute.MembershipKbYouthClub.route) { KbYouthClubScreen() }

        // 12. 사업자 (business)
        composable(AppRoute.BusinessBossPlus.route) { BossPlusScreen() }
    }
}
