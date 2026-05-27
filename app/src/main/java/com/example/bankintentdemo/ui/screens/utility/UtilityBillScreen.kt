package com.example.bankintentdemo.ui.screens.utility

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
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.BusinessCenter
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.Gavel
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Screenshot
import androidx.compose.material.icons.outlined.Smartphone
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.WorkspacePremium
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import com.example.bankintentdemo.ui.MainViewModel

@Composable
fun UtilityBillScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        UtilityTopBar(
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
                .padding(horizontal = 28.dp)
        ) {
            Spacer(modifier = Modifier.height(28.dp))
            UtilitySegment()
            Spacer(modifier = Modifier.height(44.dp))
            TaxSummaryCard()
            Spacer(modifier = Modifier.height(36.dp))
            SectionTitle("세금")
            Spacer(modifier = Modifier.height(24.dp))
            UtilityGrid(
                listOf(
                    UtilityItem("국세", Icons.Outlined.Receipt, Color(0xFFE9F6FF)),
                    UtilityItem("지방세", Icons.Outlined.Receipt, Color(0xFFE9F6FF)),
                    UtilityItem("관세", Icons.Outlined.Savings, Color(0xFFF4F6F8)),
                    UtilityItem("기금/국고", Icons.Outlined.AccountBalance, Color(0xFFF4F6F8)),
                    UtilityItem("세외수입", Icons.Outlined.WorkspacePremium, Color(0xFFEAF8F1)),
                    UtilityItem("환경개선부담금", Icons.Outlined.Eco, Color(0xFFEAF8F1)),
                    UtilityItem("교통범칙금", Icons.Outlined.DirectionsCar, Color(0xFFF4F6F8)),
                    UtilityItem("검찰청벌과금", Icons.Outlined.Gavel, Color(0xFFF4F6F8)),
                    UtilityItem("4대보험료", Icons.Outlined.BusinessCenter, Color(0xFFF4F6F8))
                )
            )
            Spacer(modifier = Modifier.height(42.dp))
            LivingUtilityHeader()
            Spacer(modifier = Modifier.height(24.dp))
            UtilityGrid(
                listOf(
                    UtilityItem("지로", Icons.Outlined.Description, Color(0xFFF4F6F8)),
                    UtilityItem("KT통신", Icons.Outlined.Smartphone, Color(0xFFF4F6F8)),
                    UtilityItem("전기/TV", Icons.Outlined.Receipt, Color(0xFFFFF6D9)),
                    UtilityItem("상하수도", Icons.Outlined.WaterDrop, Color(0xFFEAF6FF)),
                    UtilityItem("관리비", Icons.Outlined.Description, Color(0xFFEFF3FF)),
                    UtilityItem("등록금", Icons.Outlined.School, Color(0xFFEFF3FF))
                )
            )
            Spacer(modifier = Modifier.height(36.dp))
            ScanPaymentButton()
            Spacer(modifier = Modifier.height(18.dp))
            BottomActionButtons()
            Spacer(modifier = Modifier.height(42.dp))
            TaxNoticeBanner()
            Spacer(modifier = Modifier.height(44.dp))
        }
    }
}

@Composable
private fun UtilityTopBar(
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
            text = "공과금 납부/조회",
            fontSize = 25.sp,
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
private fun UtilitySegment() {
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
            text = "납부하기",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 18.dp),
            text = "납부 내역 조회",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7B8288),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
private fun TaxSummaryCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF7F8FA))
            .padding(horizontal = 24.dp, vertical = 30.dp)
    ) {
        Text(
            text = "납부할 세금을 확인해보세요",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "2026.05.27. 19:20 기준",
            fontSize = 18.sp,
            color = Color(0xFF8B939B)
        )
        Spacer(modifier = Modifier.height(28.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE1E4E8), RoundedCornerShape(10.dp))
                .padding(horizontal = 24.dp, vertical = 22.dp)
        ) {
            TaxLookupRow("국세")
            TaxLookupRow("지방세")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFFE1E4E8))
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "더보기", fontSize = 20.sp, color = Color(0xFF7B8288))
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "⌄", fontSize = 26.sp, color = Color(0xFF8B939B))
            }
        }
        Spacer(modifier = Modifier.height(22.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF9AA2AA)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "!", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "주민등록번호 기준으로 조회되는 내역만 보여드려요.",
                fontSize = 17.sp,
                color = Color(0xFF7B8288)
            )
        }
    }
}

@Composable
private fun TaxLookupRow(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            fontSize = 21.sp,
            color = Color(0xFF555C63)
        )
        Text(text = "조회", fontSize = 20.sp, color = Color(0xFF7B8288))
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF7B8288)
        )
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF20242A)
    )
}

@Composable
private fun LivingUtilityHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "생활공과금",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
        Text(text = "자주쓰는 지로관리", fontSize = 18.sp, color = Color(0xFF8B939B))
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFF7B8288)
        )
    }
}

private data class UtilityItem(
    val title: String,
    val icon: ImageVector,
    val backgroundColor: Color
)

@Composable
private fun UtilityGrid(items: List<UtilityItem>) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        items.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(26.dp)
            ) {
                rowItems.forEach { item ->
                    UtilityMenuItem(modifier = Modifier.weight(1f), item = item)
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun UtilityMenuItem(modifier: Modifier, item: UtilityItem) {
    Row(
        modifier = modifier.height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFF6F7F8)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(item.backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = Color(0xFF4F78D7)
                )
            }
        }
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = item.title,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20242A)
        )
    }
}

@Composable
private fun ScanPaymentButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .border(1.dp, Color(0xFF8B939B), RoundedCornerShape(2.dp)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = Icons.Outlined.Screenshot,
            contentDescription = null,
            tint = Color(0xFF30343A)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "고지서 촬영으로 간편 납부",
            fontSize = 20.sp,
            color = Color(0xFF30343A)
        )
    }
}

@Composable
private fun BottomActionButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        UtilityOutlineButton(modifier = Modifier.weight(1f), text = "자동납부")
        UtilityOutlineButton(modifier = Modifier.weight(1f), text = "예약납부")
    }
}

@Composable
private fun UtilityOutlineButton(modifier: Modifier, text: String) {
    Box(
        modifier = modifier
            .height(58.dp)
            .border(1.dp, Color(0xFF8B939B), RoundedCornerShape(2.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 21.sp, color = Color(0xFF30343A))
    }
}

@Composable
private fun TaxNoticeBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEAF7F4))
            .padding(horizontal = 28.dp, vertical = 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "자동차세, 재산세 등",
                fontSize = 19.sp,
                color = Color(0xFF7B8288)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "지방세·세외수입 알림 받으세요",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
        }
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF3EC7B8)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "TAX",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
