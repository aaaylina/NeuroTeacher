package ru.itis.neuroteacher.testcreation.domain.repository

import android.graphics.Bitmap
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CameraRepository {
    suspend fun startCamera(lifecycleOwner: LifecycleOwner)
    fun setupPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner)
    suspend fun capturePhoto(): Result<Bitmap>
    suspend fun saveBitmap(bitmap: Bitmap): Result<Uri>
    fun releaseCamera()
}