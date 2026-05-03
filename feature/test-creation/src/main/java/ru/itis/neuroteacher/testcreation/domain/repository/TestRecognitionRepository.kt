package ru.itis.neuroteacher.testcreation.domain.repository

import android.graphics.Bitmap

interface TextRecognitionRepository {
    suspend fun recognizeText(bitmap: Bitmap): Result<String>
}