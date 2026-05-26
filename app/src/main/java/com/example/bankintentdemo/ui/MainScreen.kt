package com.example.bankintentdemo.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private sealed interface MainDestination {
    data object Home : MainDestination
    data object Menu : MainDestination
    data object Wallet : MainDestination
    data object Benefits : MainDestination
    data object Transfer : MainDestination
    data object AutoTransfer : MainDestination
    data object TransferManagement : MainDestination
    data object UtilityBills : MainDestination
    data object Expense : MainDestination
    data object MyDataSettings : MainDestination
    data object ExchangeRate : MainDestination
    data object CurrencyExchange : MainDestination
    data object OverseasRemittance : MainDestination
    data object ForeignCurrencyTransfer : MainDestination
    data object ForeignInfoManagement : MainDestination
    data class EventList(val returnToMenu: Boolean) : MainDestination
    data class MobileId(val returnToWallet: Boolean) : MainDestination
    data class Payment(val returnToWallet: Boolean) : MainDestination
    data class NftWallet(val returnToWallet: Boolean) : MainDestination
    data class PublicNotifier(val returnToWallet: Boolean) : MainDestination
    data class Pending(val title: String) : MainDestination
}

@Composable
fun MainScreen() {
    var destination by remember { mutableStateOf<MainDestination>(MainDestination.Home) }

    when (val current = destination) {
        MainDestination.Home -> NormalHomeScreen(
            onAiToggleClick = { destination = MainDestination.Pending("AI 모드") },
            onMenuClick = { destination = MainDestination.Menu },
            onAccountClick = { destination = MainDestination.Pending("거래내역 조회") },
            onTransferClick = { destination = MainDestination.Transfer },
            onAllAccountsClick = { destination = MainDestination.Pending("전체계좌 조회") },
            onYouthClubClick = { destination = MainDestination.Pending("KB youth club") },
            onBottomMenuClick = { title ->
                destination = when (title) {
                    "지갑" -> MainDestination.Wallet
                    "혜택" -> MainDestination.Benefits
                    "지출" -> MainDestination.Expense
                    else -> MainDestination.Pending(title)
                }
            }
        )

        MainDestination.Menu -> MenuScreen(
            onClose = { destination = MainDestination.Home },
            onItemClick = { sectionTitle, item ->
                destination = when {
                    sectionTitle == "이체/출금" && item.title == "이체" -> MainDestination.Transfer
                    sectionTitle == "이체/출금" && item.title == "자동이체" -> MainDestination.AutoTransfer
                    sectionTitle == "이체/출금" && item.title == "이체관리" -> MainDestination.TransferManagement
                    sectionTitle == "공과금" && item.title == "공과금 납부/조회" -> MainDestination.UtilityBills
                    sectionTitle == "자산관리" && item.title == "지출" -> MainDestination.Expense
                    sectionTitle == "자산관리" && item.title == "마이데이터 설정" -> MainDestination.MyDataSettings
                    sectionTitle == "외환" && item.title == "환율" -> MainDestination.ExchangeRate
                    sectionTitle == "외환" && item.title == "환전" -> MainDestination.CurrencyExchange
                    sectionTitle == "외환" && item.title == "해외송금" -> MainDestination.OverseasRemittance
                    sectionTitle == "외환" && item.title == "국내외화 이체/입출금" -> MainDestination.ForeignCurrencyTransfer
                    sectionTitle == "외환" && item.title == "외환정보 관리" -> MainDestination.ForeignInfoManagement
                    sectionTitle == "혜택" && item.title == "이벤트" -> MainDestination.EventList(returnToMenu = true)
                    sectionTitle == "지갑" && item.title == "모바일 신분증" -> MainDestination.MobileId(returnToWallet = false)
                    sectionTitle == "지갑" && item.title == "결제" -> MainDestination.Payment(returnToWallet = false)
                    sectionTitle == "지갑" && item.title == "NFT" -> MainDestination.NftWallet(returnToWallet = false)
                    sectionTitle == "지갑" && (item.title == "공공알리미" || item.title == "공공알리미(국민비서 · 전자문서)") -> MainDestination.PublicNotifier(returnToWallet = false)
                    else -> MainDestination.Pending(item.title)
                }
            }
        )

        MainDestination.Wallet -> WalletScreen(
            onClose = { destination = MainDestination.Home },
            onItemClick = { title ->
                destination = if (title == "모바일 신분증") {
                    MainDestination.MobileId(returnToWallet = true)
                } else if (title == "결제") {
                    MainDestination.Payment(returnToWallet = true)
                } else if (title == "NFT") {
                    MainDestination.NftWallet(returnToWallet = true)
                } else if (title == "공공알리미") {
                    MainDestination.PublicNotifier(returnToWallet = true)
                } else {
                    MainDestination.Pending(title)
                }
            }
        )

        MainDestination.Benefits -> BenefitsScreen(
            onBackClick = { destination = MainDestination.Home },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu },
            onItemClick = { title ->
                destination = if (title == "이벤트") {
                    MainDestination.EventList(returnToMenu = false)
                } else {
                    MainDestination.Pending(title)
                }
            }
        )

        MainDestination.Transfer -> TransferScreen(
            onBackClick = { destination = MainDestination.Menu },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        MainDestination.AutoTransfer -> AutoTransferScreen(
            onBackClick = { destination = MainDestination.Menu },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        MainDestination.TransferManagement -> TransferManagementScreen(
            onBackClick = { destination = MainDestination.Menu },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        MainDestination.UtilityBills -> UtilityBillsScreen(
            onBackClick = { destination = MainDestination.Menu },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        MainDestination.Expense -> ExpenseScreen(
            onBackClick = { destination = MainDestination.Home },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        MainDestination.MyDataSettings -> MyDataSettingsScreen(
            onBackClick = { destination = MainDestination.Menu },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        MainDestination.ExchangeRate -> ExchangeRateScreen(
            onBackClick = { destination = MainDestination.Menu },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        MainDestination.CurrencyExchange -> ForeignMenuScreen(
            title = "환전",
            items = listOf("환전신청", "환전조회/관리", "비로그인 환전 내역 조회"),
            onBackClick = { destination = MainDestination.Menu },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        MainDestination.OverseasRemittance -> ForeignMenuScreen(
            title = "해외송금",
            items = listOf(
                "해외송금보내기",
                "웨스턴유니온송금보내기",
                "보낸송금 내용변경/반환신청",
                "보낸내역조회",
                "무증빙 해외송금 내역조회",
                "해외송금받기",
                "거래외국환은행지정"
            ),
            onBackClick = { destination = MainDestination.Menu },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        MainDestination.ForeignCurrencyTransfer -> ForeignMenuScreen(
            title = "국내외화이체/예금입출금",
            items = listOf("외화이체/예금입출금", "외화자동이체", "외화이체 내역조회"),
            onBackClick = { destination = MainDestination.Menu },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        MainDestination.ForeignInfoManagement -> ForeignMenuScreen(
            title = "외환정보관리",
            items = listOf(
                "영문정보관리",
                "외화알림서비스",
                "외화송금 국내/해외 주소록",
                "해외송금 수수료 납부/조회",
                "외화수표 수수료 납부/조회"
            ),
            onBackClick = { destination = MainDestination.Menu },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        is MainDestination.EventList -> EventListScreen(
            onBackClick = {
                destination = if (current.returnToMenu) {
                    MainDestination.Menu
                } else {
                    MainDestination.Benefits
                }
            },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        is MainDestination.MobileId -> MobileIdScreen(
            onBackClick = {
                destination = if (current.returnToWallet) {
                    MainDestination.Wallet
                } else {
                    MainDestination.Menu
                }
            },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        is MainDestination.Payment -> PaymentScreen(
            onBackClick = {
                destination = if (current.returnToWallet) {
                    MainDestination.Wallet
                } else {
                    MainDestination.Menu
                }
            },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        is MainDestination.NftWallet -> NftWalletScreen(
            onBackClick = {
                destination = if (current.returnToWallet) {
                    MainDestination.Wallet
                } else {
                    MainDestination.Menu
                }
            },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        is MainDestination.PublicNotifier -> PublicNotifierScreen(
            onBackClick = {
                destination = if (current.returnToWallet) {
                    MainDestination.Wallet
                } else {
                    MainDestination.Menu
                }
            },
            onHomeClick = { destination = MainDestination.Home },
            onMenuClick = { destination = MainDestination.Menu }
        )

        is MainDestination.Pending -> PlaceholderScreen(
            title = current.title,
            message = "${current.title} 화면은 나중에 구현하면 됩니다.",
            buttonText = "홈으로 돌아가기",
            onButtonClick = { destination = MainDestination.Home }
        )
    }
}

@Composable
private fun TransferScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val textColor = Color(0xFF24272D)
    val mutedColor = Color(0xFF7E838A)
    val lineColor = Color(0xFFB69B64)

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {}

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(top = 22.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackIcon(
                    modifier = Modifier
                        .size(31.dp)
                        .clickable(onClick = onBackClick),
                    color = textColor
                )
                Spacer(Modifier.width(14.dp))
                Text("이체", color = textColor, fontSize = 22.sp, fontWeight = FontWeight.Normal)
                Spacer(Modifier.weight(1f))
                HomeLineIcon(
                    modifier = Modifier
                        .size(34.dp)
                        .clickable(onClick = onHomeClick),
                    color = textColor
                )
                Spacer(Modifier.width(24.dp))
                MenuLineIcon(
                    modifier = Modifier
                        .size(34.dp)
                        .clickable(onClick = onMenuClick),
                    color = textColor
                )
            }

            Spacer(Modifier.height(64.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 27.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "누구에게 보낼까요?",
                    color = textColor,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Light,
                    letterSpacing = (-1).sp
                )
                Spacer(Modifier.weight(1f))
                Surface(
                    modifier = Modifier.width(128.dp),
                    shape = RoundedCornerShape(28.dp),
                    color = Color.White,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC9CDD2))
                ) {
                    Box(
                        modifier = Modifier.height(43.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "여러 건 이체",
                            color = Color(0xFF3A3D42),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(Modifier.height(48.dp))

            Column(modifier = Modifier.padding(horizontal = 27.dp)) {
                Text(
                    text = "계좌번호",
                    color = Color(0xFF9DA1A6),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(22.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(lineColor)
                )
                Spacer(Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CameraLineIcon(Modifier.size(26.dp), color = Color(0xFF555B63))
                    Spacer(Modifier.width(10.dp))
                    Text("촬영이체", color = Color(0xFF3A3D42), fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    Spacer(Modifier.width(18.dp))
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(24.dp)
                            .background(Color(0xFFDDE1E6))
                    )
                    Spacer(Modifier.width(18.dp))
                    ContactLineIcon(Modifier.size(27.dp), color = Color(0xFF555B63))
                    Spacer(Modifier.width(9.dp))
                    Text("연락처이체", color = Color(0xFF3A3D42), fontSize = 18.sp, fontWeight = FontWeight.Medium)
                }
            }
        }

        TransferBottomSheet(
            modifier = Modifier.align(Alignment.BottomCenter),
            textColor = textColor,
            mutedColor = mutedColor
        )
    }
}

@Composable
private fun TransferBottomSheet(
    modifier: Modifier = Modifier,
    textColor: Color,
    mutedColor: Color
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(390.dp),
        shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp),
        color = Color.White,
        shadowElevation = 18.dp
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .padding(start = 27.dp, end = 24.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                TransferTab("최근", selected = true)
                TransferTab("자주쓰는")
                TransferTab("빠른")
                TransferTab("내계좌")
                Spacer(Modifier.weight(1f))
                SearchLineIcon(
                    modifier = Modifier
                        .padding(bottom = 18.dp)
                        .size(34.dp),
                    color = textColor
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFFE0E3E7))
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ExclamationCircle(Modifier.size(82.dp), backgroundColor = Color(0xFFCCD1D6))
                Spacer(Modifier.height(28.dp))
                Text(
                    text = "최근 이체내역이 없습니다.",
                    color = textColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Composable
private fun TransferTab(
    title: String,
    selected: Boolean = false
) {
    Column(
        modifier = Modifier
            .height(66.dp)
            .padding(end = 25.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = title,
            color = if (selected) Color(0xFF25272B) else Color(0xFF6D727A),
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .width(if (selected) 42.dp else 0.dp)
                .height(4.dp)
                .background(if (selected) Color(0xFF25272B) else Color.Transparent)
        )
    }
}

@Composable
private fun AutoTransferScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val textColor = Color(0xFF25272B)
    val mutedColor = Color(0xFF7B8088)
    val yellow = Color(0xFFFFD337)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.statusBars.asPaddingValues())
                    .padding(top = 22.dp, bottom = 86.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BackIcon(
                        modifier = Modifier
                            .size(31.dp)
                            .clickable(onClick = onBackClick),
                        color = textColor
                    )
                    Spacer(Modifier.width(14.dp))
                    Text("자동이체", color = textColor, fontSize = 21.sp, fontWeight = FontWeight.Normal)
                    Spacer(Modifier.weight(1f))
                    HomeLineIcon(
                        modifier = Modifier
                            .size(34.dp)
                            .clickable(onClick = onHomeClick),
                        color = textColor
                    )
                    Spacer(Modifier.width(24.dp))
                    MenuLineIcon(
                        modifier = Modifier
                            .size(34.dp)
                            .clickable(onClick = onMenuClick),
                        color = textColor
                    )
                }

                Spacer(Modifier.height(44.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 27.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MonthChip("지난달", selected = false)
                    Spacer(Modifier.width(12.dp))
                    MonthChip("이번달", selected = true)
                    Spacer(Modifier.weight(1f))
                    Text("자동이체 관리", color = mutedColor, fontSize = 17.sp)
                    Spacer(Modifier.width(6.dp))
                    Text("›", color = mutedColor, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 24.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    SpeechBubble("등록 조회·변경·해지 가능!")
                }

                Spacer(Modifier.height(54.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ExclamationCircle(Modifier.size(70.dp), backgroundColor = Color(0xFFCCD1D6))
                    Spacer(Modifier.height(24.dp))
                    Text(
                        text = "예정된 자동이체 내역이 없어요",
                        color = textColor,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(Modifier.height(11.dp))
                    Text(
                        text = "자동이체를 등록해보세요.",
                        color = mutedColor,
                        fontSize = 15.sp
                    )
                }

                Spacer(Modifier.height(48.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(78.dp)
                        .background(Color(0xFFF5F6F8))
                        .padding(horizontal = 27.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "자동이체 처리 내역",
                        color = textColor,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.weight(1f))
                    DownChevronIcon(Modifier.size(27.dp), color = textColor)
                }
            }

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(88.dp),
                color = yellow
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "등록하기",
                        color = textColor,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun TransferManagementScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val textColor = Color(0xFF25272B)
    val mutedColor = Color(0xFF757A82)
    val dividerColor = Color(0xFFE1E4E7)
    val sectionColor = Color(0xFFF4F5F6)

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            TransferStyleTopBar(
                title = "이체관리",
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                onMenuClick = onMenuClick
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(sectionColor)
                        .padding(horizontal = 27.dp, vertical = 30.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "나의 이체한도",
                            color = textColor,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.weight(1f))
                        Text("상세조회/변경", color = textColor, fontSize = 18.sp)
                        Spacer(Modifier.width(7.dp))
                        Text("›", color = Color(0xFF6F747B), fontSize = 30.sp)
                    }
                    Spacer(Modifier.height(22.dp))
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        color = Color.White,
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE3E6E9))
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 26.dp, vertical = 24.dp)) {
                            LimitRow("1일", "50,000,000 원")
                            Spacer(Modifier.height(28.dp))
                            LimitRow("1회", "20,000,000 원")
                        }
                    }
                }

                TransferManageSectionTitle("이체용 출금계좌 관리")
                TransferManageItem("출금계좌 등록/관리")
                TransferManageItem("출금계좌 등록방법 변경", trailing = "지점", trailingBlue = true)
                TransferManageItem("출금계좌 순서 변경")

                SectionGap()

                TransferManageSectionTitle("이체 설정")
                TransferManageItem("자주쓰는계좌 등록/관리", trailing = "미등록")
                TransferManageItem(
                    title = "빠른이체",
                    subtitle = "비밀번호, 보안매체, 전자서명 없이 이체 가능",
                    trailing = "미등록"
                )
                TransferManageItem(
                    title = "단축이체",
                    subtitle = "계좌번호, 금액, 통장표시 미리 등록",
                    trailing = "미등록"
                )
                TransferManageItem(
                    title = "오픈뱅킹 설정",
                    subtitle = "오픈뱅킹 자산 등록/해제 및 계좌 관리"
                )

                SectionGap()

                TransferManageSectionTitle("이체 제한/오류 해제")
                TransferManageItem("장기미사용 이체 제한 해제")
                TransferManageItem("전화승인비밀번호 오류 해제")

                SectionGap()

                TransferManageSectionTitle("잔액모으기")
                TransferManageItem("잔액모으기 예약")
                TransferManageItem("잔액모으기 예약관리")

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 27.dp, vertical = 26.dp),
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFF6F7F8)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 22.dp, vertical = 22.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("계좌관리", color = textColor, fontSize = 21.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(7.dp))
                            Text("계좌 비밀번호 변경 및 계좌 숨기기 설정", color = mutedColor, fontSize = 16.sp)
                        }
                        Text("›", color = Color(0xFF8D939B), fontSize = 30.sp)
                    }
                }

                Spacer(Modifier.height(32.dp))
            }
        }
    }
}

@Composable
private fun UtilityBillsScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val textColor = Color(0xFF25272B)
    val mutedColor = Color(0xFF7B8088)
    val sectionColor = Color(0xFFF5F6F7)

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            TransferStyleTopBar(
                title = "공과금 납부/조회",
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                onMenuClick = onMenuClick
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 27.dp, vertical = 16.dp)
                        .height(58.dp),
                    color = Color(0xFFE9ECEF),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Row {
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .height(58.dp),
                            color = Color.White,
                            shape = RoundedCornerShape(30.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDDE1E5))
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text("납부하기", color = textColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(58.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("납부 내역 조회", color = mutedColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(sectionColor)
                        .padding(horizontal = 27.dp, vertical = 34.dp)
                ) {
                    Text(
                        text = "회원 이름님, 납부할 세금을 확인해보세요",
                        color = textColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text("2026.05.26. 22:23 기준", color = Color(0xFF9AA0A7), fontSize = 18.sp)
                    Spacer(Modifier.height(26.dp))
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E5E8))
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp)) {
                            UtilityLookupRow("국세")
                            UtilityLookupRow("지방세")
                            Spacer(Modifier.height(14.dp))
                            DividerLine()
                            Spacer(Modifier.height(14.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("더보기", color = mutedColor, fontSize = 18.sp)
                                Spacer(Modifier.width(8.dp))
                                DownChevronIcon(Modifier.size(24.dp), color = mutedColor)
                            }
                        }
                    }
                    Spacer(Modifier.height(18.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ExclamationCircle(Modifier.size(25.dp), backgroundColor = Color(0xFF9AA0A7))
                        Spacer(Modifier.width(8.dp))
                        Text("주민등록번호 기준으로 조회되는 내역만 보여드려요.", color = mutedColor, fontSize = 16.sp)
                    }
                }

                UtilityGridSection(
                    title = "세금",
                    items = listOf(
                        "📄" to "국세",
                        "🧾" to "지방세",
                        "💵" to "관세",
                        "🏦" to "기금/국고",
                        "💰" to "세외수입",
                        "🌱" to "환경개선부담금",
                        "🚙" to "교통범칙금",
                        "⚖" to "검찰청벌과금",
                        "💼" to "4대보험료"
                    )
                )

                UtilityGridSection(
                    title = "생활공과금",
                    trailing = "자주쓰는 지로관리",
                    items = listOf(
                        "💳" to "지로",
                        "📱" to "KT통신",
                        "⚡" to "전기/TV",
                        "💧" to "상하수도",
                        "🏢" to "관리비",
                        "🎓" to "등록금"
                    )
                )

                Column(modifier = Modifier.padding(horizontal = 27.dp)) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        color = Color.White,
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF8E949B))
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("▣ 고지서 촬영으로 간편 납부", color = Color(0xFF3D4248), fontSize = 18.sp)
                        }
                    }
                    Spacer(Modifier.height(18.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        UtilityOutlineButton("자동납부", Modifier.weight(1f))
                        UtilityOutlineButton("예약납부", Modifier.weight(1f))
                    }
                    Spacer(Modifier.height(34.dp))
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(118.dp),
                        color = Color(0xFFEAF7F3),
                        shape = RoundedCornerShape(0.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 28.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("자동차세, 재산세 등", color = mutedColor, fontSize = 17.sp)
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    text = "지방세·세외수입 알림 받으세요",
                                    color = textColor,
                                    fontSize = 21.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text("TAX🔔", color = Color(0xFF35BFA5), fontSize = 26.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(Modifier.height(36.dp))
            }
        }
    }
}

