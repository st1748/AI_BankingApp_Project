package com.example.bankintentdemo.ui.screens.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun PublicAlertScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        PublicAlertTopBar(
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            PublicAlertHero()
            PublicAlertTabs(selectedIndex = 0)
            ImportantAlertsSection()
            ReceivedDocumentsSection()
            PublicAlertTabs(selectedIndex = 1)
            ManageSection()
            PublicAlertTabs(selectedIndex = 2)
            GuideSection()
        }
    }
}

@Composable
private fun PublicAlertTopBar(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.White)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(34.dp)
                .clickable(onClick = onBackClick),
            imageVector = Icons.Outlined.ChevronLeft,
            contentDescription = "Back",
            tint = Color(0xFF20242A)
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "공공알리미(국민비서 · 전자문서)",
            fontSize = 23.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF20242A),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Icon(
            modifier = Modifier
                .size(34.dp)
                .clickable(onClick = onHomeClick),
            imageVector = Icons.Outlined.Home,
            contentDescription = "Home",
            tint = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.size(26.dp))
        Icon(
            modifier = Modifier
                .size(36.dp)
                .clickable(onClick = onMenuClick),
            imageVector = Icons.Outlined.Menu,
            contentDescription = "Menu",
            tint = Color(0xFF20242A)
        )
    }
}

@Composable
private fun PublicAlertHero() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFFEAF4FF))
                .padding(horizontal = 22.dp, vertical = 26.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "국민비서가 처음이신가요?",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3D4147)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "꼭 필요한 행정, 공공 알림 서비스 알아보기",
                    fontSize = 18.sp,
                    color = Color(0xFF555C63)
                )
            }
            Box(
                modifier = Modifier
                    .size(width = 74.dp, height = 82.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Outlined.Article,
                    contentDescription = null,
                    tint = Color(0xFF2F80ED)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "━ • Ⅱ",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7B8288)
        )
    }
}

@Composable
private fun PublicAlertTabs(selectedIndex: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        AlertTab("받은 문서", selectedIndex == 0, Modifier.weight(1f))
        AlertTab("신청/관리", selectedIndex == 1, Modifier.weight(1f))
        AlertTab("이용안내", selectedIndex == 2, Modifier.weight(1f))
        Box(
            modifier = Modifier
                .width(56.dp)
                .height(64.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(34.dp),
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = null,
                tint = Color(0xFF8B939B)
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE1E4E8))
    )
}

@Composable
private fun AlertTab(text: String, selected: Boolean, modifier: Modifier) {
    Column(
        modifier = modifier.height(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = text,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = if (selected) Color(0xFF20242A) else Color(0xFF8B939B)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.82f)
                .height(4.dp)
                .background(if (selected) Color(0xFF20242A) else Color.Transparent)
        )
    }
}

@Composable
private fun ImportantAlertsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8F1E6))
            .padding(horizontal = 28.dp, vertical = 34.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "지금 바로 확인해야할 중요 알림",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3D4147)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "아직 읽지 않은 알림이 있어요",
                    fontSize = 18.sp,
                    color = Color(0xFF555C63)
                )
            }
            Icon(
                modifier = Modifier.size(58.dp),
                imageVector = Icons.Outlined.CalendarMonth,
                contentDescription = null,
                tint = Color(0xFFC99428)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        ImportantAlertCard("[관세청] 전자상거래(해외직구)물품 수입...")
        ImportantAlertCard("[관세청] 전자상거래(해외직구)물품 통관...")
        ImportantAlertCard("[신규서비스 안내(고유가 피해지원금 안...")
    }
}

@Composable
private fun ImportantAlertCard(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(horizontal = 22.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AgencyIcon(tint = Color(0xFFE11445))
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3D4147),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun ReceivedDocumentsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 28.dp, vertical = 48.dp)
    ) {
        Text(
            text = "받은 문서",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )

        Spacer(modifier = Modifier.height(30.dp))

        SegmentControl(left = "최신순 보기", right = "기관별 보기")

        Spacer(modifier = Modifier.height(34.dp))

        Row {
            Text(text = "전체 21", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
            Text(text = "  |  ", fontSize = 20.sp, color = Color(0xFFC5CBD1))
            Text(text = "안읽음 17", fontSize = 20.sp, color = Color(0xFF30343A))
        }

        Spacer(modifier = Modifier.height(28.dp))

        DocumentRow("KB국민은행 전자문서 중계서비스 개인정보처리...", "국민은행  |  26.05.19  |  D-345")
        DocumentRow("「전자금융서비스 이용약관」개정관련 사전안내", "국민은행  |  26.05.13  |  D-218")
        DocumentRow("[관세청] 전자상거래(해외직구)물품 수입신고 네...", "국민비서·관세청  |  26.05.12")
        DocumentRow("[관세청] 전자상거래(해외직구)물품 통관목록 제...", "국민비서·관세청  |  26.05.12")
        DocumentRow("[신규서비스 안내(고유가 피해지원금 안내)]", "국민비서·행정안전부  |  26.05.08")

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "+ 더보기",
            fontSize = 24.sp,
            color = Color(0xFF30343A)
        )
    }
}

@Composable
private fun SegmentControl(left: String, right: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(Color(0xFFE9ECEF))
            .padding(2.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
                .padding(vertical = 18.dp),
            text = left,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 18.dp),
            text = right,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF8B939B),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
