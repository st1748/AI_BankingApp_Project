package com.example.bankintentdemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankintentdemo.model.PredictionResult
import com.example.bankintentdemo.model.SlmModelManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// AI 로딩 상태를 관리하는 클래스
sealed class IntentUiState {
    object Idle : IntentUiState()
    object Loading : IntentUiState()
    data class ShowTop3(val top3List: List<PredictionResult>) : IntentUiState()
}

class MainViewModel : ViewModel() {
    // 1. 기존 AI 모드 활성화 상태 유지
    private val _isAiMode = MutableStateFlow(false)
    val isAiMode: StateFlow<Boolean> = _isAiMode.asStateFlow()

    fun toggleAiMode(enabled: Boolean) {
        _isAiMode.value = enabled
        if (!enabled) resetState() // 일반 모드로 가면 검색 결과 초기화
    }

    // 2. AI 분석 결과 상태 추가
    private val _uiState = MutableStateFlow<IntentUiState>(IntentUiState.Idle)
    val uiState: StateFlow<IntentUiState> = _uiState.asStateFlow()

    fun analyzeUserQuery(query: String, modelManager: SlmModelManager) {
        if (query.isBlank()) return

        _uiState.value = IntentUiState.Loading // 로딩 시작

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val top3Results = modelManager.predict(query)
                _uiState.value = IntentUiState.ShowTop3(top3Results) // 결과 화면에 쏘기
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = IntentUiState.Idle
            }
        }
    }

    fun resetState() {
        _uiState.value = IntentUiState.Idle
    }
}