@Composable
private fun UtilityLookupRow(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, color = Color(0xFF555A61), fontSize = 20.sp)
        Spacer(Modifier.weight(1f))
        Text("조회", color = Color(0xFF8A9098), fontSize = 18.sp)
        Spacer(Modifier.width(8.dp))
        Text("›", color = Color(0xFF555A61), fontSize = 28.sp)
    }
}

@Composable
private fun UtilityGridSection(
    title: String,
    trailing: String? = null,
    items: List<Pair<String, String>>
) {
    Column(modifier = Modifier.padding(horizontal = 27.dp, vertical = 28.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(title, color = Color(0xFF25272B), fontSize = 23.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            if (trailing != null) {
                Text(trailing, color = Color(0xFF7B8088), fontSize = 17.sp)
                Spacer(Modifier.width(6.dp))
                Text("›", color = Color(0xFF7B8088), fontSize = 27.sp)
            }
        }
        Spacer(Modifier.height(22.dp))
        val rows = items.chunked(2)
        rows.forEach { rowItems ->
            Row(modifier = Modifier.fillMaxWidth()) {
                rowItems.forEach { (icon, label) ->
                    UtilityGridItem(
                        icon = icon,
                        label = label,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (rowItems.size == 1) {
                    Spacer(Modifier.weight(1f))
                }
            }
            Spacer(Modifier.height(22.dp))
        }
    }
}

@Composable
private fun UtilityGridItem(
    icon: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFF5F6F8)),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, fontSize = 25.sp)
        }
        Spacer(Modifier.width(14.dp))
        Text(label, color = Color(0xFF25272B), fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun UtilityOutlineButton(text: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.height(58.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF8E949B))
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text, color = Color(0xFF25272B), fontSize = 21.sp)
        }
    }
}

