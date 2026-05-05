package ru.itis.neuroteacher.testcreation.domain.usecase

import android.graphics.Bitmap
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CameraUseCase {
    suspend fun startCamera(lifecycleOwner: LifecycleOwner): Result<Unit>

    fun setupPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner)

    fun releaseCamera()

    suspend fun capturePhotoAndRecognizeText(): Result<PhotoRecognitionResult>

    suspend fun processAndRecognizeImage(bitmap: Bitmap): Result<PhotoRecognitionResult>
}