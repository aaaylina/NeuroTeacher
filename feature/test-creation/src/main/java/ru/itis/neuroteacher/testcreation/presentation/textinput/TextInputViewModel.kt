package ru.itis.neuroteacher.testcreation.presentation.textinput

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import ru.itis.neuroteacher.testcreation.data.model.QuestionDataModel
import ru.itis.neuroteacher.testcreation.data.model.TestDataModel
import ru.itis.neuroteacher.testcreation.domain.usecase.GenerateTestUseCase
import javax.inject.Inject

sealed class TextInputNavigationEvent {
    data class NavigateToTest(val title: String, val questionsJson: String) : TextInputNavigationEvent()
    data object ShowError : TextInputNavigationEvent()
}

data class TextInputUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class TextInputViewModel @Inject constructor(
    private val generateTestUseCase: GenerateTestUseCase,
    private val json: Json
) : ViewModel() {

    private val _uiState = MutableStateFlow(TextInputUiState())
    val uiState: StateFlow<TextInputUiState> = _uiState.asStateFlow()

    private val _navigationEvents = MutableStateFlow<TextInputNavigationEvent?>(null)
    val navigationEvents: StateFlow<TextInputNavigationEvent?> = _navigationEvents.asStateFlow()

    fun generateTest(text: String, questionCount: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            generateTestUseCase(text, questionCount)
                .onSuccess { test ->
                    val dataModel = TestDataModel(
                        title = test.title,
                        questions = test.questions.map { q ->
                            QuestionDataModel(
                                text = q.text,
                                options = q.options,
                                correctIndex = q.correctIndex,
                                explanation = q.explanation
                            )
                        }
                    )
                    val testJson = json.encodeToString(dataModel)

                    _navigationEvents.value = TextInputNavigationEvent.NavigateToTest(
                        title = test.title,
                        questionsJson = testJson
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message ?: "Генерация не удалась"
                    )
                    _navigationEvents.value = TextInputNavigationEvent.ShowError
                }
        }
    }

    fun onEventConsumed() { _navigationEvents.value = null }
    fun clearError() { _uiState.value = _uiState.value.copy(error = null) }
}