@Composable
private fun ExpenseScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val textColor = Color(0xFF25272B)
    val mutedColor = Color(0xFF7B8088)

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            TransferStyleTopBar(
                title = "지출",
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                onMenuClick = onMenuClick
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFEFF2F4))
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color(0xFFF7F6E8), Color(0xFFF3E9A8), Color(0xFFEFF2F4))
                            )
                        )
                        .padding(start = 27.dp, end = 27.dp, top = 42.dp, bottom = 34.dp)
                ) {
                    Column {
                        Text("05월 나의 총 지출 ↻", color = textColor, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(10.dp))
                        Text("0원⌄", color = textColor, fontSize = 43.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(18.dp))
                        Text("지난달 보다 0원 더 쓰고 있어요.", color = textColor, fontSize = 20.sp)
                        Spacer(Modifier.height(42.dp))
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.White,
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Column(modifier = Modifier.padding(27.dp)) {
                                Text("디지털 PB가 분석했어요  ?", color = mutedColor, fontSize = 17.sp)
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    text = "이번 달 지출 1위는\n생활이에요",
                                    color = textColor,
                                    fontSize = 24.sp,
                                    lineHeight = 32.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(Modifier.height(28.dp))
                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = Color(0xFFF6F6F7),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.padding(24.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text("최근 지출", color = textColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                        Spacer(Modifier.height(26.dp))
                                        ExclamationCircle(Modifier.size(54.dp), backgroundColor = Color(0xFFCCD1D6))
                                        Spacer(Modifier.height(16.dp))
                                        Text("최근 지출 내역이 없어요", color = Color(0xFF7B8088), fontSize = 16.sp)
                                    }
                                }
                                Spacer(Modifier.height(28.dp))
                                Surface(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(64.dp),
                                    color = Color(0xFFFBE8D7),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text("지출내역 점검하기", color = Color(0xFF6B2E15), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                        Spacer(Modifier.height(20.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(Modifier.width(34.dp).height(8.dp).clip(RoundedCornerShape(5.dp)).background(Color(0xFF777D85)))
                            Spacer(Modifier.width(10.dp))
                            Box(Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF8D939B)))
                            Spacer(Modifier.width(10.dp))
                            Box(Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF8D939B)))
                        }
                    }
                }

                ExpenseBanner()
                ExpenseWeeklyReportCard()
                ExpenseAssetCard()
                ExpenseCardScheduleCard()
                ExpenseRegularCard()
                ExpenseUsefulFeaturesCard()

                Spacer(Modifier.height(36.dp))
            }
        }
    }
}

@Composable
private fun ExpenseRecentRow(
    title: String,
    date: String,
    accent: Color = Color(0xFF6FA3F3)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(accent),
            contentAlignment = Alignment.Center
        ) {
            Text("▣", color = Color.White, fontSize = 20.sp)
        }
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = Color(0xFF25272B), fontSize = 17.sp)
            Spacer(Modifier.height(4.dp))
            Text(date, color = Color(0xFF8A9098), fontSize = 15.sp)
        }
        Text("0원", color = Color(0xFF25272B), fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun ExpenseBanner() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 27.dp, vertical = 22.dp)
            .height(74.dp),
        color = Color.White,
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("💵", fontSize = 28.sp)
            Spacer(Modifier.width(14.dp))
            Text("월별 현금흐름 한눈에 확인하기!", color = Color(0xFF25272B), fontSize = 19.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Text("›", color = Color(0xFF8D939B), fontSize = 30.sp)
        }
    }
}

@Composable
private fun ExpenseWeeklyReportCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 27.dp, vertical = 10.dp)
            .height(318.dp),
        color = Color.White,
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(27.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("지난 주 소비리포트", color = Color(0xFF25272B), fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                    Text("전 주보다 0원 많이 썼어요", color = Color(0xFF7B8088), fontSize = 17.sp)
                }
                Text("›", color = Color(0xFF8D939B), fontSize = 32.sp)
            }
            Spacer(Modifier.height(34.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFD9DDE1))
                        .align(Alignment.Center)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                    ExpenseBar("05월 2주차", Color(0xFFE7EAED), 62.dp)
                    ExpenseBar("05월 3주차", Color(0xFFFF894F), 150.dp, selected = true)
                }
            }
        }
    }
}

@Composable
private fun ExpenseBar(label: String, color: Color, height: androidx.compose.ui.unit.Dp, selected: Boolean = false) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("0원", color = Color(0xFF25272B), fontSize = if (selected) 19.sp else 0.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .width(76.dp)
                .height(height)
                .clip(RoundedCornerShape(14.dp))
                .background(color)
        )
        Spacer(Modifier.height(14.dp))
        Text(label, color = if (selected) Color(0xFF25272B) else Color(0xFF8A9098), fontSize = 17.sp, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
    }
}

@Composable
private fun ExpenseAssetCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 27.dp, vertical = 10.dp),
        color = Color.White,
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(27.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("자산별 지출", color = Color(0xFF25272B), fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.weight(1f))
                Surface(color = Color(0xFFF0F1F2), shape = RoundedCornerShape(22.dp)) {
                    Text("+ 자산 추가", modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), color = Color(0xFF4A4F55), fontSize = 16.sp)
                }
            }
            Spacer(Modifier.height(26.dp))
            ExpenseAssetRow("💳", "카드", "0원")
            ExpenseAssetRow("🐷", "계좌", "연결하기")
            ExpenseAssetRow("₩", "간편결제", "연결하기")
            ExpenseAssetRow("📱", "휴대폰 소액결제", "연결하기")
            Spacer(Modifier.height(14.dp))
            Surface(modifier = Modifier.fillMaxWidth(), color = Color(0xFFFFF4CF), shape = RoundedCornerShape(10.dp)) {
                Row(modifier = Modifier.padding(horizontal = 18.dp, vertical = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("💰", fontSize = 24.sp)
                    Spacer(Modifier.width(10.dp))
                    Text("분류가 필요한 지출 0건", color = Color(0xFF25272B), fontSize = 17.sp)
                    Spacer(Modifier.weight(1f))
                    Text("›", color = Color(0xFF8D939B), fontSize = 30.sp)
                }
            }
        }
    }
}

