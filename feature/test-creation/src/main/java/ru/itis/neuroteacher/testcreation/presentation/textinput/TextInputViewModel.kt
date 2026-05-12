package ru.itis.neuroteacher.testcreation.presentation.textinput

import android.content.ClipboardManager
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.usecase.GenerateTestUseCase
import ru.itis.neuroteacher.testcreation.utils.constants.TestGenerationConstants
import javax.inject.Inject

sealed class TextInputNavigationEvent {
    data class NavigateToTest(val test: Test) : TextInputNavigationEvent()
    data object ShowError : TextInputNavigationEvent()
}

data class TextInputUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class TextInputViewModel @Inject constructor(
    private val generateTestUseCase: GenerateTestUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TextInputUiState())
    val uiState: StateFlow<TextInputUiState> = _uiState.asStateFlow()

    private val _navigationEvents = MutableStateFlow<TextInputNavigationEvent?>(null)
    val navigationEvents: StateFlow<TextInputNavigationEvent?> = _navigationEvents.asStateFlow()

    private val _textInput = MutableStateFlow("")
    val textInput: StateFlow<String> = _textInput.asStateFlow()

    fun onTextChange(text: String) {
        if (text.length <= TestGenerationConstants.MAX_TEXT_LENGTH) {
            _textInput.value = text
        }
    }

    fun pasteFromClipboard(context: Context) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (clipboard.hasPrimaryClip() && clipboard.primaryClip?.itemCount ?: 0 > 0) {
            val pastedText = clipboard.primaryClip?.getItemAt(0)?.coerceToText(context).toString()
            _textInput.value = if (pastedText.length > TestGenerationConstants.MAX_TEXT_LENGTH) {
                pastedText.take(TestGenerationConstants.MAX_TEXT_LENGTH)
            } else {
                pastedText
            }
        }
    }

    fun generateTest(text: String, questionCount: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            generateTestUseCase(text, questionCount)
                .onSuccess { test ->
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    _navigationEvents.value = TextInputNavigationEvent.NavigateToTest(test)
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