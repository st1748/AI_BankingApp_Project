package com.example.bankintentdemo.ui.screens.product.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankintentdemo.ui.screens.product.productCategories

@Composable
fun ProductScrollableTabs(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    onOpenBottomSheet: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.weight(1f),
            containerColor = Color.White,
            edgePadding = 16.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = Color(0xFF20242A), // 선택된 탭 블랙 밑줄
                    height = 3.dp
                )
            },
            divider = {} // 하단 기본 회색선 제거
        ) {
            productCategories.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            text = title,
                            fontSize = 16.sp,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Medium,
                            color = if (selectedTabIndex == index) Color(0xFF20242A) else Color(0xFF8A9199)
                        )
                    }
                )
            }
        }

        // 우측 하단 화살표 버튼
        Box(
            modifier = Modifier
                .padding(end = 16.dp)
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable { onOpenBottomSheet() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = "메뉴 펼치기",
                tint = Color(0xFF20242A)
            )
        }
    }
}