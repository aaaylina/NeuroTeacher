package ru.itis.neuroteacher.testcreation.domain.usecase

import android.graphics.Bitmap

interface RecognizeTextUseCase {
    suspend operator fun invoke(bitmap: Bitmap): Result<String>
}