@Composable
private fun ExpenseAssetRow(icon: String, title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(Color(0xFFEFF2F4)), contentAlignment = Alignment.Center) {
            Text(icon, fontSize = 19.sp)
        }
        Spacer(Modifier.width(14.dp))
        Text(title, color = Color(0xFF25272B), fontSize = 19.sp)
        Spacer(Modifier.weight(1f))
        Text(value, color = Color(0xFF25272B), fontSize = 19.sp, fontWeight = if (value == "0원") FontWeight.Bold else FontWeight.Normal)
        Spacer(Modifier.width(8.dp))
        Text("›", color = Color(0xFF8D939B), fontSize = 27.sp)
    }
}

@Composable
private fun ExpenseCardScheduleCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 27.dp, vertical = 10.dp)
            .height(300.dp),
        color = Color.White,
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(27.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text("카드 결제예정", color = Color(0xFF25272B), fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.weight(1f))
                Text("0원", color = Color(0xFF25272B), fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(6.dp))
                Text("›", color = Color(0xFF8D939B), fontSize = 30.sp)
            }
            Spacer(Modifier.height(34.dp))
            Box(modifier = Modifier.size(width = 205.dp, height = 125.dp).clip(RoundedCornerShape(10.dp)).background(Color(0xFFADB3BA)), contentAlignment = Alignment.Center) {
                Text("💳", fontSize = 36.sp)
            }
            Spacer(Modifier.height(18.dp))
            Text("신용카드", color = Color(0xFF8A9098), fontSize = 18.sp)
            Text("연결하기", color = Color(0xFF25272B), fontSize = 21.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun ExpenseRegularCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 27.dp, vertical = 10.dp)
            .height(318.dp),
        color = Color.White,
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(27.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("이번 달 정기지출", color = Color(0xFF25272B), fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Text("총 0건이 남아있어요", color = Color(0xFF7B8088), fontSize = 17.sp)
                }
                Text("0원", color = Color(0xFF25272B), fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(6.dp))
                Text("›", color = Color(0xFF8D939B), fontSize = 30.sp)
            }
            Spacer(Modifier.height(32.dp))
            ExclamationCircle(Modifier.size(68.dp), backgroundColor = Color(0xFFCCD1D6))
            Spacer(Modifier.height(18.dp))
            Text("다가오는 지출이 없어요", color = Color(0xFF25272B), fontSize = 19.sp)
            Spacer(Modifier.height(14.dp))
            Text("자산 추가하고 다가오는 지출 찾기", color = Color(0xFF7B8088), fontSize = 17.sp)
        }
    }
}

@Composable
private fun ExpenseUsefulFeaturesCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 27.dp, vertical = 10.dp)
            .height(295.dp),
        color = Color.White,
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(27.dp)) {
            Text("지출관리에 유용한 기능", color = Color(0xFF25272B), fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(24.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                ExpenseFeatureBox("금융플러스", "다양한 실물자산부터\n신용관리까지", Color(0xFFE6F1FE), "▣", Modifier.weight(1f))
                ExpenseFeatureBox("목표챌린지", "내 예산에 맞춘 지출관리\n시작", Color(0xFFF1E8FA), "◎", Modifier.weight(1f))
            }
            Spacer(Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Box(Modifier.width(34.dp).height(8.dp).clip(RoundedCornerShape(5.dp)).background(Color(0xFF777D85)))
                Spacer(Modifier.width(10.dp))
                Box(Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF8D939B)))
                Spacer(Modifier.width(10.dp))
                Box(Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF8D939B)))
                Spacer(Modifier.width(10.dp))
                Box(Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF8D939B)))
            }
        }
    }
}

@Composable
private fun ExpenseFeatureBox(title: String, body: String, color: Color, icon: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(160.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(18.dp)
    ) {
        Column {
            Text(title, color = Color(0xFF25272B), fontSize = 19.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Text(body, color = Color(0xFF7B8088), fontSize = 15.sp, lineHeight = 22.sp)
        }
        Text(icon, modifier = Modifier.align(Alignment.BottomEnd), color = Color(0xFF6D91E8), fontSize = 30.sp)
    }
}

@Composable
private fun MyDataSettingsScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val textColor = Color(0xFF25272B)
    val mutedColor = Color(0xFF7B8088)

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            TransferStyleTopBar(
                title = "마이데이터 설정",
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                onMenuClick = onMenuClick
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 27.dp)
            ) {
                Spacer(Modifier.height(28.dp))
                Text("연결된 기관", color = textColor, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(32.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(74.dp),
                    color = Color(0xFFF5F5F6),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("카드 ", color = mutedColor, fontSize = 22.sp)
                        Text("(1)", color = Color(0xFF2D8CFF), fontSize = 22.sp)
                        Spacer(Modifier.weight(1f))
                        DownChevronIcon(Modifier.size(30.dp), color = Color(0xFF5C626A))
                    }
                }

                Spacer(Modifier.height(68.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    MyDataOutlineButton("다른 자산 추가하기", Modifier.weight(1f))
                    MyDataOutlineButton("동의내용 변경하기", Modifier.weight(1f))
                }

                Spacer(Modifier.height(44.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("⚙", color = Color(0xFF5C626A), fontSize = 30.sp)
                    Spacer(Modifier.width(12.dp))
                    Text("마이데이터 관리", color = textColor, fontSize = 22.sp)
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "개인정보처리방침",
                        color = Color(0xFF666B72),
                        fontSize = 18.sp,
                        textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
                    )
                }
            }
        }
    }
}

@Composable
private fun MyDataOutlineButton(text: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.height(56.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF8E949B))
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text, color = Color(0xFF25272B), fontSize = 19.sp)
        }
    }
}

@Composable
private fun EventListScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val textColor = Color(0xFF25272B)
    val mutedColor = Color(0xFF7B8088)

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            TransferStyleTopBar(
                title = "이벤트",
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                onMenuClick = onMenuClick
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 27.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                EventTopTab("진행중이벤트", selected = true)
                EventTopTab("응모/당첨확인")
                EventTopTab("설문")
                Spacer(Modifier.weight(1f))
                SearchLineIcon(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(35.dp),
                    color = textColor
                )
            }
            DividerLine()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 27.dp)
            ) {
                Spacer(Modifier.height(22.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    EventFilterChip("전체", selected = true)
                    EventFilterChip("마감임박")
                    EventFilterChip("맞춤추천")
                }
                Spacer(Modifier.height(24.dp))

                EventCard(
                    title = "KB GS Pay통장 만들고",
                    subtitle = "스페셜 카드박스 응모하기",
                    period = "2026.05.25 ~ 2026.05.31",
                    background = Color(0xFFFCEBEE),
                    image = "🎁🐰"
                )
                EventCard(
                    title = "치킨도 먹고! 커피도 마시고!",
                    subtitle = "KB골든라이프와 함께",
                    period = "2026.05.15 ~ 2026.06.15",
                    background = Color(0xFFFFF4D9),
                    image = "🍗👵"
                )
                EventCard(
                    title = "하루 3번 받는 포인트",
                    subtitle = "매일걷기로 건강 앱테크",
                    period = "2026.05.13 ~ 2026.06.30",
                    background = Color(0xFFE0F3FF),
                    image = "🐻🪙"
                )
                EventCard(
                    title = "ISA로 노후준비 단디하고,",
                    subtitle = "부산 Golden Class 로 퍼뜩 오이소!",
                    period = "2026.05.12 ~ 2026.05.29",
                    background = Color(0xFFE3F5FF),
                    image = "👩‍💼💰"
                )
                EventCard(
                    title = "자취지원금 총 2천만원 쏜다",
                    subtitle = "20대라면 누구나 OK!",
                    period = "2026.05.12 ~ 2026.06.30",
                    background = Color(0xFFF0EFFF),
                    image = "🐱💸"
                )
                Spacer(Modifier.height(30.dp))
            }
        }
    }
}

@Composable
private fun EventTopTab(title: String, selected: Boolean = false) {
    Column(
        modifier = Modifier
            .height(64.dp)
            .padding(end = 28.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = title,
            color = if (selected) Color(0xFF25272B) else Color(0xFF7B8088),
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(14.dp))
        Box(
            modifier = Modifier
                .width(if (selected) 105.dp else 0.dp)
                .height(4.dp)
                .background(if (selected) Color(0xFF25272B) else Color.Transparent)
        )
    }
}

@Composable
private fun EventFilterChip(title: String, selected: Boolean = false) {
    Surface(
        modifier = Modifier.height(48.dp),
        color = if (selected) Color(0xFFFFD95A) else Color.White,
        shape = RoundedCornerShape(24.dp),
        border = if (selected) null else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF9BA1A8))
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 21.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = if (selected) Color(0xFF25272B) else Color(0xFF8C9299),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun EventCard(
    title: String,
    subtitle: String,
    period: String,
    background: Color,
    image: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(142.dp)
            .padding(bottom = 16.dp),
        color = background,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 22.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = Color(0xFF25272B), fontSize = 21.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(5.dp))
                Text(subtitle, color = Color(0xFF3E4248), fontSize = 18.sp)
                Spacer(Modifier.height(18.dp))
                Text(period, color = Color(0xFF6F747B), fontSize = 17.sp)
            }
            Box(
                modifier = Modifier
                    .size(94.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.55f)),
                contentAlignment = Alignment.Center
            ) {
                Text(image, fontSize = 32.sp)
            }
        }
    }
}

