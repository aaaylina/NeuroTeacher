package ru.itis.neuroteacher.testcreation.presentation.result

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.neuroteacher.testcreation.domain.model.TestResult
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import ru.itis.neuroteacher.testcreation.navigation.model.TestResultRoute
import javax.inject.Inject

data class TestResultUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val testTitle: String = "",
    val totalQuestions: Int = 0,
    val correctAnswers: Int = 0,
    val scorePercentage: Float = 0f,
    val questions: List<TestResult.QuestionResult> = emptyList()
)

@HiltViewModel
class TestResultViewModel @Inject constructor(
    private val repository: TestRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val route: TestResultRoute = savedStateHandle.toRoute()
    val testId: Long = route.testId
    private val resultId: Long = route.resultId

    private val _uiState = MutableStateFlow(TestResultUiState())
    val uiState: StateFlow<TestResultUiState> = _uiState.asStateFlow()

    init {
        loadResultData()
    }

    private fun loadResultData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val result = repository.getResultById(resultId)

                if (result == null) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Результат не найден"
                    )
                    return@launch
                }

                _uiState.value = TestResultUiState(
                    isLoading = false,
                    testTitle = result.testTitle,
                    totalQuestions = result.totalQuestions,
                    correctAnswers = result.correctAnswers,
                    scorePercentage = result.scorePercentage,
                    questions = result.questions
                )

                Log.d("TestResultViewModel", "Loaded result for test: ${result.testTitle}")
            } catch (e: Exception) {
                Log.e("TestResultViewModel", "Error loading result", e)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Ошибка загрузки результатов: ${e.message}"
                )
            }
        }
    }
}