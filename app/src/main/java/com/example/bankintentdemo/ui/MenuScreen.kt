package com.example.bankintentdemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute

// 부연 설명(Subtitle)이 있는 메뉴를 위한 데이터 클래스
data class MenuItem(val title: String, val route: String, val subtitle: String? = null)

@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // --- 1. 상단바 (검색 & 닫기) ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "검색",
                modifier = Modifier.size(28.dp).clickable { /* 검색 */ },
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(20.dp))
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "닫기",
                modifier = Modifier.size(28.dp).clickable { navController.popBackStack() },
                tint = Color.Black
            )
        }

        // --- 2. 메인 스크롤 메뉴 리스트 ---
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
        ) {
            // [1] 상품가입/관리 (그리드 스타일)
            item {
                CategoryHeader(title = "상품가입/관리", iconText = "KB", iconBgColor = Color(0xFFFFCC00), iconTextColor = Color.Black)
                Spacer(modifier = Modifier.height(16.dp))
                ProductGridSection(navController)
                Divider(color = Color(0xFFF3F4F6), thickness = 1.dp, modifier = Modifier.padding(vertical = 24.dp))
            }

            // [2] 조회
            item {
                CategoryHeader(title = "조회", icon = Icons.Default.Search, iconBgColor = Color(0xFF10B981))
                MenuListSection(navController, listOf(
                    MenuItem("전체계좌조회", AppRoute.InquiryAllAccounts.route),
                    MenuItem("통합거래내역조회", AppRoute.InquiryIntegratedHistory.route),
                    MenuItem("계좌관리", AppRoute.InquiryAccountManagement.route, "비밀번호 관리, 계좌통합관리서비스(어카운트인포) 등")
                ))
                Divider(color = Color(0xFFF3F4F6), thickness = 1.dp, modifier = Modifier.padding(vertical = 24.dp))
            }

            // [3] 이체/출금
            item {
                CategoryHeader(title = "이체/출금", icon = Icons.Default.ArrowForward, iconBgColor = Color(0xFF3B82F6))
                MenuListSection(navController, listOf(
                    MenuItem("이체", AppRoute.TransferMain.route),
                    MenuItem("자동이체", AppRoute.TransferAutomatic.route),
                    MenuItem("이체관리", AppRoute.TransferManagement.route, "이체한도 조회/변경, 출금계좌등록/해제/등록방법변경 등")
                ))
                Divider(color = Color(0xFFF3F4F6), thickness = 1.dp, modifier = Modifier.padding(vertical = 24.dp))
            }

            // [4] 공과금
            item {
                CategoryHeader(title = "공과금", icon = Icons.Default.Receipt, iconBgColor = Color(0xFF10B981), isSquareIcon = true)
                MenuListSection(navController, listOf(
                    MenuItem("공과금 납부/조회", AppRoute.UtilityBill.route)
                ))
                Divider(color = Color(0xFFF3F4F6), thickness = 1.dp, modifier = Modifier.padding(vertical = 24.dp))
            }

            // [5] 자산관리
            item {
                CategoryHeader(title = "자산관리", icon = Icons.Default.PieChart, iconBgColor = Color(0xFFF3F4F6), iconTintColor = Color(0xFFEF4444))
                MenuListSection(navController, listOf(
                    MenuItem("지출", AppRoute.AssetExpense.route, "가계부, 카드관리, 정기지출"),
                    MenuItem("마이데이터 설정", AppRoute.AssetMyDataSetting.route)
                ))
                Divider(color = Color(0xFFF3F4F6), thickness = 1.dp, modifier = Modifier.padding(vertical = 24.dp))
            }

            // [6] 외환
            item {
                CategoryHeader(title = "외환", icon = Icons.Default.AttachMoney, iconBgColor = Color(0xFFFFCC00))
                MenuListSection(navController, listOf(
                    MenuItem("환율", AppRoute.ExchangeRate.route),
                    MenuItem("환전", AppRoute.ExchangeCurrency.route),
                    MenuItem("해외송금", AppRoute.ExchangeOverseasRemittance.route),
                    MenuItem("국내외화 이체/입출금", AppRoute.ExchangeDomesticForeign.route),
                    MenuItem("외환정보 관리", AppRoute.ExchangeManagement.route)
                ))
                Divider(color = Color(0xFFF3F4F6), thickness = 1.dp, modifier = Modifier.padding(vertical = 24.dp))
            }

            // [7] 지갑
            item {
                CategoryHeader(title = "지갑", icon = Icons.Default.AccountBalanceWallet, iconBgColor = Color(0xFFE0E7FF), iconTintColor = Color(0xFF3B82F6))
                MenuListSection(navController, listOf(
                    MenuItem("모바일 신분증", AppRoute.WalletMobileId.route, "주민등록증, 운전면허증, 국가보훈증, 외국인증"),
                    MenuItem("결제", AppRoute.WalletPayment.route, "계좌기반 간편결제, 스타포인트 사용 가능"),
                    MenuItem("NFT", AppRoute.WalletNft.route, "티켓도 디지털로! NFT에 안전보관"),
                    MenuItem("공공알리미(국민비서 · 전자문서)", AppRoute.WalletPublicAlert.route)
                ))
                Divider(color = Color(0xFFF3F4F6), thickness = 1.dp, modifier = Modifier.padding(vertical = 24.dp))
            }

            // [8] 혜택
            item {
                CategoryHeader(title = "혜택", icon = Icons.Default.CardGiftcard, iconBgColor = Color(0xFFFEE2E2), iconTintColor = Color(0xFFEF4444))
                MenuListSection(navController, listOf(
                    MenuItem("이벤트", AppRoute.BenefitEvent.route),
                    MenuItem("쿠폰함", AppRoute.BenefitCouponBox.route)
                ))
                Divider(color = Color(0xFFF3F4F6), thickness = 1.dp, modifier = Modifier.padding(vertical = 24.dp))
            }

            // [9] 생활
            item {
                CategoryHeader(title = "생활", icon = Icons.Default.AddBox, iconBgColor = Color(0xFF8B5CF6), isSquareIcon = true)
                MenuListSection(navController, listOf(
                    MenuItem("기차표(KTX · SRT) 예매", AppRoute.LifeTrainTicket.route),
                    MenuItem("여권 재발급 신청", AppRoute.LifePassportRenewal.route),
                    MenuItem("스마트항공권", AppRoute.LifeSmartAirTicket.route),
                    MenuItem("티머니 교통카드 충전", AppRoute.LifeTmoneyCharging.route)
                ))
                Divider(color = Color(0xFFF3F4F6), thickness = 1.dp, modifier = Modifier.padding(vertical = 24.dp))
            }

            // [10] 모바일 업무지원
            item {
                CategoryHeader(title = "모바일 업무지원", icon = Icons.Default.Description, iconBgColor = Color(0xFFE0F2FE), iconTintColor = Color(0xFF0EA5E9), isSquareIcon = true)
                MenuListSection(navController, listOf(
                    MenuItem("지점안내/번호표발행", AppRoute.SupportBranchGuideTicket.route),
                    MenuItem("증명서 발급/제출", AppRoute.SupportCertificateIssue.route, "정부24 전자증명서, 예금잔액증명서, 금융거래확인서 등"),
                    MenuItem("통장/보안매체 재발급", AppRoute.SupportMediaPassbookIssue.route, "지점수령, STM수령, 등기우편수령"),
                    MenuItem("전자영수증", AppRoute.SupportEReceipt.route, "은행영수증, 구매영수증"),
                    MenuItem("사고신고", AppRoute.SupportAccidentReport.route, "착오송금반환, 분실신고 등")
                ))
                Divider(color = Color(0xFFF3F4F6), thickness = 1.dp, modifier = Modifier.padding(vertical = 24.dp))
            }

            // [11] 멤버십
            item {
                CategoryHeader(title = "멤버십", icon = Icons.Default.Star, iconBgColor = Color(0xFFFFCC00))
                MenuListSection(navController, listOf(
                    MenuItem("KB스타클럽", AppRoute.MembershipKbStarClub.route),
                    MenuItem("급여클럽", AppRoute.MembershipSalaryClub.route),
                    MenuItem("KB Youth Club/밀리터리 클럽 🔴", AppRoute.MembershipKbYouthClub.route)
                ))
                Divider(color = Color(0xFFF3F4F6), thickness = 1.dp, modifier = Modifier.padding(vertical = 24.dp))
            }

            // [12] 사업자
            item {
                CategoryHeader(title = "사업자", icon = Icons.Default.BusinessCenter, iconBgColor = Color(0xFFB45309), isSquareIcon = true)
                MenuListSection(navController, listOf(
                    MenuItem("사장님+", AppRoute.BusinessBossPlus.route)
                ))
                Spacer(modifier = Modifier.height(60.dp)) // 마지막 하단 여백
            }
        }
    }
}

