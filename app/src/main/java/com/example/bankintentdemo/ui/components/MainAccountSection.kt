package com.example.bankintentdemo.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    // 3개의 가짜 빈 페이지를 위한 페이저 상태
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // 1. 메인 통장 카드 (스와이프 가능)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            EmptyAccountCard()
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2. 하단 컨트롤 영역 (좌우 넘김 버튼 & 전체계좌 보기)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 좌측: 좌우 화살표 버튼
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        // 왼쪽 버튼 클릭 시 이전 페이지로 이동
                        coroutineScope.launch {
                            if (pagerState.currentPage > 0) {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "이전 계좌")
                }

                IconButton(
                    onClick = {
                        // 오른쪽 버튼 클릭 시 다음 페이지로 이동
                        coroutineScope.launch {
                            if (pagerState.currentPage < 2) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "다음 계좌")
                }
            }

            // 우측: 전체계좌 보기 버튼
            TextButton(
                onClick = { navController.navigate(AppRoute.InquiryAllAccounts.route) }
            ) {
                Text(
                    text = "전체계좌 보기",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            }
        }
    }
}

// 디자인만 있는 빈 컴포넌트
@Composable
fun EmptyAccountCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(180.dp), // 은행 앱 카드와 비슷한 비율
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F6F8)) // 연한 회색 배경
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "계좌나 통장을 등록해보세요",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* 기능 없음 */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDCDFE4)) // 비활성화 느낌의 버튼
            ) {
                Text("등록 / 설정", color = Color.Black)
            }
        }
    }
}