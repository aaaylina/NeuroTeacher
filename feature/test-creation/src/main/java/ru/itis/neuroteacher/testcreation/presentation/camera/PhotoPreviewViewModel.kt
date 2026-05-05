package ru.itis.neuroteacher.testcreation.presentation.camera

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.neuroteacher.testcreation.data.TestCache
import ru.itis.neuroteacher.testcreation.domain.usecase.GenerateTestUseCase
import ru.itis.neuroteacher.testcreation.navigation.model.PhotoPreviewRoute
import ru.itis.neuroteacher.testcreation.utils.constants.TestGenerationConstants
import javax.inject.Inject

sealed class PhotoPreviewNavigationEvent {
    data class NavigateToTest(val testId: String) : PhotoPreviewNavigationEvent()
    data object NavigateBackToCamera : PhotoPreviewNavigationEvent()
}

data class PhotoPreviewUiState(
    val imageUri: String = "",
    val recognizedText: String = "",
    val isGeneratingTest: Boolean = false,
    val selectedQuestionCount: Int = TestGenerationConstants.QUESTION_COUNT_OPTIONS.first(),
    val error: String? = null
)

@HiltViewModel
class PhotoPreviewViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val generateTestUseCase: GenerateTestUseCase,
) : ViewModel() {

    private val route: PhotoPreviewRoute = savedStateHandle.toRoute()

    private val _uiState = MutableStateFlow(
        PhotoPreviewUiState(
            imageUri = route.imageUri,
            recognizedText = route.recognizedText
        )
    )
    val uiState: StateFlow<PhotoPreviewUiState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<PhotoPreviewNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun updateQuestionCount(count: Int) {
        _uiState.update { it.copy(selectedQuestionCount = count) }
    }

    fun onRetakeClick() {
        viewModelScope.launch {
            _navigationEvent.emit(PhotoPreviewNavigationEvent.NavigateBackToCamera)
        }
    }

    fun generateTestFromText() {
        val text = _uiState.value.recognizedText
        val questionCount = _uiState.value.selectedQuestionCount

        if (text.isBlank()) {
            _uiState.update { it.copy(error = "Текст для генерации теста отсутствует") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isGeneratingTest = true, error = null) }

            val result = generateTestUseCase(text, questionCount)

            result.fold(
                onSuccess = { test ->
                    val cacheId = TestCache.save(test)
                    _uiState.update { it.copy(isGeneratingTest = false) }
                    _navigationEvent.emit(PhotoPreviewNavigationEvent.NavigateToTest(cacheId))
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isGeneratingTest = false,
                            error = error.message ?: "Ошибка генерации теста"
                        )
                    }
                }
            )
        }
    }
}