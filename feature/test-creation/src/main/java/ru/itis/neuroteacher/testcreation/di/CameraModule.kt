package ru.itis.neuroteacher.testcreation.di

import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.testcreation.data.repository.TestRecognitionRepositoryImpl
import ru.itis.neuroteacher.testcreation.domain.repository.TextRecognitionRepository
import ru.itis.neuroteacher.testcreation.domain.usecase.RecognizeTextUseCase
import ru.itis.neuroteacher.testcreation.domain.usecase.RecognizeTextUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CameraModule {

    @Binds
    @Singleton
    abstract fun bindTextRecognitionRepository(
        impl: TestRecognitionRepositoryImpl
    ): TextRecognitionRepository

    @Binds
    internal abstract fun bindRecognizeTextUseCase(
        impl: RecognizeTextUseCaseImpl
    ): RecognizeTextUseCase

    companion object {
        @Provides
        fun provideTextRecognizer(): TextRecognizer {
            return TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        }
    }

}