package ru.itis.neuroteacher.testcreation.domain.usecase

import android.graphics.Bitmap
import ru.itis.neuroteacher.testcreation.domain.repository.TextRecognitionRepository
import javax.inject.Inject

internal class RecognizeTextUseCaseImpl @Inject constructor(
    private val repository: TextRecognitionRepository
) : RecognizeTextUseCase {

    override suspend fun invoke(bitmap: Bitmap): Result<String> {
        if (bitmap.width == 0 || bitmap.height == 0){
            return Result.failure(Exception("Invalid bitmap"))
        }
        return repository.recognizeText(bitmap)
    }
}