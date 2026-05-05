package ru.itis.neuroteacher.testcreation.domain.usecase

import ru.itis.neuroteacher.testcreation.domain.model.Test

interface SyncTestToFirebaseUseCase {
    suspend fun syncCompleteTest(
        test: Test,
        resultId: Long,
        answers: List<Int>,
        correctCount: Int,
        scorePercentage: Float
    ): Result<String>
}