@Composable
private fun MobileIdScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val textColor = Color(0xFF25272B)

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            TransferStyleTopBar(
                title = "모바일 신분증",
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                onMenuClick = onMenuClick
            )
            DividerLine()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(76.dp)
                    .background(Color.White)
                    .padding(horizontal = 27.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Surface(
                    shape = RoundedCornerShape(26.dp),
                    color = Color.White,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC9CDD2))
                ) {
                    Text(
                        text = "국민지갑 메인",
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                        color = Color(0xFF3A3D42),
                        fontSize = 18.sp
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFFEAF6FF))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 70.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "신분증이 필요한 순간\n모바일 신분증 하나로",
                        color = textColor,
                        fontSize = 31.sp,
                        lineHeight = 42.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(22.dp))
                    Text(
                        text = "안전하고 간편한 모바일 신분증\n바로 발급해보세요",
                        color = Color(0xFF555A61),
                        fontSize = 21.sp,
                        lineHeight = 30.sp
                    )
                    Spacer(Modifier.height(56.dp))
                    MobileIdHero()
                }

                Surface(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(92.dp),
                    color = Color(0xFF343C61)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "모바일 신분증 이용하기",
                            color = Color.White,
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MobileIdHero() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(430.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .width(270.dp)
                .height(380.dp),
            color = Color.Transparent,
            shape = RoundedCornerShape(24.dp)
        ) {
            Box(
                modifier = Modifier.background(
                    Brush.linearGradient(
                        listOf(Color(0xFF8FD9E9), Color(0xFFB89DF5), Color(0xFFE7F4FF))
                    )
                )
            )
        }
        MobileSideIdCard(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 0.dp),
            rotationHint = -9f,
            color = Color(0xFFFFF4C4),
            label = "주민등록증"
        )
        MobileSideIdCard(
            modifier = Modifier.align(Alignment.CenterEnd),
            rotationHint = 9f,
            color = Color(0xFFEAF7F3),
            label = "국가보훈등록증"
        )
        Surface(
            modifier = Modifier
                .width(246.dp)
                .height(315.dp),
            color = Color(0xFFEFF8FF),
            shape = RoundedCornerShape(18.dp),
            shadowElevation = 8.dp
        ) {
            Column(modifier = Modifier.padding(18.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(color = Color.White, shape = RoundedCornerShape(22.dp), shadowElevation = 5.dp) {
                    Text(
                        "눌러서 정보 확인",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        color = Color(0xFF25272B),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .size(92.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🐰", fontSize = 45.sp)
                }
                Spacer(Modifier.height(22.dp))
                Text("자동차운전면허증", color = Color(0xFF25272B), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(10.dp))
                Text("김국민", color = Color(0xFF25272B), fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(4.dp))
                Text("123456-1234567", color = Color(0xFF25272B), fontSize = 17.sp)
                Spacer(Modifier.height(18.dp))
                Text("서울특별시경찰청장", color = Color(0xFF555A61), fontSize = 14.sp)
            }
        }
    }
}

@Composable
private fun MobileSideIdCard(
    modifier: Modifier,
    rotationHint: Float,
    color: Color,
    label: String
) {
    Surface(
        modifier = modifier
            .width(128.dp)
            .height(220.dp),
        color = color,
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(label, color = Color(0xFF25272B), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(18.dp))
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(if (rotationHint < 0) "🐢" else "🐻", fontSize = 34.sp)
            }
            Spacer(Modifier.height(22.dp))
            Text("김국민", color = Color(0xFF25272B), fontSize = 17.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun NftWalletScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val textColor = Color(0xFF25272B)
    val mutedColor = Color(0xFF666B72)

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            TransferStyleTopBar(
                title = "NFT 지갑",
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                onMenuClick = onMenuClick
            )
            DividerLine()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(88.dp)
                        .background(Color(0xFFEAF7FF))
                        .padding(horizontal = 28.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Surface(
                        color = Color.White,
                        shape = RoundedCornerShape(26.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC6CDD5))
                    ) {
                        Text(
                            text = "국민지갑 메인",
                            modifier = Modifier.padding(horizontal = 22.dp, vertical = 10.dp),
                            color = textColor,
                            fontSize = 18.sp
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color(0xFFEAF7FF),
                                    Color(0xFFEFE7FF),
                                    Color(0xFFEDE2FF)
                                )
                            )
                        )
                        .padding(horizontal = 28.dp, vertical = 26.dp)
                ) {
                    Text("나만의 NFT 컬렉션", color = textColor, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(18.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = Color(0xFFDDE8F8),
                            shape = RoundedCornerShape(24.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 9.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("물색설경운치4818", color = Color(0xFF40454C), fontSize = 18.sp)
                                Spacer(Modifier.width(8.dp))
                                Text("›", color = Color(0xFF40454C), fontSize = 28.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        Spacer(Modifier.weight(1f))
                        Text("전체 보기 ", color = mutedColor, fontSize = 18.sp)
                        Text("0", color = Color(0xFF2D83FF), fontSize = 18.sp)
                        Spacer(Modifier.width(6.dp))
                        Text("›", color = mutedColor, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(Modifier.height(122.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFD7C8F6)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("!", color = Color(0xFFB8A9DC), fontSize = 34.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(Modifier.height(42.dp))
                        Text("보유한 NFT가 없어요.", color = textColor, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(Modifier.height(86.dp))

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFFD8C6FF),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 28.dp, vertical = 24.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("NFT는 어떻게 모으나요?", color = textColor, fontSize = 18.sp)
                                Spacer(Modifier.height(6.dp))
                                Text("NFT 소장 방법 알아보기", color = textColor, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                            }
                            NftSmallBadge()
                        }
                    }

                    Spacer(Modifier.height(34.dp))
                    Text(
                        text = "거래 내역",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Color(0xFF5F646B),
                        fontSize = 22.sp
                    )
                    Spacer(Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(78.dp)
                            .height(1.dp)
                            .background(Color(0xFF5F646B))
                    )
                    Spacer(Modifier.height(30.dp))
                }

                NftGuideSection(
                    title = "NFT 지갑 이벤트 안내",
                    rows = listOf("🎉" to "안녕 나는 웰컴이야~!!")
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(18.dp)
                        .background(Color(0xFFF4F5F6))
                )
                NftGuideSection(
                    title = "NFT 지갑 이용 안내",
                    rows = listOf(
                        "ⓘ" to "NFT 지갑 설명서",
                        "▣" to "이전 New Fun Contents 보러가기"
                    )
                )
                Spacer(Modifier.height(36.dp))
            }
        }
    }
}

@Composable
private fun NftSmallBadge() {
    Surface(
        modifier = Modifier.size(74.dp),
        color = Color.White,
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 2.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("NFT", color = Color(0xFF7C57E8), fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Text("✓", color = Color(0xFFD2A327), fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun NftGuideSection(
    title: String,
    rows: List<Pair<String, String>>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 28.dp, vertical = 34.dp)
    ) {
        Text(title, color = Color(0xFF25272B), fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(28.dp))
        rows.forEachIndexed { index, row ->
            NftGuideRow(icon = row.first, title = row.second)
            if (index != rows.lastIndex) {
                Spacer(Modifier.height(20.dp))
                DividerLine()
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun NftGuideRow(
    icon: String,
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(icon, fontSize = 27.sp)
        Spacer(Modifier.width(24.dp))
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = Color(0xFF25272B),
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )
        Text("›", color = Color(0xFF444A52), fontSize = 32.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun PublicNotifierScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val textColor = Color(0xFF25272B)
    val mutedColor = Color(0xFF858B93)

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            TransferStyleTopBar(
                title = "공공알리미",
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                onMenuClick = onMenuClick
            )
            DividerLine()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(Modifier.height(28.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    color = Color(0xFFEAF3FF),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 22.dp, vertical = 24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("한국주택금융공사를 만나보세요", color = textColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(8.dp))
                            Text(
                                "우리가족 소중한 보금자리, 내 집 마련 꿈 실현하기",
                                color = Color(0xFF565B63),
                                fontSize = 17.sp,
                                lineHeight = 23.sp
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Box(
                            modifier = Modifier
                                .size(66.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("HF", color = Color(0xFF2E8DDC), fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Spacer(Modifier.height(14.dp))
                Text(
                    text = "• ━ ❚❚",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color(0xFF7F858C),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(26.dp))
                PublicNotifierTabs(selected = "받은 문서")

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF7F1E6))
                        .padding(horizontal = 24.dp, vertical = 26.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("지금 바로 확인해야할 중요 알림", color = Color(0xFF363A40), fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(6.dp))
                            Text("아직 읽지 않은 알림이 있어요", color = Color(0xFF676D75), fontSize = 17.sp)
                        }
                        Text("📅", fontSize = 44.sp)
                    }
                    Spacer(Modifier.height(22.dp))
                    listOf(
                        "[관세청] 전자상거래(해외직구)물품 수입...",
                        "[관세청] 전자상거래(해외직구)물품 통관...",
                        "[신규서비스 안내(고유가 피해지원금 안..."
                    ).forEach {
                        PublicImportantNotice(title = it)
                        Spacer(Modifier.height(10.dp))
                    }
                }

                PublicReceivedDocuments()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(18.dp)
                        .background(Color(0xFFF5F6F7))
                )

                PublicNotifierTabs(selected = "신청/관리")
                PublicApplyManagement()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(18.dp)
                        .background(Color(0xFFF5F6F7))
                )

                PublicNotifierTabs(selected = "이용안내")
                PublicUsageGuide()
                Spacer(Modifier.height(36.dp))
            }
        }
    }
}

@Composable
private fun ExchangeRateScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    ForeignMenuScreen(
        title = "환율",
        items = listOf("환율조회", "환율동향정보"),
        onBackClick = onBackClick,
        onHomeClick = onHomeClick,
        onMenuClick = onMenuClick
    )
}

@Composable
private fun ForeignMenuScreen(
    title: String,
    items: List<String>,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val textColor = Color(0xFF25272B)
    val lineColor = Color(0xFFE6E9EC)

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            TransferStyleTopBar(
                title = title,
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                onMenuClick = onMenuClick
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 28.dp)
            ) {
                Spacer(Modifier.height(20.dp))
                items.forEach { item ->
                    ForeignMenuListRow(title = item, textColor = textColor, lineColor = lineColor)
                }
            }
        }
    }
}

@Composable
private fun ForeignMenuListRow(
    title: String,
    textColor: Color,
    lineColor: Color
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                color = textColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text("›", color = Color(0xFF858B93), fontSize = 34.sp, fontWeight = FontWeight.Light)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(lineColor)
        )
    }
}

