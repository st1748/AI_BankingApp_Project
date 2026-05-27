package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bankintentdemo.model.PredictionResult
import com.example.bankintentdemo.navigation.AppRoute

@Composable
fun Top3ResultCard(navController: NavController, top3List: List<PredictionResult>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "분석완료! 가장 일치하는 페이지 3가지 입니다.",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0075FF),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(12.dp))

            top3List.forEachIndexed { index, result ->
                // 1. 모델의 숫자 결과(intentIndex)를 한글 이름과 네비게이션 주소로 변환
                val mappedMenu = getIntentMapping(result.intentIndex)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // 2. 카드를 클릭하면 해당하는 진짜 화면으로 다이렉트 점프
                            navController.navigate(mappedMenu.route.route)
                        }
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        // 한글 라벨 출력
                        Text(
                            text = "${index + 1}. ${mappedMenu.title}",
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        // 소분류 태그 출력 (예: 조회, 이체)
                        Text(
                            text = mappedMenu.category,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // 확신도 퍼센트 표시
                        Text(
                            text = "${(result.confidence * 100).toInt()}%",
                            color = Color(0xFF0075FF),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "화면 이동",
                            tint = Color.LightGray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                if (index < top3List.size - 1) {
                    HorizontalDivider(color = Color(0xFFF0F2F5))
                }
            }
        }
    }
}

// UI 매핑용 내부 데이터 클래스
private data class IntentMenuMap(val title: String, val category: String, val route: AppRoute)

// 45개 모델 라벨 인덱스와 프로젝트의 실제 Screen Route 간의 매핑 테이블
private fun getIntentMapping(index: Int): IntentMenuMap {
    return var_optimized_labels[index] ?: IntentMenuMap("알 수 없는 업무", "기타", AppRoute.NormalHome)
}

// 학습 데이터셋의 Label Index 번호와 일치하는 한글 라벨 및 45개 상세 Route 매핑
private val var_optimized_labels = mapOf(
    // [상품가입관리] 0 ~ 11
    0 to IntentMenuMap("추천상품", "상품가입관리", AppRoute.ProductRecommend),
    1 to IntentMenuMap("예적금", "상품가입관리", AppRoute.ProductDepositSaving),
    2 to IntentMenuMap("대출", "상품가입관리", AppRoute.ProductLoan),
    3 to IntentMenuMap("입출금", "상품가입관리", AppRoute.ProductCheckingAccount),
    4 to IntentMenuMap("퇴직연금", "상품가입관리", AppRoute.ProductRetirementPension),
    5 to IntentMenuMap("펀드", "상품가입관리", AppRoute.ProductFund),
    6 to IntentMenuMap("청약/채권", "상품가입관리", AppRoute.ProductSubscriptionBond),
    7 to IntentMenuMap("ISA", "상품가입관리", AppRoute.ProductIsa),
    8 to IntentMenuMap("외화예금", "상품가입관리", AppRoute.ProductForeignDeposit),
    9 to IntentMenuMap("보험", "상품가입관리", AppRoute.ProductInsurance),
    10 to IntentMenuMap("신탁", "상품가입관리", AppRoute.ProductTrust),
    11 to IntentMenuMap("골드/실버", "상품가입관리", AppRoute.ProductGoldSilver),

    // [조회] 12 ~ 14
    12 to IntentMenuMap("전체계좌조회", "조회", AppRoute.InquiryAllAccounts),
    13 to IntentMenuMap("통합거래내역조회", "조회", AppRoute.InquiryIntegratedHistory),
    14 to IntentMenuMap("계좌관리", "조회", AppRoute.InquiryAccountManagement),

    // [이체/출금] 15 ~ 17
    15 to IntentMenuMap("이체", "이체/출금", AppRoute.TransferMain),
    16 to IntentMenuMap("자동이체", "이체/출금", AppRoute.TransferAutomatic),
    17 to IntentMenuMap("이체관리", "이체/출금", AppRoute.TransferManagement),

    // [공과금] 18
    18 to IntentMenuMap("공과금 납부/조회", "공과금", AppRoute.UtilityBill),

    // [자산관리] 19 ~ 20
    19 to IntentMenuMap("지출", "자산관리", AppRoute.AssetExpense),
    20 to IntentMenuMap("마이데이터 설정", "자산관리", AppRoute.AssetMyDataSetting),

    // [외환] 21 ~ 25
    21 to IntentMenuMap("환율", "외환", AppRoute.ExchangeRate),
    22 to IntentMenuMap("환전", "외환", AppRoute.ExchangeCurrency),
    23 to IntentMenuMap("해외송금", "외환", AppRoute.ExchangeOverseasRemittance),
    24 to IntentMenuMap("국내외화 이체/입출금", "외환", AppRoute.ExchangeDomesticForeign),
    25 to IntentMenuMap("외환정보 관리", "외환", AppRoute.ExchangeManagement),

    // [지갑] 26 ~ 29
    26 to IntentMenuMap("모바일신분증", "지갑", AppRoute.WalletMobileId),
    27 to IntentMenuMap("결제", "지갑", AppRoute.WalletPayment),
    28 to IntentMenuMap("NFT", "지갑", AppRoute.WalletNft),
    29 to IntentMenuMap("공공알리미", "지갑", AppRoute.WalletPublicAlert),

    // [혜택] 30 ~ 31
    30 to IntentMenuMap("이벤트", "혜택", AppRoute.BenefitEvent),
    31 to IntentMenuMap("쿠폰함", "혜택", AppRoute.BenefitCouponBox),

    // [생활] 32 ~ 35
    32 to IntentMenuMap("기차표 예매", "생활", AppRoute.LifeTrainTicket),
    33 to IntentMenuMap("여권 재발급 신청", "생활", AppRoute.LifePassportRenewal),
    34 to IntentMenuMap("스마트항공권", "생활", AppRoute.LifeSmartAirTicket),
    35 to IntentMenuMap("티머니 교통카드 충전", "생활", AppRoute.LifeTmoneyCharging),

    // [모바일업무지원] 36 ~ 40
    36 to IntentMenuMap("지점안내/번호표발행", "모바일업무지원", AppRoute.SupportBranchGuideTicket),
    37 to IntentMenuMap("증명서 발급/제출", "모바일업무지원", AppRoute.SupportCertificateIssue),
    38 to IntentMenuMap("통장/보안매체 발급", "모바일업무지원", AppRoute.SupportMediaPassbookIssue),
    39 to IntentMenuMap("전자영수증", "모바일업무지원", AppRoute.SupportEReceipt),
    40 to IntentMenuMap("사고신고", "모바일업무지원", AppRoute.SupportAccidentReport),

    // [멤버십] 41 ~ 43
    41 to IntentMenuMap("KB스타클럽", "멤버십", AppRoute.MembershipKbStarClub),
    42 to IntentMenuMap("급여클럽", "멤버십", AppRoute.MembershipSalaryClub),
    43 to IntentMenuMap("KB youth club", "멤버십", AppRoute.MembershipKbYouthClub),

    // [사업자] 44
    44 to IntentMenuMap("사장님+", "사업자", AppRoute.BusinessBossPlus)
)