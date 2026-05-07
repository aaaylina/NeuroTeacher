package ru.itis.neuroteacher.testcreation.presentation.camera

import android.graphics.Bitmap
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.neuroteacher.testcreation.domain.usecase.CameraManager
import javax.inject.Inject

sealed class CameraNavigationEvent {
    data class NavigateToPhotoDemo(val imageUri: String, val recognizedText: String) : CameraNavigationEvent()
    data object ShowError : CameraNavigationEvent()
}

data class CameraUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val cameraManager: CameraManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(CameraUiState())
    val uiState: StateFlow<CameraUiState> = _uiState.asStateFlow()

    private val _navigationEvents = MutableStateFlow<CameraNavigationEvent?>(null)
    val navigationEvents: StateFlow<CameraNavigationEvent?> = _navigationEvents.asStateFlow()

    fun startCamera(lifecycleOwner: LifecycleOwner) {
        viewModelScope.launch {
            cameraManager.startCamera().onFailure { e ->
                handleError(e.message)
            }
        }
    }

    fun setupPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner) {
        cameraManager.setupPreview(previewView, lifecycleOwner)
    }

    fun capturePhoto() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            cameraManager.capturePhotoAndRecognizeText().fold(
                onSuccess = { result ->
                    _uiState.update { it.copy(isLoading = false) }
                    _navigationEvents.update {
                        CameraNavigationEvent.NavigateToPhotoDemo(
                            imageUri = result.imageUri.toString(),
                            recognizedText = result.recognizedText
                        )
                    }
                },
                onFailure = { exception ->
                    handleError(exception.message)
                }
            )
        }
    }

    fun processSelectedImage(bitmap: Bitmap) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            cameraManager.processAndRecognizeImage(bitmap).fold(
                onSuccess = { result ->
                    _uiState.update { it.copy(isLoading = false) }
                    _navigationEvents.update {
                        CameraNavigationEvent.NavigateToPhotoDemo(
                            imageUri = result.imageUri.toString(),
                            recognizedText = result.recognizedText
                        )
                    }
                },
                onFailure = { exception ->
                    handleError(exception.message)
                }
            )
        }
    }

    private fun handleError(errorMessage: String?) {
        _uiState.update {
            it.copy(
                isLoading = false,
                error = errorMessage ?: "Неизвестная ошибка"
            )
        }
        _navigationEvents.update { CameraNavigationEvent.ShowError }
    }

    fun onEventConsumed() {
        _navigationEvents.update { null }
    }

    override fun onCleared() {
        super.onCleared()
        cameraManager.releaseCamera()
    }
}