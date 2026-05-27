package com.example.bankintentdemo.ui.screens.asset

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
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.TrackChanges
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun ExpenseScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ExpenseTopBar(
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

        ExpenseTabs()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFF9F8EA), Color(0xFFEFF3F7), Color(0xFFE6F1D5))
                    )
                )
        ) {
            ExpenseSummarySection()
            InsightCard()
            IndicatorDots()
            CashFlowButton()
            WeeklyReportCard()
            AssetExpenseCard()
            CardPaymentCard()
            RegularExpenseCard()
            UsefulFeaturesCard()
            Spacer(modifier = Modifier.height(44.dp))
        }
    }
}

@Composable
private fun ExpenseTopBar(
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
            text = "지출",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF20242A)
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
private fun ExpenseTabs() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(Color.White),
        verticalAlignment = Alignment.Bottom
    ) {
        ExpenseTab("한번에", false, Modifier.weight(1f))
        ExpenseTab("지출", true, Modifier.weight(1f))
        ExpenseTab("투자", false, Modifier.weight(1f))
        ExpenseTab("연금/절세", false, Modifier.weight(1.3f))
        ExpenseTab("금융팁", false, Modifier.weight(1.1f))
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE1E4E8))
    )
}

@Composable
private fun ExpenseTab(text: String, selected: Boolean, modifier: Modifier) {
    Column(
        modifier = modifier.height(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = text,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = if (selected) Color(0xFF20242A) else Color(0xFF8B939B)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .height(4.dp)
                .background(if (selected) Color(0xFF20242A) else Color.Transparent)
        )
    }
}

@Composable
private fun ExpenseSummarySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 46.dp)
    ) {
        Text(
            text = "05월 나의 총 지출 ⟳",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3D4147)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "243,450원⌄",
            fontSize = 37.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "지난달 보다 39,500원 더 쓰고 있어요.",
            fontSize = 16.sp,
            color = Color(0xFF30343A)
        )
    }
}

@Composable
private fun InsightCard() {
    Column(
        modifier = Modifier
            .padding(horizontal = 28.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 26.dp)
    ) {
        Text(
            text = "디지털 PB가 분석했어요 ？",
            fontSize = 15.sp,
            color = Color(0xFF8B939B)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "이번 달 지출 1위는\n생활이에요",
            fontSize = 22.sp,
            lineHeight = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.height(28.dp))
        RecentExpenseBox()
        Spacer(modifier = Modifier.height(28.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFFCE9D8)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "지출내역 점검하기",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6B2E18)
            )
        }
    }
}

@Composable
private fun RecentExpenseBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF6F6F6))
            .padding(horizontal = 24.dp, vertical = 21.dp)
    ) {
        Text(
            text = "최근 지출",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.height(20.dp))
        ExpenseHistoryRow(Icons.Outlined.Coffee, Color(0xFF9B755F), "카페캠퍼", "05.27", "-2,000원")
        ExpenseHistoryRow(Icons.Outlined.Storefront, Color(0xFF6EA0F5), "지에스25 신촌그랑자이점", "05.26", "-13,600원")
        ExpenseHistoryRow(Icons.Outlined.Storefront, Color(0xFF6EA0F5), "지에스25 신촌그랑자이점", "05.25", "-15,100원")
    }
}

@Composable
private fun ExpenseHistoryRow(
    icon: ImageVector,
    color: Color,
    title: String,
    date: String,
    amount: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleIcon(icon = icon, color = color)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 13.sp, color = Color(0xFF30343A))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = date, fontSize = 12.sp, color = Color(0xFF8B939B))
        }
        Text(
            text = amount,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
    }
}

@Composable
private fun IndicatorDots() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 36.dp, height = 10.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF6F767D))
        )
        Spacer(modifier = Modifier.width(12.dp))
        Dot()
        Spacer(modifier = Modifier.width(12.dp))
        Dot()
    }
}

@Composable
private fun Dot() {
    Box(
        modifier = Modifier
            .size(10.dp)
            .clip(CircleShape)
            .background(Color(0xFF9AA2AA))
    )
}

@Composable
private fun CashFlowButton() {
    Row(
        modifier = Modifier
            .padding(horizontal = 28.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 19.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(34.dp),
            imageVector = Icons.Outlined.CalendarMonth,
            contentDescription = null,
            tint = Color(0xFF9BCB75)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = "현금흐름 한눈에 확인하기!",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF8B939B)
        )
    }
}

@Composable
private fun WeeklyReportCard() {
    Column(
        modifier = Modifier
            .padding(horizontal = 28.dp, vertical = 19.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 26.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "지난 주 소비리포트",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF20242A)
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "전 주보다 113,950원 많이 썼어요",
                    fontSize = 14.sp,
                    color = Color(0xFF7B8288)
                )
            }
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF8B939B)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            BarColumn("05월 2주차", "26,000원", Color(0xFFE8ECEF), 90)
            Spacer(modifier = Modifier.width(64.dp))
            BarColumn("05월 3주차", "139,950원", Color(0xFFFF8A4A), 190)
        }
    }
}

