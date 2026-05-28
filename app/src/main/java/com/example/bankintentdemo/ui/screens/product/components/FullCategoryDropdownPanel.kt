package com.example.bankintentdemo.ui.screens.product.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
fun FullCategoryDropdownPanel(
    onDismiss: () -> Unit,
    onCategorySelected: (Int) -> Unit
) {
    // 하단 모서리만 둥글게 깎인 하얀색 패널
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            Text(
                text = "전체 카테고리",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF20242A)
            )
            Spacer(modifier = Modifier.height(24.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.heightIn(max = 400.dp) // 너무 길어지지 않게 제한
            ) {
                items(productCategories.size) { index ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { onCategorySelected(index) }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF7F8FA)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = if (index == 0) Color(0xFFFF528A) else Color(0xFF3D8BFF)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = productCategories[index],
                            fontSize = 13.sp,
                            color = Color(0xFF4A5056)
                        )
                    }
                }
            }
        }

        HorizontalDivider(color = Color(0xFFF0F2F5))

        // 하단 닫기 버튼
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onDismiss() }
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "닫기", fontSize = 15.sp, color = Color(0xFF6B7279))
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowUp,
                contentDescription = null,
                tint = Color(0xFF6B7279),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}