package com.example.bankintentdemo.ui.screens.inquiry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.KeyboardArrowUp
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
fun AccountManagementScreen(navController: NavController, viewModel: MainViewModel) {
    val isAiMode by viewModel.isAiMode.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        InquiryTopBar(title = "계좌관리", navController = navController, isAiMode = isAiMode)

        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

            // 1. 계좌 관리 섹션
            Spacer(modifier = Modifier.height(16.dp))
            Text("계좌 관리", fontSize = 14.sp, color = Color(0xFF6B7279), modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(8.dp))

            SettingListItem("계좌 숨기기", "조회/이체 시 숨기고 싶은 계좌 관리")
            SettingListItem("KB내맘대로계좌번호 서비스", "원하는 번호로 입금전용 계좌번호 설정")
            SettingListItem("계좌통합관리서비스(어카운트인포)", "내 계좌와 카드를 한눈에 조회")
            SettingListItem("연금 받는계좌 조회/변경", "국민연금, 공무원연금, 사학연금, 군인연금")
            SettingListItem("해지계좌 조회", "")
            SettingListItem("계좌종합관리", "사업·단체 계좌 연결/해제")

            HorizontalDivider(color = Color(0xFFF0F2F5), thickness = 8.dp)

            // 2. 비밀번호 관리 섹션
            Spacer(modifier = Modifier.height(24.dp))
            Text("비밀번호 관리", fontSize = 14.sp, color = Color(0xFF6B7279), modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(8.dp))

            SettingListItem("계좌비밀번호 변경", "")
            SettingListItem("계좌비밀번호 오류 해제", "", trailingText = "잠긴 계좌 0개 >", trailingColor = Color(0xFF3D8BFF))
            SettingListItem("계좌비밀번호 신규 등록", "지점에 방문하여 인증번호를 받은 경우")

            Spacer(modifier = Modifier.height(16.dp))

            // 3. 이체관리 배너
            Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).clip(RoundedCornerShape(8.dp)).background(Color(0xFFF7F8FA)).padding(20.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("이체관리", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("이체한도 변경 및 출금 계좌 설정", fontSize = 13.sp, color = Color(0xFF6B7279))
                    }
                    Icon(Icons.Outlined.ChevronRight, contentDescription = null, tint = Color(0xFF8A9199))
                }
            }

            // 4. 알려드립니다 (아코디언)
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider(color = Color(0xFFF0F2F5))
            Row(modifier = Modifier.fillMaxWidth().padding(24.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("ℹ️", fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("알려드립니다", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4A5056))
                }
                Icon(Icons.Outlined.KeyboardArrowUp, contentDescription = null, tint = Color(0xFF8A9199))
            }

            Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
                Text("계좌 비밀번호를 잊었거나, 여러 계좌를 동시에 변경하려면?", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
                Spacer(modifier = Modifier.height(12.dp))
                Text("• 비대면 본인확인 후, 새로운 비밀번호를 설정해주세요.", fontSize = 13.sp, color = Color(0xFF6B7279))
                Text("- 신분증 촬영/얼굴확인 또는 모바일 신분증 인증", fontSize = 13.sp, color = Color(0xFF8A9199))
                Text("- 국민은행 계좌인증/다른은행 계좌인증/영상통화 인증(택 1)", fontSize = 13.sp, color = Color(0xFF8A9199))
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

// 리스트 아이템 컴포넌트
@Composable
fun SettingListItem(title: String, desc: String, trailingText: String = "", trailingColor: Color = Color(0xFF8A9199)) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF20242A))
            if (desc.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(desc, fontSize = 13.sp, color = Color(0xFF8A9199))
            }
        }
        if (trailingText.isNotEmpty()) {
            Text(trailingText, fontSize = 14.sp, color = trailingColor)
        } else {
            Icon(Icons.Outlined.ChevronRight, contentDescription = null, tint = Color(0xFF8A9199))
        }
    }
    HorizontalDivider(color = Color(0xFFF0F2F5), modifier = Modifier.padding(horizontal = 24.dp))
}