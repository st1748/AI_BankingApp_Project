package com.example.bankintentdemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankintentdemo.data.bankMenuSections
import com.example.bankintentdemo.model.MenuItem
import com.example.bankintentdemo.model.MenuSection
import com.example.bankintentdemo.model.MenuSectionLayout

@Composable
fun MenuScreen(
    onClose: () -> Unit = {},
    onItemClick: (String, MenuItem) -> Unit = { _, _ -> }
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = KbWhite
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.statusBars.asPaddingValues())
                .verticalScroll(scrollState)
                .padding(start = 24.dp, end = 24.dp, bottom = 48.dp)
        ) {
            TopIcons(onClose = onClose)
            Spacer(Modifier.height(8.dp))
            DividerLine()
            bankMenuSections.forEach { section ->
                SectionHeader(section = section)
                when (section.layout) {
                    MenuSectionLayout.Grid -> ProductGrid(
                        section = section,
                        onItemClick = onItemClick
                    )

                    MenuSectionLayout.List -> section.items.forEach { item ->
                        MenuRow(
                            item = item,
                            onClick = { onItemClick(section.title, item) }
                        )
                    }
                }
                Spacer(Modifier.height(20.dp))
                DividerLine()
            }
        }
    }
}

@Composable
private fun TopIcons(onClose: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 4.dp, top = 22.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.weight(1f))
        Text(
            text = "⌕",
            color = KbDarkGray,
            fontSize = 42.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(Modifier.width(24.dp))
        Text(
            text = "×",
            modifier = Modifier.clickable(onClick = onClose),
            color = KbDarkGray,
            fontSize = 42.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
private fun SectionHeader(section: MenuSection) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(section.iconColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = section.iconText,
                color = Color.White,
                fontSize = if (section.iconText.length > 1) 8.sp else 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(Modifier.width(12.dp))
        Text(
            text = section.title,
            color = KbGray,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ProductGrid(
    section: MenuSection,
    onItemClick: (String, MenuItem) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        section.items.chunked(4).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowItems.forEach { item ->
                    ProductButton(
                        modifier = Modifier.weight(1f),
                        item = item,
                        onClick = { onItemClick(section.title, item) }
                    )
                }
                repeat(4 - rowItems.size) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun ProductButton(
    modifier: Modifier = Modifier,
    item: MenuItem,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(54.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        color = KbButtonBackground
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = item.title,
                color = KbText,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun MenuRow(
    item: MenuItem,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(start = 30.dp, top = 13.dp, bottom = 13.dp, end = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = item.title,
                color = KbText,
                fontSize = 22.sp,
                lineHeight = 29.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (item.hasNewBadge) {
                Spacer(Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(KbRed)
                )
            }
        }
        item.description?.let { description ->
            Spacer(Modifier.height(4.dp))
            Text(
                text = description,
                color = KbGray,
                fontSize = 15.sp,
                lineHeight = 21.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun DividerLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(KbLine)
    )
}

private val KbWhite = Color(0xFFFFFFFF)
private val KbText = Color(0xFF25272B)
private val KbGray = Color(0xFF73777F)
private val KbDarkGray = Color(0xFF4A4E54)
private val KbLine = Color(0xFFECEEF1)
private val KbRed = Color(0xFFE84D4F)
private val KbButtonBackground = Color(0xFFF6F7F9)

@Preview(showBackground = true)
@Composable
private fun MenuScreenPreview() {
    MaterialTheme {
        MenuScreen()
    }
}

