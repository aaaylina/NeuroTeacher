package ru.itis.neuroteacher.testcreation.domain.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import javax.inject.Inject

class LoadBitmapUseCaseImpl @Inject constructor() : LoadBitmapUseCase {

    override fun invoke(imageUri: String): Bitmap? {
        return runCatching {
            val file = File(imageUri)
            if (file.exists()) {
                BitmapFactory.decodeFile(imageUri)
            } else {
                null
            }
        }.getOrNull()
    }
}