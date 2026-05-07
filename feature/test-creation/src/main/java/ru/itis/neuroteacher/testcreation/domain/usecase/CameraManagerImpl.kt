package ru.itis.neuroteacher.testcreation.domain.usecase

import android.graphics.Bitmap
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import ru.itis.neuroteacher.testcreation.domain.repository.CameraRepository
import ru.itis.neuroteacher.testcreation.domain.repository.TextRecognitionRepository
import javax.inject.Inject

data class PhotoRecognitionResult(
    val imageUri: Uri,
    val recognizedText: String,
    val bitmap: Bitmap
)

internal class CameraManagerImpl @Inject constructor(
    private val cameraRepository: CameraRepository,
    private val textRecognitionRepository: TextRecognitionRepository
) : CameraManager {

    override fun isCameraReady(): Boolean = cameraRepository.isCameraReady()

    override suspend fun startCamera(): Result<Unit> = runCatching {
        cameraRepository.startCamera()
    }

    override fun setupPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner) {
        cameraRepository.setupPreview(previewView, lifecycleOwner)
    }

    override fun releaseCamera() {
        cameraRepository.releaseCamera()
    }

    override suspend fun capturePhotoAndRecognizeText(): Result<PhotoRecognitionResult> = runCatching {
        val bitmap = cameraRepository.capturePhoto()
            .getOrElse { throw CameraException("Не удалось сделать фото", it) }

        processAndRecognizeImage(bitmap).getOrThrow()
    }

    override suspend fun processAndRecognizeImage(bitmap: Bitmap): Result<PhotoRecognitionResult> = runCatching {
        val uri = cameraRepository.saveBitmap(bitmap)
            .getOrElse { throw SaveException("Не удалось сохранить фото", it) }

        val recognizedText = textRecognitionRepository.recognizeText(bitmap)
            .getOrElse { throw RecognitionException("Не удалось распознать текст", it) }

        PhotoRecognitionResult(uri, recognizedText.trim(), bitmap)
    }
}

internal class CameraException(message: String, cause: Throwable) : Exception(message, cause)
internal class SaveException(message: String, cause: Throwable) : Exception(message, cause)
internal class RecognitionException(message: String, cause: Throwable) : Exception(message, cause)