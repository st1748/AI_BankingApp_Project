package com.example.bankintentdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.bankintentdemo.ui.MainScreen
import com.example.bankintentdemo.ui.theme.BankIntentDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFFF4F6F9) // 배경색
            ) {
                BankIntentDemoTheme(darkTheme = false) { // darkTheme = false
                    MainScreen()
                }
            }
        }
    }
}