@Composable
private fun BarColumn(label: String, amount: String, color: Color, height: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = amount,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .size(width = 76.dp, height = height.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF30343A)
        )
    }
}

@Composable
private fun AssetExpenseCard() {
    Column(
        modifier = Modifier
            .padding(horizontal = 28.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 26.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = "자산별 지출",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color(0xFFF1F3F5))
                    .padding(horizontal = 18.dp, vertical = 7.dp),
                text = "+ 자산 추가",
                fontSize = 12.sp,
                color = Color(0xFF30343A)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        AssetExpenseRow(Icons.Outlined.CreditCard, Color(0xFFFF874D), "카드", "245,450원")
        AssetExpenseRow(Icons.Outlined.Savings, Color(0xFFF36F77), "계좌", "연결하기")
        AssetExpenseRow(Icons.Outlined.Payments, Color(0xFF4C85F0), "간편결제", "연결하기")
        AssetExpenseRow(Icons.Outlined.AccountBalanceWallet, Color(0xFFA968E8), "휴대폰 소액결제", "연결하기")
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFFFF5CE))
                .padding(horizontal = 22.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "💰", fontSize = 19.sp)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                modifier = Modifier.weight(1f),
            text = "분류가 필요한 지출 4건이 있어요",
                fontSize = 15.sp,
                color = Color(0xFF30343A)
            )
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF8B939B)
            )
        }
    }
}

@Composable
private fun AssetExpenseRow(icon: ImageVector, color: Color, title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleIcon(icon = icon, color = color)
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            fontSize = 16.sp,
            color = Color(0xFF30343A)
        )
        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = if (value == "0원") Color(0xFF20242A) else Color(0xFF7B8288)
        )
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF8B939B)
        )
    }
}

@Composable
private fun CardPaymentCard() {
    Column(
        modifier = Modifier
            .padding(horizontal = 28.dp, vertical = 19.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = "카드 결제예정",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Text(
                text = "0원",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF8B939B)
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
        Box(
            modifier = Modifier
                .size(width = 230.dp, height = 150.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFB4BBC3)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(54.dp),
                imageVector = Icons.Outlined.CreditCard,
                contentDescription = null,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Text(text = "신용카드", fontSize = 14.sp, color = Color(0xFF7B8288))
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "연결하기",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
    }
}

@Composable
private fun RegularExpenseCard() {
    Column(
        modifier = Modifier
            .padding(horizontal = 28.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "이번 달 정기지출",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF20242A)
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "총 0건이 남아있어요",
                    fontSize = 14.sp,
                    color = Color(0xFF7B8288)
                )
            }
            Text(
                text = "0원",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF8B939B)
            )
        }
        Spacer(modifier = Modifier.height(44.dp))
        Box(
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape)
                .background(Color(0xFFC5CBD1)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.Outlined.Error,
                contentDescription = null,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = "다가오는 지출이 없어요",
            fontSize = 15.sp,
            color = Color(0xFF30343A)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "자산 추가하고 다가오는 지출 찾기",
            fontSize = 14.sp,
            color = Color(0xFF7B8288),
            textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
        )
    }
}

@Composable
private fun UsefulFeaturesCard() {
    Column(
        modifier = Modifier
            .padding(horizontal = 28.dp, vertical = 19.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 26.dp)
    ) {
        Text(
            text = "지출관리에 유용한 기능",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.height(28.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            FeatureCard(
                modifier = Modifier.weight(1f),
                title = "금융플러스",
                description = "다양한 실물자산부터\n신용관리까지",
                backgroundColor = Color(0xFFE7F1FF),
                icon = Icons.Outlined.PieChart
            )
            FeatureCard(
                modifier = Modifier.weight(1f),
                title = "목표챌린지",
                description = "내 예산에 맞춘 지출관리\n시작",
                backgroundColor = Color(0xFFF0E7FF),
                icon = Icons.Outlined.TrackChanges
            )
        }
        IndicatorDots()
    }
}

@Composable
private fun FeatureCard(
    modifier: Modifier,
    title: String,
    description: String,
    backgroundColor: Color,
    icon: ImageVector
) {
    Column(
        modifier = modifier
            .height(210.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .padding(24.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = description,
            fontSize = 12.sp,
            lineHeight = 24.sp,
            color = Color(0xFF7B8288)
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.End),
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF78A8E8)
        )
    }
}

@Composable
private fun CircleIcon(icon: ImageVector, color: Color) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(27.dp),
            imageVector = icon,
            contentDescription = null,
            tint = Color.White
        )
    }
}
