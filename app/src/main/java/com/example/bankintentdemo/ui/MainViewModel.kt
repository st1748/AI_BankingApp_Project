package com.example.bankintentdemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankintentdemo.model.ExplainManager
import com.example.bankintentdemo.model.Explanation
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
    data class ShowTop3(
        val top3List: List<PredictionResult>,
        val explanation: Explanation? = null, // 판단 근거 (어절별 기여도)
    ) : IntentUiState()
}

class MainViewModel : ViewModel() {
    // 1. 기존 AI 모드 활성화 상태 유지
    private val _isAiMode = MutableStateFlow(false)
    val isAiMode: StateFlow<Boolean> = _isAiMode.asStateFlow()

    fun toggleAiMode(enabled: Boolean) {
        _isAiMode.value = enabled
        if (!enabled) resetState() // 일반 모드로 가면 검색 결과 초기화
    }

    // 2. AI 분석 결과 상태
    private val _uiState = MutableStateFlow<IntentUiState>(IntentUiState.Idle)
    val uiState: StateFlow<IntentUiState> = _uiState.asStateFlow()

    // 3. 개발자 모드: 마지막 분석 결과, 로딩 여부, 속도 측정 결과
    private val _lastExplanation = MutableStateFlow<Explanation?>(null)
    val lastExplanation: StateFlow<Explanation?> = _lastExplanation.asStateFlow()

    private val _devLoading = MutableStateFlow(false)
    val devLoading: StateFlow<Boolean> = _devLoading.asStateFlow()

    private val _benchmarkMs = MutableStateFlow<Double?>(null)
    val benchmarkMs: StateFlow<Double?> = _benchmarkMs.asStateFlow()

    fun analyzeUserQuery(query: String, modelManager: SlmModelManager) {
        if (query.isBlank()) return

        _uiState.value = IntentUiState.Loading // 로딩 시작

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val explanation = ExplainManager(modelManager).explain(query)
                _lastExplanation.value = explanation
                _benchmarkMs.value = null
                _uiState.value = IntentUiState.ShowTop3(explanation.top3, explanation) // 결과 화면에 쏘기
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = IntentUiState.Idle
            }
        }
    }

    /** 개발자 화면에서 문장 분석 (AI 홈 결과 카드에는 영향 없음) */
    fun analyzeForDeveloper(query: String, modelManager: SlmModelManager) {
        if (query.isBlank() || _devLoading.value) return
        _devLoading.value = true
        viewModelScope.launch(Dispatchers.Default) {
            try {
                _lastExplanation.value = ExplainManager(modelManager).explain(query)
                _benchmarkMs.value = null
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _devLoading.value = false
            }
        }
    }

    /** 기기 추론 시간 측정: 워밍업 3회 후 20회 평균 (XAI 포함) */
    fun runBenchmark(query: String, modelManager: SlmModelManager) {
        if (query.isBlank() || _devLoading.value) return
        _devLoading.value = true
        viewModelScope.launch(Dispatchers.Default) {
            try {
                _benchmarkMs.value = ExplainManager(modelManager).benchmark(query, runs = 20, warmup = 3)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _devLoading.value = false
            }
        }
    }

    fun resetState() {
        _uiState.value = IntentUiState.Idle
    }
}