// 카테고리 헤더 (아이콘 + 제목)
@Composable
fun CategoryHeader(
    title: String,
    icon: ImageVector? = null,
    iconText: String? = null,
    iconBgColor: Color,
    iconTintColor: Color = Color.White,
    iconTextColor: Color = Color.White,
    isSquareIcon: Boolean = false
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(18.dp)
                .clip(if (isSquareIcon) RoundedCornerShape(4.dp) else CircleShape)
                .background(iconBgColor),
            contentAlignment = Alignment.Center
        ) {
            if (iconText != null) {
                Text(text = iconText, color = iconTextColor, fontSize = 8.sp, fontWeight = FontWeight.Bold)
            } else if (icon != null) {
                Icon(imageVector = icon, contentDescription = null, tint = iconTintColor, modifier = Modifier.size(12.dp))
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
    }
}

// 하위 메뉴 리스트 출력용 컴포넌트
@Composable
fun MenuListSection(navController: NavController, items: List<MenuItem>) {
    Column(modifier = Modifier.padding(top = 16.dp, start = 8.dp)) {
        items.forEach { item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(item.route) }
                    .padding(vertical = 12.dp)
            ) {
                Text(text = item.title, fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Normal)
                if (item.subtitle != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = item.subtitle, fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}

//  4x3 그리드 컴포넌트
@Composable
fun ProductGridSection(navController: NavController) {
    val productItems = listOf(
        Pair("추천상품", AppRoute.ProductRecommend.route), Pair("예적금", AppRoute.ProductDepositSaving.route), Pair("대출", AppRoute.ProductLoan.route), Pair("입출금", AppRoute.ProductCheckingAccount.route),
        Pair("퇴직연금", AppRoute.ProductRetirementPension.route), Pair("펀드", AppRoute.ProductFund.route), Pair("청약/채권", AppRoute.ProductSubscriptionBond.route), Pair("ISA", AppRoute.ProductIsa.route),
        Pair("외화예금", AppRoute.ProductForeignDeposit.route), Pair("보험", AppRoute.ProductInsurance.route), Pair("신탁", AppRoute.ProductTrust.route), Pair("골드/실버", AppRoute.ProductGoldSilver.route)
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (row in 0 until 3) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (col in 0 until 4) {
                    val index = row * 4 + col
                    val item = productItems[index]
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp)
                            .background(Color(0xFFF3F4F6), RoundedCornerShape(8.dp))
                            .clickable { navController.navigate(item.second) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = item.first, fontSize = 13.sp, color = Color.Black, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}