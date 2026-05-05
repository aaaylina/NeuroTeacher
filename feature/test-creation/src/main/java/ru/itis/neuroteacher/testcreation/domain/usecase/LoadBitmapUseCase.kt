package ru.itis.neuroteacher.testcreation.domain.usecase

import android.graphics.Bitmap

interface LoadBitmapUseCase {
    operator fun invoke(imageUri: String): Bitmap?
}