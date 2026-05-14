package ru.itis.neuroteacher.testcreation.presentation.test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.neuroteacher.testcreation.data.TestCache
import ru.itis.neuroteacher.testcreation.data.db.model.SourceType
import ru.itis.neuroteacher.testcreation.domain.model.Question
import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import ru.itis.neuroteacher.testcreation.domain.usecase.SyncTestToFirebaseUseCase
import javax.inject.Inject

data class TestUiState(
    val currentQuestionIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val answers: List<Int> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val testTitle: String = "",
    val questions: List<Question> = emptyList(),
    val savedTestId: Long = 0L
)

sealed class TestEvent {
    data class NavigateToResults(val testId: Long, val resultId: Long) : TestEvent()
}

@HiltViewModel
internal class TestViewModel @Inject constructor(
    private val repository: TestRepository,
    private val syncUseCase: SyncTestToFirebaseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TestUiState())
    val uiState: StateFlow<TestUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<TestEvent>()
    val events = _events.asSharedFlow()

    private var _questions: List<Question> = emptyList()

    fun loadTestFromCache(testId: String) {
        val test = TestCache.get(testId)
        if (test != null) {
            saveTestToDatabase(test, testId)

            _uiState.update {
                it.copy(
                    testTitle = test.title,
                    questions = test.questions
                )
            }
            _questions = test.questions
        } else {
            _uiState.update { it.copy(error = "Тест не найден в кэше") }
        }
    }

    fun loadTestFromDatabase(testId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val test = repository.getTestById(testId)
                if (test != null) {
                    _questions = test.questions
                    _uiState.update {
                        it.copy(
                            testTitle = test.title,
                            questions = test.questions,
                            savedTestId = testId,
                            isLoading = false
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            error = "Тест не найден в базе данных",
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = "Ошибка загрузки теста: ${e.message}",
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun saveTestToDatabase(test: Test, cacheId: String) {
        viewModelScope.launch {
            try {
                val id = repository.saveTest(test, SourceType.TEXT)
                _uiState.update { it.copy(savedTestId = id) }
                TestCache.clear(cacheId)
                repository.getOrCreateRemoteQuizId(id, test).onFailure { e ->
                    Log.e("TestViewModel", "Не удалось сразу сохранить тест в облако", e)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = "Ошибка сохранения теста: ${e.message}")
                }
            }
        }
    }

    fun selectOption(index: Int) {
        if (_uiState.value.selectedOptionIndex != null) return
        _uiState.update { state ->
            val newAnswers = state.answers.toMutableList().apply {
                if (size <= state.currentQuestionIndex) add(index)
                else set(state.currentQuestionIndex, index)
            }
            state.copy(
                selectedOptionIndex = index,
                answers = newAnswers
            )
        }
    }

    fun nextQuestion() {
        if (_uiState.value.selectedOptionIndex == null) return
        val currentIndex = _uiState.value.currentQuestionIndex
        if (currentIndex < _questions.lastIndex) {
            _uiState.update {
                it.copy(
                    currentQuestionIndex = currentIndex + 1,
                    selectedOptionIndex = null
                )
            }
        }
    }

    fun previousQuestion() {
        val currentIndex = _uiState.value.currentQuestionIndex
        if (currentIndex > 0) {
            val previousAnswers = _uiState.value.answers
            val previousSelectedIndex = if (currentIndex - 1 < previousAnswers.size) {
                previousAnswers[currentIndex - 1]
            } else {
                null
            }
            _uiState.update {
                it.copy(
                    currentQuestionIndex = currentIndex - 1,
                    selectedOptionIndex = previousSelectedIndex
                )
            }
        }
    }

    fun finishTest() {
        val state = _uiState.value
        val correctCount = state.answers.foldIndexed(0) { idx, acc, answerIdx ->
            if (idx < _questions.size && answerIdx == _questions[idx].correctIndex) acc + 1 else acc
        }

        viewModelScope.launch {
            val testIdToUse = _uiState.value.savedTestId
                .takeIf { it != 0L }
                ?: run {
                    _uiState.update { it.copy(error = "ID теста не найден") }
                    return@launch
                }

            val resultId = repository.saveResult(
                testId = testIdToUse,
                totalQuestions = _questions.size,
                correctAnswers = correctCount,
                scorePercentage = if (_questions.isNotEmpty()) {
                    (correctCount.toFloat() / _questions.size) * 100
                } else {
                    0f
                },
                answers = state.answers
            )

            val test = Test(_uiState.value.testTitle, _questions)
            syncUseCase.syncCompleteTest(
                test = test,
                resultId = resultId,
                answers = state.answers,
                correctCount = correctCount,
                scorePercentage = (correctCount.toFloat() / _questions.size) * 100
            ).onSuccess { firebaseId ->
            }.onFailure { error ->
            }

            _events.emit(TestEvent.NavigateToResults(testIdToUse, resultId))
        }
    }

    fun getCurrentQuestion(): Question? =
        _questions.getOrNull(_uiState.value.currentQuestionIndex)
}