package ru.itis.neuroteacher.testcreation.domain.usecase

import android.graphics.Bitmap
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CameraManager {
    suspend fun startCamera(): Result<Unit>
    fun setupPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner)
    fun releaseCamera()
    suspend fun capturePhotoAndRecognizeText(): Result<PhotoRecognitionResult>
    suspend fun processAndRecognizeImage(bitmap: Bitmap): Result<PhotoRecognitionResult>
    fun isCameraReady(): Boolean
}