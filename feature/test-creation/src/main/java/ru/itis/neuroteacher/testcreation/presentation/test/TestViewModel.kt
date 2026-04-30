package ru.itis.neuroteacher.testcreation.presentation.test

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import ru.itis.neuroteacher.testcreation.data.TestCache
import ru.itis.neuroteacher.testcreation.domain.model.Question
import javax.inject.Inject

private const val TEST_ID_KEY = "testId"

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
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(TestUiState())
    val uiState: StateFlow<TestUiState> = _uiState.asStateFlow()
    private var _questions: List<Question> = emptyList()
    private var _testTitle: String = ""
    fun loadTestFromCache(cache: TestCache) {
        val testId = savedStateHandle.get<String>(TEST_ID_KEY)
        if (testId != null) {
            val test = cache.get(testId)
            if (test != null) {
                _testTitle = test.title
                _questions = test.questions

                _uiState.value = _uiState.value.copy(
                    testTitle = test.title,
                    questions = test.questions
                )
            } else {
                _uiState.value = _uiState.value.copy(error = "Тест не найден в кеше")
            }
        } else {
            _uiState.value = _uiState.value.copy(error = "testId не передан")
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

    fun finishTest(): TestResult {
        val state = _uiState.value
        val correctCount = state.answers.foldIndexed(0) { idx, acc, answerIdx ->
            if (idx < _questions.size && answerIdx == _questions[idx].correctIndex) acc + 1 else acc
        }
        return TestResult(
            totalQuestions = _questions.size,
            correctAnswers = correctCount,
            testTitle = _testTitle
        )
    }

    fun getCurrentQuestion(): Question? =
        _questions.getOrNull(_uiState.value.currentQuestionIndex)

    @Serializable
    data class TestResult(
        val totalQuestions: Int,
        val correctAnswers: Int,
        val testTitle: String
    )
}