package ru.itis.neuroteacher.testcreation.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import ru.itis.neuroteacher.testcreation.domain.repository.CameraRepository
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class CameraRepositoryImpl @Inject constructor(
    @param:ApplicationContext private val appContext: Context
) : CameraRepository {

    private var cameraProvider: ProcessCameraProvider? = null
    private var imageCapture: ImageCapture? = null

    private val cameraExecutor: ExecutorService by lazy { Executors.newSingleThreadExecutor() }
    private val mainExecutor = ContextCompat.getMainExecutor(appContext)

    override suspend fun startCamera() = suspendCancellableCoroutine<Unit> { continuation ->
        val cameraProviderFuture = ProcessCameraProvider.getInstance(appContext)

        cameraProviderFuture.addListener({
            runCatching {
                cameraProvider = cameraProviderFuture.get()
                imageCapture = ImageCapture.Builder()
                    .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                    .build()
            }.onSuccess {
                continuation.resume(Unit)
            }.onFailure { e ->
                continuation.resumeWithException(e)
            }
        }, mainExecutor)

        continuation.invokeOnCancellation {
            releaseCamera()
        }
    }

    override fun setupPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner) {
        val preview = Preview.Builder().build().apply {
            setSurfaceProvider(previewView.surfaceProvider)
        }

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        runCatching {
            cameraProvider?.apply {
                unbindAll()
                bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            }
        }.onFailure { e ->
            e.printStackTrace()
        }
    }

    override suspend fun capturePhoto(): Result<Bitmap> = runCatching {
        val capture = imageCapture ?: throw Exception("Камера не инициализирована")

        suspendCancellableCoroutine { continuation ->
            capture.takePicture(
                cameraExecutor,
                object : ImageCapture.OnImageCapturedCallback() {
                    override fun onCaptureSuccess(image: ImageProxy) {
                        val bitmap = image.use { proxy ->
                            imageProxyToBitmap(proxy)
                        }
                        if (bitmap != null) {
                            continuation.resume(bitmap)
                        } else {
                            continuation.resumeWithException(Exception("Ошибка конвертации изображения"))
                        }
                    }

                    override fun onError(exception: ImageCaptureException) {
                        continuation.resumeWithException(exception)
                    }
                }
            )
        }
    }

    private fun imageProxyToBitmap(image: ImageProxy): Bitmap? = runCatching {
        val buffer = image.planes[0].buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)

        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        val matrix = Matrix().apply {
            postRotate(image.imageInfo.rotationDegrees.toFloat())
        }

        Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }.getOrNull()

    override suspend fun saveBitmap(bitmap: Bitmap): Result<Uri> = runCatching {
        val file = File(appContext.cacheDir, "photo_${System.currentTimeMillis()}.jpg")
        file.outputStream().use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        }
        Uri.fromFile(file)
    }

    override fun releaseCamera() {
        runCatching {
            cameraProvider?.unbindAll()
        }
        cameraProvider = null
        imageCapture = null
    }
}