private fun DocumentRow(title: String, meta: String) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFE1E4E8))
        )
        Spacer(modifier = Modifier.height(22.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF20242A),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = meta,
                    fontSize = 17.sp,
                    color = Color(0xFF8B939B),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = "안읽음",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2F80ED)
            )
        }
        Spacer(modifier = Modifier.height(22.dp))
    }
}

@Composable
private fun ManageSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F7F8))
            .padding(horizontal = 28.dp, vertical = 48.dp)
    ) {
        Text(
            text = "신청/관리",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.height(28.dp))
        Row {
            Text(text = "전체 12", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
            Text(text = "  |  신청완료 2  |  미신청 10", fontSize = 20.sp, color = Color(0xFF30343A))
        }
        Spacer(modifier = Modifier.height(28.dp))

        val agencies = listOf(
            AgencyInfo("국민비서", "행정정보 공공알림", "수신중(65/109)", true, Color(0xFFE11445)),
            AgencyInfo("한국주택금융공사", "보금자리론, 내집마련디딤돌 안내 등", "신청하기", false, Color(0xFF2F80ED)),
            AgencyInfo("국세청", "근로장려금, 종합소득세, 국세환급금 등", "신청하기", false, Color(0xFFE11445)),
            AgencyInfo("사학연금", "대여 미상환금, 퇴직급여 청구 안내 등", "신청하기", false, Color(0xFF4267B2)),
            AgencyInfo("지방자치단체", "지방세, 세외수입 등", "신청하기", false, Color(0xFFE11445)),
            AgencyInfo("KB국민은행", "예금잔액 조회장, 상품가입 계약서류 등", "수신중", true, Color(0xFFFFC400)),
            AgencyInfo("KB국민카드", "이용대금명세서", "신청하기", false, Color(0xFFFFC400)),
            AgencyInfo("KB증권", "월간거래내역", "신청하기", false, Color(0xFFFFC400)),
            AgencyInfo("KB캐피탈", "KB캐피탈 안내장 등", "신청하기", false, Color(0xFFFFC400)),
            AgencyInfo("KB손해보험", "보험계약 효력상실 통지, 퇴직연금증권 등", "신청하기", false, Color(0xFFFFC400)),
            AgencyInfo("KB라이프생명", "보험료 자동이체 확인서 등", "신청하기", false, Color(0xFFFFC400)),
            AgencyInfo("KB지방세입", "전국 지방세외수입 알림", "신청하기", false, Color(0xFFFFC400))
        )

        agencies.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                rowItems.forEach { agency ->
                    AgencyCard(modifier = Modifier.weight(1f), agency = agency)
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

private data class AgencyInfo(
    val title: String,
    val description: String,
    val action: String,
    val active: Boolean,
    val color: Color
)

@Composable
private fun AgencyCard(modifier: Modifier, agency: AgencyInfo) {
    Column(
        modifier = modifier
            .height(168.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AgencyIcon(tint = agency.color)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = agency.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = agency.description,
            fontSize = 16.sp,
            lineHeight = 23.sp,
            color = Color(0xFF7B8288),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = agency.action,
                fontSize = 17.sp,
                color = if (agency.active) Color(0xFF2F80ED) else Color(0xFF30343A)
            )
            Icon(
                modifier = Modifier.size(22.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = if (agency.active) Color(0xFF2F80ED) else Color(0xFF30343A)
            )
        }
    }
}

@Composable
private fun GuideSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 28.dp, vertical = 48.dp)
    ) {
        Text(
            text = "이용안내",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.height(28.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFF3F7FB))
                .padding(horizontal = 22.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF2F80ED)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(34.dp),
                    imageVector = Icons.Outlined.Article,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(18.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "KB공공알리미가 처음이신가요?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF20242A)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "KB공공알리미 사용법 보러가기",
                    fontSize = 18.sp,
                    color = Color(0xFF30343A)
                )
            }
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF8B939B)
            )
        }

        Spacer(modifier = Modifier.height(58.dp))

        Text(
            text = "자주 묻는 질문",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.height(26.dp))

        FaqRow("공공기관의 알림이 왜 오는건가요?")
        FaqRow("문서 도착 알림이 오지 않아요")
        FaqRow("열람기한이 지나면 볼수 없나요?")
        FaqRow("알림 내용에 대한 문의는 어디로 하나요?")
        FaqRow("광고 스팸으로 의심되는 전자문서를 받았어요")

        Spacer(modifier = Modifier.height(48.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F6F7))
                .padding(vertical = 36.dp)
        ) {
            Text(text = "전자문서", fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6C737A))
            Spacer(modifier = Modifier.height(18.dp))
            Text(text = "개인정보처리방침", fontSize = 19.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6C737A))
            Spacer(modifier = Modifier.height(28.dp))
            Text(text = "국민비서", fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6C737A))
            Spacer(modifier = Modifier.height(18.dp))
            Text(text = "서비스 이용약관  |  개인정보처리방침", fontSize = 17.sp, color = Color(0xFF8B939B))
        }
    }
}

@Composable
private fun FaqRow(text: String) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFE1E4E8))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                fontSize = 20.sp,
                color = Color(0xFF30343A)
            )
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Outlined.ExpandMore,
                contentDescription = null,
                tint = Color(0xFF8B939B)
            )
        }
    }
}

@Composable
private fun AgencyIcon(tint: Color) {
    Box(
        modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(Color.White)
            .border(1.dp, Color(0xFFE7EAED), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Outlined.Notifications,
            contentDescription = null,
            tint = tint
        )
    }
}