@Composable
private fun PublicNotifierTabs(selected: String) {
    val tabs = listOf("받은 문서", "신청/관리", "이용안내")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEach { tab ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = tab,
                    color = if (tab == selected) Color(0xFF25272B) else Color(0xFF8B929A),
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .width(if (tab == selected) 74.dp else 0.dp)
                        .height(4.dp)
                        .background(if (tab == selected) Color(0xFF25272B) else Color.Transparent)
                )
            }
        }
        Text("⋮", color = Color(0xFF8B929A), fontSize = 34.sp, fontWeight = FontWeight.Bold)
    }
    DividerLine()
}

@Composable
private fun PublicImportantNotice(title: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp),
        color = Color.White,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("🇰🇷", fontSize = 27.sp)
            Spacer(Modifier.width(14.dp))
            Text(title, color = Color(0xFF343941), fontSize = 19.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun PublicReceivedDocuments() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 34.dp)
    ) {
        Text("받은 문서", color = Color(0xFF25272B), fontSize = 29.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(22.dp))
        PublicSegmentedControl(left = "최신순 보기", right = "기관별 보기")
        Spacer(Modifier.height(24.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("전체 21", color = Color(0xFF25272B), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("  |  안읽음 17", color = Color(0xFF535960), fontSize = 18.sp)
        }
        Spacer(Modifier.height(22.dp))
        listOf(
            Triple("KB국민은행 전자문서 중계서비스 개인정보처리...", "국민은행  |  26.05.19  |  D-346", "안읽음"),
            Triple("「전자금융서비스 이용약관」 개정관련 사전안내", "국민은행  |  26.05.13  |  D-219", "안읽음"),
            Triple("[관세청] 전자상거래(해외직구)물품 수입신고 내...", "국민비서 · 관세청  |  26.05.12", "안읽음"),
            Triple("[관세청] 전자상거래(해외직구)물품 통관목록 제...", "국민비서 · 관세청  |  26.05.12", "안읽음"),
            Triple("[신규서비스 안내(고유가 피해지원금 안내)]", "국민비서 · 행정안전부  |  26.05.08", "안읽음")
        ).forEach {
            PublicDocumentRow(title = it.first, subtitle = it.second, status = it.third)
            DividerLine()
        }
        Spacer(Modifier.height(22.dp))
        Text(
            "+ 더보기",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFF3E4248),
            fontSize = 22.sp
        )
    }
}

@Composable
private fun PublicSegmentedControl(left: String, right: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = Color(0xFFEFF2F5),
        shape = RoundedCornerShape(30.dp)
    ) {
        Row {
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                color = Color.White,
                shape = RoundedCornerShape(30.dp),
                shadowElevation = 2.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(left, color = Color(0xFF25272B), fontSize = 19.sp, fontWeight = FontWeight.Bold)
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(right, color = Color(0xFF8B929A), fontSize = 19.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun PublicDocumentRow(title: String, subtitle: String, status: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = Color(0xFF25272B), fontSize = 20.sp, fontWeight = FontWeight.Bold, lineHeight = 26.sp)
            Spacer(Modifier.height(8.dp))
            Text(subtitle, color = Color(0xFF858B93), fontSize = 17.sp)
        }
        Spacer(Modifier.width(10.dp))
        Text(status, color = Color(0xFF2B83FF), fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun PublicApplyManagement() {
    val cards = listOf(
        Triple("🇰🇷", "국민비서", "행정정보 공공알림\n수신중(65/109)"),
        Triple("HF", "한국주택금융공사", "보금자리론, 내집마련디딤돌 안내 등\n신청하기"),
        Triple("🇰🇷", "국세청", "근로장려금, 종합소득세,\n국세환급금 등"),
        Triple("TP", "사학연금", "대여 미상환금, 퇴직급여\n청구 안내 등"),
        Triple("🇰🇷", "지방자치단체", "지방세, 세외수입 등"),
        Triple("KB", "KB국민은행", "예금잔액 조회장, 상품가입\n계약서류 등"),
        Triple("KB", "KB국민카드", "이용대금명세서"),
        Triple("KB", "KB증권", "월간거래내역")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F7F8))
            .padding(horizontal = 24.dp, vertical = 28.dp)
    ) {
        Text("신청/관리", color = Color(0xFF25272B), fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(18.dp))
        Text("전체 12  |  신청완료 2  |  미신청 10", color = Color(0xFF3E4248), fontSize = 18.sp)
        Spacer(Modifier.height(20.dp))
        for (row in cards.chunked(2)) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                row.forEach { card ->
                    PublicApplyCard(
                        icon = card.first,
                        title = card.second,
                        body = card.third,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
private fun PublicApplyCard(
    icon: String,
    title: String,
    body: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(170.dp),
        color = Color.White,
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(icon, fontSize = 24.sp)
                Spacer(Modifier.width(10.dp))
                Text(title, color = Color(0xFF25272B), fontSize = 19.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(12.dp))
            Text(body, color = Color(0xFF737A82), fontSize = 16.sp, lineHeight = 23.sp)
            Spacer(Modifier.weight(1f))
            Text("신청하기 ›", modifier = Modifier.align(Alignment.End), color = Color(0xFF3D4249), fontSize = 17.sp)
        }
    }
}

@Composable
private fun PublicUsageGuide() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 34.dp)
    ) {
        Text("이용안내", color = Color(0xFF25272B), fontSize = 29.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(24.dp))
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFFF3F6FA),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF3B91F3)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("▤", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.width(18.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("KB공공알리미가 처음이신가요?", color = Color(0xFF25272B), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(4.dp))
                    Text("KB공공알리미 사용법 보러가기", color = Color(0xFF4A5058), fontSize = 17.sp)
                }
                Text("›", color = Color(0xFF8B929A), fontSize = 32.sp)
            }
        }
        Spacer(Modifier.height(48.dp))
        Text("자주 묻는 질문", color = Color(0xFF25272B), fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(22.dp))
        listOf(
            "공공기관의 알림이 왜 오는건가요?",
            "문서 도착 알림이 오지 않아요",
            "열람기한이 지나면 볼수 없나요?",
            "알림 내용에 대한 문의는 어디로 하나요?",
            "광고 스팸으로 의심되는 전자문서를 받았어요"
        ).forEach {
            DividerLine()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(it, modifier = Modifier.weight(1f), color = Color(0xFF3D4248), fontSize = 19.sp)
                Text("⌄", color = Color(0xFF858B93), fontSize = 27.sp)
            }
        }
        DividerLine()
    }
}

@Composable
private fun PaymentScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val textColor = Color(0xFF25272B)
    val mutedColor = Color(0xFF7B8088)

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFF4F5F6)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
        ) {
            TransferStyleTopBar(
                title = "스타뱅킹 결제",
                onBackClick = onBackClick,
                onHomeClick = onHomeClick,
                onMenuClick = onMenuClick
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 22.dp)
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 22.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(Modifier.weight(1f))
                            PaymentSegment("QR결제", selected = true)
                            PaymentSegment("제로페이", selected = false)
                            Spacer(Modifier.weight(1f))
                            Text("⋮", color = Color(0xFF5A6068), fontSize = 32.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(Modifier.height(22.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFF0F1F2)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("QR", color = Color(0xFFBEC4CA), fontSize = 38.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(Modifier.height(22.dp))
                        DividerLine()
                        Spacer(Modifier.height(22.dp))
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            color = Color.White,
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF7D838A))
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text("▣  QR 코드 스캔", color = textColor, fontSize = 20.sp)
                            }
                        }
                        Spacer(Modifier.height(28.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("출금계좌", color = textColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.weight(1f))
                            Text("🎫", fontSize = 22.sp)
                            Spacer(Modifier.width(6.dp))
                            Text("쿠폰함", color = textColor, fontSize = 18.sp)
                        }
                        Spacer(Modifier.height(12.dp))
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.White,
                            shape = RoundedCornerShape(14.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFD1D5DA))
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)
                                        .clip(RoundedCornerShape(7.dp))
                                        .background(Color(0xFFF0F1F2))
                                )
                                Spacer(Modifier.height(18.dp))
                                DividerLine()
                                Spacer(Modifier.height(18.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .width(88.dp)
                                            .height(32.dp)
                                            .clip(RoundedCornerShape(7.dp))
                                            .background(Color(0xFFF0F1F2))
                                    )
                                    Spacer(Modifier.width(14.dp))
                                    Surface(
                                        color = Color.White,
                                        shape = RoundedCornerShape(6.dp),
                                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFB9BEC5))
                                    ) {
                                        Text(
                                            "보기",
                                            modifier = Modifier.padding(horizontal = 18.dp, vertical = 6.dp),
                                            color = textColor,
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(Modifier.height(24.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("스타포인트", color = textColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.width(10.dp))
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(Color(0xFFF0F1F2))
                            )
                            Spacer(Modifier.weight(1f))
                            Box(
                                modifier = Modifier
                                    .width(128.dp)
                                    .height(34.dp)
                                    .clip(RoundedCornerShape(7.dp))
                                    .background(Color(0xFFF0F1F2))
                            )
                        }
                    }
                }

                PaymentMerchantCard()
                PaymentHistoryCard()
                Spacer(Modifier.height(32.dp))
            }
        }
    }
}

