package com.example.bankintentdemo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AiHomeScreen() {
    var aiMode by remember { mutableStateOf(true) }
    var showMenu by remember { mutableStateOf(false) }
    var showTransfer by remember { mutableStateOf(false) }
    var showTransactionHistory by remember { mutableStateOf(false) }
    var showTotalAccounts by remember { mutableStateOf(false) }

    if (showMenu) {
        MenuScreen(onBackClick = { showMenu = false })
        return
    }

    if (showTransfer) {
        TransferScreen(onBackClick = { showTransfer = false })
        return
    }

    if (showTransactionHistory) {
        TransactionHistoryScreen(onBackClick = { showTransactionHistory = false })
        return
    }

    if (showTotalAccounts) {
        TotalAccountsScreen(onBackClick = { showTotalAccounts = false })
        return
    }

    if (!aiMode) {
        NormalHomeScreen(
            onAiModeChange = { aiMode = it },
            onMenuClick = { showMenu = true }
        )
        return
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F5F5)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(16.dp)
        ) {
            item {
                TopSection(
                    aiMode = aiMode,
                    onAiModeChange = { aiMode = it },
                    onMenuClick = { showMenu = true }
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                AccountCardSection(
                    onTransferClick = { showTransfer = true },
                    onTransactionHistoryClick = { showTransactionHistory = true },
                    onTotalAccountsClick = { showTotalAccounts = true }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                SearchSection()
            }
        }
    }
}

@Composable
fun TopSection(
    aiMode: Boolean,
    onAiModeChange: (Boolean) -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.clickable {
                onAiModeChange(!aiMode)
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                checked = aiMode,
                onCheckedChange = onAiModeChange
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "회원 이름",
                fontWeight = FontWeight.Bold
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Member"
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notification"
                )
            }

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }

            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        }
    }
}

@Composable
fun AccountCardSection(
    onTransferClick: () -> Unit,
    onTransactionHistoryClick: () -> Unit,
    onTotalAccountsClick: () -> Unit
) {
    var currentCardIndex by remember { mutableStateOf(0) }
    val totalCardCount = 3

    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onTransactionHistoryClick),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                if (currentCardIndex == 0) {
                    Text(
                        text = "KB나라사랑우대통장",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "12345-67-891011")

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "잔액 숨김",
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = onTransferClick,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFD54F)
                            )
                        ) {
                            Text(
                                text = "계좌이체",
                                color = Color.Black
                            )
                        }

                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = onTransferClick,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE0E0E0)
                            )
                        ) {
                            Text(
                                text = "연락처 이체",
                                color = Color.Black
                            )
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "계좌를 추가하세요",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(88.dp))
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            currentCardIndex =
                                if (currentCardIndex == 0) totalCardCount - 1 else currentCardIndex - 1
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Previous account"
                        )
                    }

                    Text(
                        text = "${currentCardIndex + 1} / $totalCardCount",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(
                        onClick = {
                            currentCardIndex = (currentCardIndex + 1) % totalCardCount
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Next account"
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = onTotalAccountsClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                )
            ) {
                Text(text = "전체계좌 보기")
            }
        }
    }
}

@Composable
fun SearchSection() {
    var searchText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchText,
            onValueChange = {
                searchText = it
            },
            placeholder = {
                Text("무엇을 찾고 싶으세요?")
            },
            shape = RoundedCornerShape(30.dp)
        )
    }
}

@Composable
fun RecommendationItem(
    number: Int,
    title: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$number.",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                fontSize = 16.sp
            )
        }
    }
}
