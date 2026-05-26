package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bankintentdemo.navigation.AppRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainAccountSection(navController: NavController) {
    // 총 3개의 카드 (1번: 실제 계좌, 2번/3번: 빈 카드)
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // 1. 메인 카드 페이저 (스와이프 영역)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp), // 좌우 여백
            pageSpacing = 12.dp // 카드 사이 간격
        ) { page ->
            if (page == 0) {
                // 첫 번째 페이지: 실제 통장 카드
                RealAccountCard(navController = navController)
            } else {
                // 두 번째, 세 번째 페이지: 빈 통장 등록 카드
                EmptyAccountCard()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 2. 하단 컨트롤러 ( < 1 / 3 >  및 전체계좌 보기 버튼 )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 좌측: 화살표와 페이지 번호 (< 1 / 3 >)
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            if (pagerState.currentPage > 0) pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = "이전", tint = Color.DarkGray)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${pagerState.currentPage + 1} / 3",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            if (pagerState.currentPage < 2) pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "다음", tint = Color.DarkGray)
                }
            }

            // 우측: 전체계좌 보기 둥근 버튼
            Button(
                onClick = { navController.navigate(AppRoute.InquiryAllAccounts.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6B7280)),
                shape = RoundedCornerShape(50),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                modifier = Modifier.height(32.dp)
            ) {
                Text("전체계좌 보기", fontSize = 13.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

// 계좌 카드 디자인
@Composable
fun RealAccountCard(navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp) // 아주 살짝 그림자
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate(AppRoute.InquiryIntegratedHistory.route) }
                .padding(20.dp)
        ) {
            // 상단: 통장 이름 및 더보기
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // 통장 로고 (원형 임시 로고)
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF8B6B4A)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("KB", color = Color.Yellow, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "KB나라사랑우대통장", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "더보기", tint = Color.Gray)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // 계좌번호 및 복사 아이콘
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "466802-04-274759", fontSize = 13.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "복사", tint = Color.LightGray, modifier = Modifier.size(14.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 금액 및 숨김 버튼
            Row(verticalAlignment = Alignment.Bottom) {
                Text(text = "609,149", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
                Text(text = "원", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(bottom = 2.dp, start = 2.dp))
                Spacer(modifier = Modifier.width(8.dp))
                // 숨김 뱃지
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF3F4F6), RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .padding(bottom = 4.dp)
                ) {
                    Text(text = "숨김", fontSize = 11.sp, color = Color.DarkGray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 계좌이체 & 연락처 이체 버튼
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { navController.navigate(AppRoute.TransferMain.route) },
                    modifier = Modifier.weight(1f).height(44.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD600)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("계좌이체", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }
                Button(
                    onClick = { navController.navigate(AppRoute.TransferMain.route) },
                    modifier = Modifier.weight(1f).height(44.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF3F4F6)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("연락처 이체", color = Color.DarkGray, fontWeight = FontWeight.Medium, fontSize = 15.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 최근 이체 내역 아이콘들
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                RecentTransferItem(name = "하정빈", iconColor = Color(0xFF2563EB), initial = "S")
                RecentTransferItem(name = "이성탁", iconColor = Color(0xFFFFD600), initial = "B", textColor = Color.Black)
                RecentTransferItem(name = "조중혁", iconColor = Color(0xFF10B981), initial = "★")
            }
        }
    }
}

// 하단 최근 이체 내역 컴포넌트
@Composable
fun RecentTransferItem(name: String, iconColor: Color, initial: String, textColor: Color = Color.White) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(iconColor),
            contentAlignment = Alignment.Center
        ) {
            Text(text = initial, color = textColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = name, fontSize = 13.sp, color = Color.Gray)
    }
}

// 빈 카드
@Composable
fun EmptyAccountCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp), // 실제 카드와 높이를 비슷하게 맞춤
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null, tint = Color.Transparent) // 레이아웃 맞춤용
            Text(
                text = "계좌나 통장을 등록해보세요",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* 등록 로직 */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF3F4F6)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("등록 / 설정", color = Color.DarkGray)
            }
        }
    }
}