@Composable
private fun PaymentSegment(title: String, selected: Boolean) {
    Surface(
        modifier = Modifier.height(42.dp),
        color = if (selected) Color.White else Color(0xFFD6D9DC),
        shape = RoundedCornerShape(22.dp),
        border = if (selected) androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF9CA1A8)) else null
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 28.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(title, color = Color(0xFF25272B), fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun PaymentMerchantCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp),
        color = Color.White,
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text("🏪", fontSize = 25.sp)
                Spacer(Modifier.width(10.dp))
                Text("결제 가능 가맹점", color = Color(0xFF25272B), fontSize = 21.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.weight(1f))
                Text("더보기 ›", color = Color(0xFF6F747B), fontSize = 18.sp)
            }
            Spacer(Modifier.height(28.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                listOf("🛒", "🛍", "☕", "🍽").forEach {
                    Box(
                        modifier = Modifier
                            .size(58.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF0F1F2)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(it, fontSize = 26.sp)
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            Text("모든 편의점 등 전국 가맹점에서", color = Color(0xFF3E4248), fontSize = 20.sp)
            Spacer(Modifier.height(5.dp))
            Text("결제 가능합니다.", color = Color(0xFF3E4248), fontSize = 20.sp)
        }
    }
}

@Composable
private fun PaymentHistoryCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp),
        color = Color.White,
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("₩", color = Color(0xFF2B8FEA), fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(12.dp))
            Text("결제 내역", color = Color(0xFF25272B), fontSize = 21.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Text("전체보기 ›", color = Color(0xFF6F747B), fontSize = 18.sp)
        }
    }
}

@Composable
private fun TransferStyleTopBar(
    title: String,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val textColor = Color(0xFF25272B)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackIcon(
            modifier = Modifier
                .size(31.dp)
                .clickable(onClick = onBackClick),
            color = textColor
        )
        Spacer(Modifier.width(14.dp))
        Text(title, color = textColor, fontSize = 22.sp, fontWeight = FontWeight.Normal)
        Spacer(Modifier.weight(1f))
        HomeLineIcon(
            modifier = Modifier
                .size(34.dp)
                .clickable(onClick = onHomeClick),
            color = textColor
        )
        Spacer(Modifier.width(24.dp))
        MenuLineIcon(
            modifier = Modifier
                .size(34.dp)
                .clickable(onClick = onMenuClick),
            color = textColor
        )
    }
}

@Composable
private fun LimitRow(label: String, amount: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = Color(0xFF555A61), fontSize = 20.sp)
        Spacer(Modifier.weight(1f))
        Text(amount, color = Color(0xFF25272B), fontSize = 22.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun TransferManageSectionTitle(title: String) {
    Column(modifier = Modifier.padding(horizontal = 27.dp)) {
        Spacer(Modifier.height(30.dp))
        Text(title, color = Color(0xFF25272B), fontSize = 21.sp, fontWeight = FontWeight.Normal)
        Spacer(Modifier.height(24.dp))
        DividerLine()
    }
}

@Composable
private fun TransferManageItem(
    title: String,
    subtitle: String? = null,
    trailing: String? = null,
    trailingBlue: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 27.dp)
            .height(if (subtitle == null) 72.dp else 98.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = Color(0xFF25272B), fontSize = 20.sp, fontWeight = FontWeight.Bold)
            if (subtitle != null) {
                Spacer(Modifier.height(7.dp))
                Text(subtitle, color = Color(0xFF62676E), fontSize = 16.sp)
            }
        }
        if (trailing != null) {
            Text(
                trailing,
                color = if (trailingBlue) Color(0xFF2D8CFF) else Color(0xFF8D939B),
                fontSize = 18.sp
            )
            Spacer(Modifier.width(6.dp))
        }
        Text("›", color = Color(0xFF8D939B), fontSize = 29.sp)
    }
    Column(modifier = Modifier.padding(horizontal = 27.dp)) {
        DividerLine()
    }
}

@Composable
private fun DividerLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE1E4E7))
    )
}

@Composable
private fun SectionGap() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(16.dp)
            .background(Color(0xFFF1F3F4))
    )
}

@Composable
private fun MonthChip(
    title: String,
    selected: Boolean
) {
    Surface(
        modifier = Modifier.height(44.dp),
        color = if (selected) Color(0xFF4B4F55) else Color.White,
        shape = RoundedCornerShape(24.dp),
        border = if (selected) null else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF9BA1A8))
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 22.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = if (selected) Color.White else Color(0xFF8C9299),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun SpeechBubble(text: String) {
    Box {
        Canvas(
            modifier = Modifier
                .width(274.dp)
                .height(72.dp)
        ) {
            val stroke = Stroke(width = 2.2f, cap = StrokeCap.Round, join = StrokeJoin.Round)
            val bubbleTop = size.height * 0.22f
            val path = Path().apply {
                moveTo(size.width * 0.08f, bubbleTop)
                lineTo(size.width * 0.78f, bubbleTop)
                lineTo(size.width * 0.86f, 0f)
                lineTo(size.width * 0.94f, bubbleTop)
                quadraticTo(size.width, bubbleTop, size.width, bubbleTop + 16f)
                lineTo(size.width, size.height - 14f)
                quadraticTo(size.width, size.height, size.width - 14f, size.height)
                lineTo(14f, size.height)
                quadraticTo(0f, size.height, 0f, size.height - 14f)
                lineTo(0f, bubbleTop + 16f)
                quadraticTo(0f, bubbleTop, size.width * 0.08f, bubbleTop)
            }
            drawPath(path, Color.White)
            drawPath(path, Color(0xFFD0D5DA), style = stroke)
        }
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 13.dp),
            color = Color(0xFF2D8CFF),
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun BackIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 3.2f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        drawLine(color, Offset(size.width * 0.72f, size.height * 0.18f), Offset(size.width * 0.28f, size.height * 0.5f), strokeWidth = stroke.width, cap = StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.28f, size.height * 0.5f), Offset(size.width * 0.72f, size.height * 0.82f), strokeWidth = stroke.width, cap = StrokeCap.Round)
    }
}

@Composable
private fun HomeLineIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 3.6f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        val roof = Path().apply {
            moveTo(size.width * 0.16f, size.height * 0.48f)
            lineTo(size.width * 0.5f, size.height * 0.18f)
            lineTo(size.width * 0.84f, size.height * 0.48f)
        }
        drawPath(roof, color, style = stroke)
        val body = Path().apply {
            moveTo(size.width * 0.25f, size.height * 0.45f)
            lineTo(size.width * 0.25f, size.height * 0.82f)
            lineTo(size.width * 0.75f, size.height * 0.82f)
            lineTo(size.width * 0.75f, size.height * 0.45f)
        }
        drawPath(body, color, style = stroke)
        drawLine(color, Offset(size.width * 0.45f, size.height * 0.82f), Offset(size.width * 0.45f, size.height * 0.63f), strokeWidth = stroke.width, cap = StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.55f, size.height * 0.63f), Offset(size.width * 0.55f, size.height * 0.82f), strokeWidth = stroke.width, cap = StrokeCap.Round)
    }
}

@Composable
private fun MenuLineIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val strokeWidth = 3.6f
        drawLine(color, Offset(size.width * 0.18f, size.height * 0.28f), Offset(size.width * 0.82f, size.height * 0.28f), strokeWidth = strokeWidth, cap = StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.18f, size.height * 0.50f), Offset(size.width * 0.82f, size.height * 0.50f), strokeWidth = strokeWidth, cap = StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.18f, size.height * 0.72f), Offset(size.width * 0.82f, size.height * 0.72f), strokeWidth = strokeWidth, cap = StrokeCap.Round)
    }
}

@Composable
private fun SearchLineIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val strokeWidth = 3.4f
        drawCircle(color, radius = size.minDimension * 0.29f, center = Offset(size.width * 0.43f, size.height * 0.43f), style = Stroke(strokeWidth, cap = StrokeCap.Round))
        drawLine(color, Offset(size.width * 0.64f, size.height * 0.64f), Offset(size.width * 0.84f, size.height * 0.84f), strokeWidth = strokeWidth, cap = StrokeCap.Round)
    }
}

@Composable
private fun CameraLineIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 2.9f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        val body = androidx.compose.ui.geometry.Rect(size.width * 0.12f, size.height * 0.28f, size.width * 0.88f, size.height * 0.80f)
        drawRoundRect(color, topLeft = body.topLeft, size = Size(body.width, body.height), cornerRadius = androidx.compose.ui.geometry.CornerRadius(5f, 5f), style = stroke)
        drawLine(color, Offset(size.width * 0.32f, size.height * 0.28f), Offset(size.width * 0.38f, size.height * 0.17f), strokeWidth = stroke.width, cap = StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.38f, size.height * 0.17f), Offset(size.width * 0.62f, size.height * 0.17f), strokeWidth = stroke.width, cap = StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.62f, size.height * 0.17f), Offset(size.width * 0.68f, size.height * 0.28f), strokeWidth = stroke.width, cap = StrokeCap.Round)
        drawCircle(color, radius = size.minDimension * 0.16f, center = Offset(size.width * 0.5f, size.height * 0.55f), style = Stroke(stroke.width, cap = StrokeCap.Round))
    }
}

