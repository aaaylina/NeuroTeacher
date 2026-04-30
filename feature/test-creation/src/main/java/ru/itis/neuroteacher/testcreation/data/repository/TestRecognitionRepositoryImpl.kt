package ru.itis.neuroteacher.testcreation.data.repository

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognizer
import kotlinx.coroutines.tasks.await
import ru.itis.neuroteacher.testcreation.domain.repository.TextRecognitionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestRecognitionRepositoryImpl @Inject constructor(
    private val recognizer: TextRecognizer
) : TextRecognitionRepository {

    override suspend fun recognizeText(bitmap: Bitmap): Result<String> {
        return runCatching {
            val image = InputImage.fromBitmap(bitmap, 0)
            val result = recognizer.process(image).await()
            result.text
        }
    }
}