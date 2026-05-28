package com.example.bankintentdemo.ui.screens.inquiry

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.ui.MainViewModel
import com.example.bankintentdemo.ui.screens.inquiry.components.InquiryTopBar

@Composable
fun AllAccountsScreen(navController: NavController, viewModel: MainViewModel) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        InquiryTopBar(title = "전체계좌조회", navController = navController, isAiMode = isAiMode)

        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            // 1. 상단 탭
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("KB국민은행", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color(0xFF20242A)))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("다른금융", fontSize = 16.sp, color = Color(0xFF8A9199))
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFEBECEF)))
                }
            }

            // 2. 알림 배너
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Notifications, contentDescription = null, tint = Color(0xFF20242A))
                Spacer(modifier = Modifier.width(8.dp))
                Text("입출금 알림을 실시간으로 받아보세요!", fontSize = 14.sp, color = Color(0xFF6B7279), modifier = Modifier.weight(1f))
                Text(">", fontSize = 14.sp, color = Color(0xFF8A9199))
            }
            HorizontalDivider(color = Color(0xFFF0F2F5), thickness = 8.dp)

            // 3. 총 잔액 및 버튼
            Column(modifier = Modifier.padding(24.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("총 잔액 ❔", fontSize = 14.sp, color = Color(0xFF6B7279))
                    Spacer(modifier = Modifier.width(8.dp))
                    // 임시 스위치 UI
                    Box(modifier = Modifier.width(40.dp).height(20.dp).clip(RoundedCornerShape(10.dp)).background(Color(0xFF8A9199))) {
                        Box(modifier = Modifier.size(16.dp).align(Alignment.CenterStart).padding(start = 2.dp).clip(RoundedCornerShape(8.dp)).background(Color.White))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.weight(1f).height(48.dp).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF0F2F5)), contentAlignment = Alignment.Center) {
                        Text("모으기", fontSize = 15.sp, color = Color(0xFF4A5056))
                    }
                    Box(modifier = Modifier.weight(1f).height(48.dp).clip(RoundedCornerShape(8.dp)).background(Color(0xFFFFCC00)), contentAlignment = Alignment.Center) {
                        Text("이체", fontSize = 15.sp, color = Color(0xFF20242A))
                    }
                }
            }

            // 4. 예금/적금 카드
            Box(modifier = Modifier.fillMaxWidth().background(Color(0xFFF4F6F9)).padding(horizontal = 24.dp, vertical = 16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("예금 · 적금", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("609,149", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                        Text("원", fontSize = 15.sp, color = Color(0xFF6B7279))
                        Icon(Icons.Outlined.KeyboardArrowUp, contentDescription = null, tint = Color(0xFF8A9199))
                    }
                }
            }

            // 내 계좌 상세 카드
            Box(modifier = Modifier.padding(horizontal = 24.dp).offset(y = (-8).dp).clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)).background(Color.White).border(1.dp, Color(0xFFEBECEF), RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)).padding(20.dp)) {
                Column {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text("KB나라사랑우대통장", fontSize = 13.sp, color = Color(0xFF6B7279))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("466802-04-274759 ⧉", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                        }
                        Icon(Icons.Outlined.MoreVert, contentDescription = null, tint = Color(0xFF8A9199))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("609,149원", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A), modifier = Modifier.align(Alignment.End))
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(modifier = Modifier.weight(1f).height(40.dp).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF0F2F5)), contentAlignment = Alignment.Center) {
                            Text("모으기", fontSize = 14.sp, color = Color(0xFF4A5056))
                        }
                        Box(modifier = Modifier.weight(1f).height(40.dp).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF0F2F5)), contentAlignment = Alignment.Center) {
                            Text("이체", fontSize = 14.sp, color = Color(0xFF4A5056))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // 하단 메뉴들
            ListTile("보험 · 공제")
            ListTile("퇴직연금")

            Spacer(modifier = Modifier.height(24.dp))
            // 하단 배너
            Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF9F7D9)).padding(20.dp)) {
                Column {
                    Text("안쓰는 계좌 해지하고 잔돈 모으세요", fontSize = 12.sp, color = Color(0xFF6B7279))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("숨은 잔돈 모으기", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun ListTile(title: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6B7279))
        Icon(Icons.Outlined.KeyboardArrowDown, contentDescription = null, tint = Color(0xFF8A9199))
    }
    HorizontalDivider(color = Color(0xFFF0F2F5), modifier = Modifier.padding(horizontal = 24.dp))
}