@Composable
private fun ContactLineIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val stroke = Stroke(width = 2.9f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        drawCircle(color, radius = size.minDimension * 0.14f, center = Offset(size.width * 0.44f, size.height * 0.27f), style = stroke)
        val shoulder = Path().apply {
            moveTo(size.width * 0.19f, size.height * 0.76f)
            cubicTo(size.width * 0.22f, size.height * 0.52f, size.width * 0.65f, size.height * 0.52f, size.width * 0.68f, size.height * 0.76f)
            lineTo(size.width * 0.19f, size.height * 0.76f)
        }
        drawPath(shoulder, color, style = stroke)
        drawLine(color, Offset(size.width * 0.68f, size.height * 0.47f), Offset(size.width * 0.86f, size.height * 0.47f), strokeWidth = stroke.width, cap = StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.86f, size.height * 0.47f), Offset(size.width * 0.78f, size.height * 0.38f), strokeWidth = stroke.width, cap = StrokeCap.Round)
        drawLine(color, Offset(size.width * 0.86f, size.height * 0.47f), Offset(size.width * 0.78f, size.height * 0.56f), strokeWidth = stroke.width, cap = StrokeCap.Round)
    }
}

@Composable
private fun ExclamationCircle(modifier: Modifier = Modifier, backgroundColor: Color) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text("!", color = Color.White, fontSize = 46.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DownChevronIcon(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        drawLine(
            color = color,
            start = Offset(size.width * 0.22f, size.height * 0.36f),
            end = Offset(size.width * 0.50f, size.height * 0.64f),
            strokeWidth = 3.4f,
            cap = StrokeCap.Round
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.50f, size.height * 0.64f),
            end = Offset(size.width * 0.78f, size.height * 0.36f),
            strokeWidth = 3.4f,
            cap = StrokeCap.Round
        )
    }
}

@Composable
private fun BenefitsScreen(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(horizontal = 24.dp, vertical = 22.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "<  혜택",
                    modifier = Modifier.clickable(onClick = onBackClick),
                    color = Color(0xFF25272B),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.weight(1f))
                Text("☺", color = Color(0xFF25272B), fontSize = 26.sp)
                Spacer(Modifier.width(20.dp))
                Text(
                    text = "⌂",
                    modifier = Modifier.clickable(onClick = onHomeClick),
                    color = Color(0xFF25272B),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(20.dp))
                Text(
                    text = "☰",
                    modifier = Modifier.clickable(onClick = onMenuClick),
                    color = Color(0xFF25272B),
                    fontSize = 32.sp
                )
            }

            Spacer(Modifier.height(44.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFF2C8)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("P", color = Color(0xFFFFB800), fontSize = 28.sp, fontWeight = FontWeight.Black)
                }
                Spacer(Modifier.width(14.dp))
                Text("스타포인트", color = Color(0xFF25272B), fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.weight(1f))
                Text("0P", color = Color(0xFF25272B), fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(8.dp))
                Text(">", color = Color(0xFF8D939B), fontSize = 30.sp)
            }

            Spacer(Modifier.height(38.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(158.dp),
                color = Color(0xFFCFF8CE),
                shape = RoundedCornerShape(18.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 22.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Surface(
                            color = Color(0xFFEFFFBE),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Text(
                                text = "👑 오늘의 혜택",
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                                color = Color(0xFF5A6E44),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(Modifier.height(18.dp))
                        Text(
                            text = "이벤트와 쿠폰을\n한번에 챙겨보세요",
                            color = Color(0xFF25272B),
                            fontSize = 24.sp,
                            lineHeight = 31.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text("🌳", fontSize = 62.sp)
                }
            }

            Spacer(Modifier.height(48.dp))

            Text(
                text = "놓치기 아까운 혜택",
                color = Color(0xFF25272B),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(22.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Surface(
                    color = Color(0xFF4A4E54),
                    shape = RoundedCornerShape(26.dp)
                ) {
                    Text(
                        text = "혜택 모아보기",
                        modifier = Modifier.padding(horizontal = 22.dp, vertical = 12.dp),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(26.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFD8DCE0))
                ) {
                    Text(
                        text = "🎁 이벤트",
                        modifier = Modifier.padding(horizontal = 22.dp, vertical = 12.dp),
                        color = Color(0xFF6F747B),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(30.dp))

            BenefitListItem(
                icon = "🎉",
                eyebrow = "지금 참여 가능한 혜택",
                title = "이벤트",
                badge = "OPEN",
                onClick = { onItemClick("이벤트") }
            )
            BenefitListItem(
                icon = "🎟",
                eyebrow = "내 쿠폰함 바로가기",
                title = "쿠폰함",
                badge = "확인하기",
                onClick = { onItemClick("쿠폰함") }
            )
        }
    }
}

@Composable
private fun BenefitListItem(
    icon: String,
    eyebrow: String,
    title: String,
    badge: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFFF5F6F8)),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, fontSize = 34.sp)
        }
        Spacer(Modifier.width(20.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(eyebrow, color = Color(0xFF7A7F87), fontSize = 17.sp)
            Spacer(Modifier.height(5.dp))
            Text(title, color = Color(0xFF25272B), fontSize = 23.sp, fontWeight = FontWeight.Bold)
        }
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(24.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDDE1E5))
        ) {
            Text(
                text = badge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 9.dp),
                color = Color(0xFF4A4E54),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun WalletScreen(
    onClose: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1F2430)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(start = 20.dp, end = 20.dp, top = 18.dp, bottom = 28.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "×",
                    modifier = Modifier.clickable(onClick = onClose),
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Light
                )
                Spacer(Modifier.width(18.dp))
                Text("국민지갑 전체보기 >", color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(Modifier.height(24.dp))

            WalletShortcut(
                title = "모바일 신분증",
                description = "주민등록증, 운전면허증을 안전하게 보관해요.",
                accent = Color(0xFFFFD73D),
                icon = "🪪",
                onClick = { onItemClick("모바일 신분증") }
            )
            Spacer(Modifier.height(10.dp))
            WalletShortcut(
                title = "결제",
                description = "계좌 기반 간편결제와 포인트 사용을 준비했어요.",
                accent = Color(0xFFFFD73D),
                icon = "💳",
                onClick = { onItemClick("결제") }
            )

            Spacer(Modifier.height(14.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = Color.White,
                shape = RoundedCornerShape(22.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    WalletCardSection(
                        title = "NFT",
                        tabLabels = listOf("아트", "티켓"),
                        icon = "🎟",
                        headline = "가지고 있는 NFT가 없어요",
                        body = "KB국민은행이 진행하는 다양한 이벤트로 NFT를 받을 수 있어요.",
                        buttonText = "NFT 더 알아보기",
                        onClick = { onItemClick("NFT") }
                    )

                    Spacer(Modifier.height(22.dp))

                    WalletCardSection(
                        title = "공공알리미",
                        tabLabels = listOf("국민비서", "전자문서"),
                        icon = "📬",
                        headline = "놓치기 쉬운 안내를 모아드려요",
                        body = "생활에 필요한 공공 알림과 전자문서를 이곳에서 확인할 수 있어요.",
                        buttonText = "공공알리미 보기",
                        onClick = { onItemClick("공공알리미") }
                    )
                }
            }
        }
    }
}

@Composable
private fun WalletShortcut(
    title: String,
    description: String,
    accent: Color,
    icon: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(76.dp)
            .clickable(onClick = onClick),
        color = accent,
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(icon, fontSize = 27.sp)
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = Color(0xFF25272B), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(3.dp))
                Text(description, color = Color(0xFF5E5A36), fontSize = 13.sp)
            }
            Text("›", color = Color(0xFF5E5A36), fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun WalletCardSection(
    title: String,
    tabLabels: List<String>,
    icon: String,
    headline: String,
    body: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, color = Color(0xFF25272B), fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = Color(0xFFF1F3F6)
            ) {
                Row(modifier = Modifier.padding(3.dp)) {
                    tabLabels.forEachIndexed { index, label ->
                        Surface(
                            color = if (index == 0) Color.White else Color.Transparent,
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(
                                text = label,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 7.dp),
                                color = Color(0xFF6F747B),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(22.dp))
        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        listOf(Color(0xFFFFF3B0), Color(0xFFE6ECFF))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, fontSize = 25.sp)
        }
        Spacer(Modifier.height(16.dp))
        Text(headline, color = Color(0xFF25272B), fontSize = 19.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text(body, color = Color(0xFF7A7F87), fontSize = 14.sp, lineHeight = 20.sp)
        Spacer(Modifier.height(16.dp))
        Surface(
            color = Color(0xFFFFD337),
            shape = RoundedCornerShape(3.dp)
        ) {
            Text(
                text = buttonText,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 9.dp),
                color = Color(0xFF25272B),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun SimpleListScreen(
    title: String,
    items: List<String>,
    onBackClick: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(horizontal = 28.dp, vertical = 24.dp)
        ) {
            Text(
                text = "<  $title",
                modifier = Modifier.clickable(onClick = onBackClick),
                color = Color(0xFF25272B),
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(26.dp))
            items.forEach { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onItemClick(item) }
                        .padding(vertical = 18.dp, horizontal = 8.dp),
                    color = Color(0xFF25272B),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun PlaceholderScreen(
    title: String,
    message: String,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = Color(0xFF25272B),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = message,
                color = Color(0xFF73777F),
                fontSize = 18.sp,
                lineHeight = 26.sp
            )
            Spacer(Modifier.height(28.dp))
            Surface(
                modifier = Modifier
                    .height(52.dp)
                    .clickable(onClick = onButtonClick),
                color = Color(0xFF4A4E54),
                shape = RoundedCornerShape(28.dp)
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 28.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = buttonText,
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
