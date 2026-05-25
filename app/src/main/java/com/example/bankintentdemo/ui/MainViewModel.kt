package com.example.bankintentdemo.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    // 1. AI 모드 활성화 여부 상태 저장
    private val _isAiMode = MutableStateFlow(false)
    val isAiMode: StateFlow<Boolean> = _isAiMode.asStateFlow()

    // AI 모드 On/Off 전환 함수
    fun toggleAiMode(enabled: Boolean) {
        _isAiMode.value = enabled
    }

    // (추후 구현) Top 3 분류 결과 등을 저장할 변수들도 여기에 추가될 예정
}