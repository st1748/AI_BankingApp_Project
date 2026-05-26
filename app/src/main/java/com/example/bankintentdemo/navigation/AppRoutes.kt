package com.example.bankintentdemo.navigation

sealed class AppRoute(val route: String) {
    // 메인 화면들
    object NormalHome : AppRoute("normal_home")
    object AIHome : AppRoute("ai_home")
    object MainMenu : AppRoute("main_menu")

    // 모델 분류 라벨 45개에 매칭될 상세 메뉴 object 라우트
    // product : 상품가입관리
    object ProductRecommend : AppRoute("product_recommend")
    object ProductDepositSaving : AppRoute("product_deposit_saving")
    object ProductLoan : AppRoute("product_loan")
    object ProductCheckingAccount : AppRoute("product_checking_account")
    object ProductRetirementPension : AppRoute("product_retirement_pension")
    object ProductFund : AppRoute("product_fund")
    object ProductSubscriptionBond : AppRoute("product_subscription_bond")
    object ProductIsa : AppRoute("product_isa")
    object ProductForeignDeposit : AppRoute("product_foreign_deposit")
    object ProductInsurance : AppRoute("product_insurance")
    object ProductTrust : AppRoute("product_trust")
    object ProductGoldSilver : AppRoute("product_gold_silver")
    
    // inquiry : 조회
    object InquiryAllAccounts : AppRoute("inquiry_all_accounts")
    object InquiryIntegratedHistory : AppRoute("inquiry_integrated_history")
    object InquiryAccountManagement : AppRoute("inquiry_account_management")

    // 3. transfer : 이체/출금
    object TransferMain : AppRoute("transfer_main")
    object TransferAutomatic : AppRoute("transfer_automatic")
    object TransferManagement : AppRoute("transfer_management")

    // 4. utility : 공과금
    object UtilityBill : AppRoute("utility_bill")

    // 5. asset : 자산관리
    object AssetExpense : AppRoute("asset_expense")
    object AssetMyDataSetting : AppRoute("asset_my_data_setting")

    // 6. exchange : 외환
    object ExchangeRate : AppRoute("exchange_rate")
    object ExchangeCurrency : AppRoute("exchange_currency")
    object ExchangeOverseasRemittance : AppRoute("exchange_overseas_remittance")
    object ExchangeDomesticForeign : AppRoute("exchange_domestic_foreign")
    object ExchangeManagement : AppRoute("exchange_management")

    // 7. wallet : 지갑
    object WalletMobileId : AppRoute("wallet_mobile_id")
    object WalletPayment : AppRoute("wallet_payment")
    object WalletNft : AppRoute("wallet_nft")
    object WalletPublicAlert : AppRoute("wallet_public_alert")

    // 8. benefit : 혜택
    object BenefitEvent : AppRoute("benefit_event")
    object BenefitCouponBox : AppRoute("benefit_coupon_box")

    // 9. life : 생활
    object LifeTrainTicket : AppRoute("life_train_ticket")
    object LifePassportRenewal : AppRoute("life_passport_renewal")
    object LifeSmartAirTicket : AppRoute("life_smart_air_ticket")
    object LifeTmoneyCharging : AppRoute("life_tmoney_charging")

    // 10. support : 모바일업무지원
    object SupportBranchGuideTicket : AppRoute("support_branch_guide_ticket")
    object SupportCertificateIssue : AppRoute("support_certificate_issue")
    object SupportMediaPassbookIssue : AppRoute("support_media_passbook_issue")
    object SupportEReceipt : AppRoute("support_e_receipt")
    object SupportAccidentReport : AppRoute("support_accident_report")

    // 11. membership : 멤버십
    object MembershipKbStarClub : AppRoute("membership_kb_star_club")
    object MembershipSalaryClub : AppRoute("membership_salary_club")
    object MembershipKbYouthClub : AppRoute("membership_kb_youth_club")

    // 12. business : 사업자
    object BusinessBossPlus : AppRoute("business_boss_plus")
}