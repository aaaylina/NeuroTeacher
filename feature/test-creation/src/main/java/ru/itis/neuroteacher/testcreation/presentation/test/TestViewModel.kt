package ru.itis.neuroteacher.testcreation.presentation.test

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.neuroteacher.testcreation.data.TestCache
import ru.itis.neuroteacher.testcreation.data.db.model.SourceType
import ru.itis.neuroteacher.testcreation.domain.model.Question
import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import javax.inject.Inject

private const val TEST_ID_KEY = "testId"
private const val SAVED_TEST_ID_KEY = "savedTestId"

data class TestUiState(
    val currentQuestionIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val answers: List<Int> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val testTitle: String = "",
    val questions: List<Question> = emptyList()
)

@HiltViewModel
class TestViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: TestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TestUiState())
    val uiState: StateFlow<TestUiState> = _uiState.asStateFlow()
    private var _questions: List<Question> = emptyList()
    private var onTestReadyCallback: ((Long) -> Unit)? = null

    private val _savedTestId = MutableStateFlow(0L)
    val savedTestId: StateFlow<Long> = _savedTestId.asStateFlow()

    fun loadTestFromCache(cache: TestCache) {
        val testId = savedStateHandle.get<String>(TEST_ID_KEY)
        if (testId != null) {
            val test = cache.get(testId)
            if (test != null) {
                saveTestToDatabase(test, testId, cache)

                _uiState.value = _uiState.value.copy(
                    testTitle = test.title,
                    questions = test.questions
                )
                _questions = test.questions
            } else {
                _uiState.value = _uiState.value.copy(error = "Тест не найден в кеше")
            }
        } else {
            _uiState.value = _uiState.value.copy(error = "testId не передан")
        }
    }

    fun loadTestFromDatabase(testId: Long) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val test = repository.getTestById(testId)
                if (test != null) {
                    _questions = test.questions
                    _savedTestId.value = testId
                    _uiState.value = _uiState.value.copy(
                        testTitle = test.title,
                        questions = test.questions,
                        isLoading = false
                    )
                    onTestReadyCallback?.invoke(testId)
                    Log.d("TestViewModel", "Test loaded from database with id: $testId")
                } else {
                    _uiState.value = _uiState.value.copy(
                        error = "Тест не найден в базе данных",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                Log.e("TestViewModel", "Error loading test from database", e)
                _uiState.value = _uiState.value.copy(
                    error = "Ошибка загрузки теста: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    private fun saveTestToDatabase(test: Test, cacheId: String, cache: TestCache) {
        viewModelScope.launch {
            try {
                val id = repository.saveTest(test, SourceType.TEXT)
                _savedTestId.value = id
                cache.clear(cacheId)
                savedStateHandle.set(SAVED_TEST_ID_KEY, id)

                Log.d("TestViewModel", "Test saved successfully with id: $id")
                onTestReadyCallback?.invoke(id)
            } catch (e: Exception) {
                Log.e("TestViewModel", "Error saving test", e)
                _uiState.value = _uiState.value.copy(
                    error = "Ошибка сохранения теста: ${e.message}"
                )
            }
        }
    }

    fun setOnTestReadyCallback(callback: (Long) -> Unit) {
        onTestReadyCallback = callback
        if (_savedTestId.value != 0L) {
            callback(_savedTestId.value)
        }
    }

    fun selectOption(index: Int) {
        if (_uiState.value.selectedOptionIndex != null) return
        _uiState.value = _uiState.value.copy(
            selectedOptionIndex = index,
            answers = _uiState.value.answers.toMutableList().also {
                if (it.size <= _uiState.value.currentQuestionIndex) it.add(index)
                else it[_uiState.value.currentQuestionIndex] = index
            }
        )
    }

    fun nextQuestion() {
        if (_uiState.value.selectedOptionIndex == null) return
        val currentIndex = _uiState.value.currentQuestionIndex
        if (currentIndex < _questions.size - 1) {
            _uiState.value = _uiState.value.copy(
                currentQuestionIndex = currentIndex + 1,
                selectedOptionIndex = null
            )
        }
    }



    fun finishTest(onResultSaved: (Long) -> Unit) {
        val state = _uiState.value
        val correctCount = state.answers.foldIndexed(0) { idx, acc, answerIdx ->
            if (idx < _questions.size && answerIdx == _questions[idx].correctIndex) acc + 1 else acc
        }

        viewModelScope.launch {
            val testIdToUse = _savedTestId.value
                .takeIf { it != 0L }
                ?: savedStateHandle.get<Long>(SAVED_TEST_ID_KEY)
                ?: run {
                    _uiState.value = _uiState.value.copy(error = "ID теста не найден")
                    return@launch
                }

            val resultId = repository.saveResult(
                testId = testIdToUse,
                totalQuestions = _questions.size,
                correctAnswers = correctCount,
                scorePercentage = if (_questions.isNotEmpty()) {
                    (correctCount.toFloat() / _questions.size) * 100
                } else 0f,
                answers = state.answers
            )
            onResultSaved(resultId)
        }
    }


    fun getTestId(): Long {
        if (_savedTestId.value != 0L) {
            return _savedTestId.value
        }
        return savedStateHandle.get<Long>(SAVED_TEST_ID_KEY) ?: 0L
    }

    fun getCurrentQuestion(): Question? =
        _questions.getOrNull(_uiState.value.currentQuestionIndex)

    fun previousQuestion() {
        val currentIndex = _uiState.value.currentQuestionIndex
        if (currentIndex > 0) {
            val previousAnswers = _uiState.value.answers
            val previousSelectedIndex = if (currentIndex - 1 < previousAnswers.size) {
                previousAnswers[currentIndex - 1]
            } else {
                null
            }
            _uiState.value = _uiState.value.copy(
                currentQuestionIndex = currentIndex - 1,
                selectedOptionIndex = previousSelectedIndex
            )
        }
    }

}