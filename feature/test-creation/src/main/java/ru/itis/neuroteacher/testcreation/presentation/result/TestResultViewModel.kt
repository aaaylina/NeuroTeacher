package ru.itis.neuroteacher.testcreation.presentation.result

import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.testcreation.domain.model.TestResult
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import ru.itis.neuroteacher.testcreation.navigation.model.TestResultRoute
import javax.inject.Inject

internal data class TestResultUiState(
    val testId: Long = 0L,
    val isLoading: Boolean = true,
    @StringRes val errorResId: Int? = null,
    val testTitle: String = "",
    val totalQuestions: Int = 0,
    val correctAnswers: Int = 0,
    val scorePercentage: Float = 0f,
    val questions: List<TestResult.QuestionResult> = emptyList()
)

@HiltViewModel
internal class TestResultViewModel @Inject constructor(
    private val repository: TestRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val route: TestResultRoute = savedStateHandle.toRoute()
    private val resultId: Long = route.resultId

    private val _uiState = MutableStateFlow(TestResultUiState())
    val uiState: StateFlow<TestResultUiState> = _uiState.asStateFlow()

    init {
        loadResultData()
    }

    private fun loadResultData() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    testId = route.testId,
                    isLoading = true,
                    errorResId = null
                )
            }

            val result = runCatching {
                repository.getResultById(resultId)
            }.onFailure { e ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorResId = R.string.test_result_load_error
                    )
                }
            }.getOrNull()

            if (result != null) {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        errorResId = null,
                        testTitle = result.testTitle,
                        totalQuestions = result.totalQuestions,
                        correctAnswers = result.correctAnswers,
                        scorePercentage = result.scorePercentage,
                        questions = result.questions
                    )
                }
            } else if (_uiState.value.errorResId == null) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorResId = R.string.test_result_not_found
                    )
                }
            }
        }
    }
}