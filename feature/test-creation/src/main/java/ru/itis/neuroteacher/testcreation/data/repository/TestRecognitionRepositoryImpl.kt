package ru.itis.neuroteacher.testcreation.data.repository

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.tasks.await
import ru.itis.neuroteacher.testcreation.domain.repository.TextRecognitionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestRecognitionRepositoryImpl @Inject constructor() : TextRecognitionRepository {

    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    override suspend fun recognizeText(bitmap: Bitmap): Result<String> {
        return runCatching {
            val image = InputImage.fromBitmap(bitmap, 0)
            val result = recognizer.process(image).await()
            result.text
        }
    }
}