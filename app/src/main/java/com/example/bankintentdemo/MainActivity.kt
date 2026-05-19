package com.example.bankintentdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bankintentdemo.ui.MainScreen
import com.example.bankintentdemo.ui.theme.BankIntentDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankIntentDemoTheme {
                MainScreen()
            }
        }
    }
}
