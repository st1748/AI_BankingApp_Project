package com.example.bankintentdemo.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute

// 대메뉴와 소메뉴 구조를 담을 데이터 클래스
data class MainMenuCategory(
    val name: String,
    val subMenus: List<SubMenuItem>
)

data class SubMenuItem(
    val title: String,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    // 12개 대메뉴와 45개 상세 메뉴 데이터 정의 구조화
    val menuCategories = remember {
        listOf(
            MainMenuCategory("상품가입관리", listOf(
                SubMenuItem("추천상품", AppRoute.ProductRecommend.route),
                SubMenuItem("예적금", AppRoute.ProductDepositSaving.route),
                SubMenuItem("대출", AppRoute.ProductLoan.route),
                SubMenuItem("입출금", AppRoute.ProductCheckingAccount.route),
                SubMenuItem("퇴직연금", AppRoute.ProductRetirementPension.route),
                SubMenuItem("펀드", AppRoute.ProductFund.route),
                SubMenuItem("청약/채권", AppRoute.ProductSubscriptionBond.route),
                SubMenuItem("ISA", AppRoute.ProductIsa.route),
                SubMenuItem("외화예금", AppRoute.ProductForeignDeposit.route),
                SubMenuItem("보험", AppRoute.ProductInsurance.route),
                SubMenuItem("신탁", AppRoute.ProductTrust.route),
                SubMenuItem("골드/실버", AppRoute.ProductGoldSilver.route)
            )),
            MainMenuCategory("조회", listOf(
                SubMenuItem("전체계좌조회", AppRoute.InquiryAllAccounts.route),
                SubMenuItem("통합거래내역조회", AppRoute.InquiryIntegratedHistory.route),
                SubMenuItem("계좌관리", AppRoute.InquiryAccountManagement.route)
            )),
            MainMenuCategory("이체/출금", listOf(
                SubMenuItem("이체", AppRoute.TransferMain.route),
                SubMenuItem("자동이체", AppRoute.TransferAutomatic.route),
                SubMenuItem("이체관리", AppRoute.TransferManagement.route)
            )),
            MainMenuCategory("공과금", listOf(
                SubMenuItem("공과금 납부/조회", AppRoute.UtilityBill.route)
            )),
            MainMenuCategory("자산관리", listOf(
                SubMenuItem("지출", AppRoute.AssetExpense.route),
                SubMenuItem("마이데이터 설정", AppRoute.AssetMyDataSetting.route)
            )),
            MainMenuCategory("외환", listOf(
                SubMenuItem("환율", AppRoute.ExchangeRate.route),
                SubMenuItem("환전", AppRoute.ExchangeCurrency.route),
                SubMenuItem("해외송금", AppRoute.ExchangeOverseasRemittance.route),
                SubMenuItem("국내외화 이체/입출금", AppRoute.ExchangeDomesticForeign.route),
                SubMenuItem("외환정보 관리", AppRoute.ExchangeManagement.route)
            )),
            MainMenuCategory("지갑", listOf(
                SubMenuItem("모바일신분증", AppRoute.WalletMobileId.route),
                SubMenuItem("결제", AppRoute.WalletPayment.route),
                SubMenuItem("NFT", AppRoute.WalletNft.route),
                SubMenuItem("공공알리미", AppRoute.WalletPublicAlert.route)
            )),
            MainMenuCategory("혜택", listOf(
                SubMenuItem("이벤트", AppRoute.BenefitEvent.route),
                SubMenuItem("쿠폰함", AppRoute.BenefitCouponBox.route)
            )),
            MainMenuCategory("생활", listOf(
                SubMenuItem("기차표 예매", AppRoute.LifeTrainTicket.route),
                SubMenuItem("여권 재발급 신청", AppRoute.LifePassportRenewal.route),
                SubMenuItem("스마트항공권", AppRoute.LifeSmartAirTicket.route),
                SubMenuItem("티머니 교통카드 충전", AppRoute.LifeTmoneyCharging.route)
            )),
            MainMenuCategory("모바일업무지원", listOf(
                SubMenuItem("지점안내/번호표발행", AppRoute.SupportBranchGuideTicket.route),
                SubMenuItem("증명서 발급/제출", AppRoute.SupportCertificateIssue.route),
                SubMenuItem("통장/보안매체 발급", AppRoute.SupportMediaPassbookIssue.route),
                SubMenuItem("전자영수증", AppRoute.SupportEReceipt.route),
                SubMenuItem("사고신고", AppRoute.SupportAccidentReport.route)
            )),
            MainMenuCategory("멤버십", listOf(
                SubMenuItem("KB스타클럽", AppRoute.MembershipKbStarClub.route),
                SubMenuItem("급여클럽", AppRoute.MembershipSalaryClub.route),
                SubMenuItem("KB youth club", AppRoute.MembershipKbYouthClub.route)
            )),
            MainMenuCategory("사업자", listOf(
                SubMenuItem("사장님+", AppRoute.BusinessBossPlus.route)
            ))
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("전체메뉴", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "뒤로가기")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(menuCategories) { category ->
                ExpandableMenuCard(category = category, navController = navController)
            }
        }
    }
}

@Composable
fun ExpandableMenuCard(category: MainMenuCategory, navController: NavController) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // 대메뉴 영역 (클릭 시 접고 펼침)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = category.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "펼치기 아이콘"
                )
            }

            // 소메뉴 리스트 영역 (애니메이션 노출)
            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Divider(modifier = Modifier.padding(horizontal = 16.dp))
                    category.subMenus.forEach { subMenu ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    // 클릭 시 매핑된 임시 화면 주소로 이동
                                    navController.navigate(subMenu.route)
                                }
                                .padding(horizontal = 24.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "•  ${subMenu.title}",
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }
        }
    }
}