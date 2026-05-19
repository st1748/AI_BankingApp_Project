package com.example.bankintentdemo.model

import androidx.compose.ui.graphics.Color

enum class MenuSectionLayout {
    Grid,
    List
}

data class MenuItem(
    val title: String,
    val description: String? = null,
    val hasNewBadge: Boolean = false
)

data class MenuSection(
    val title: String,
    val iconText: String,
    val iconColor: Color,
    val layout: MenuSectionLayout = MenuSectionLayout.List,
    val items: List<MenuItem>
)

