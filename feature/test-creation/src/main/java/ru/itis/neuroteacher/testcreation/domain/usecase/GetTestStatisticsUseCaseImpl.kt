package ru.itis.neuroteacher.testcreation.domain.usecase

import javax.inject.Inject
import ru.itis.neuroteacher.testcreation.domain.model.TestStatistics
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository

internal class GetTestStatisticsUseCaseImpl @Inject constructor(
    private val repository: TestRepository
) : GetTestStatisticsUseCase {

    override suspend fun invoke(): TestStatistics {
        return TestStatistics(
            totalTests = repository.getTotalTestsCount(),
            completedTests = repository.getTotalCompletedTestsCount(),
            averageScore = repository.getAverageScore() ?: 0f,
            bestScore = repository.getBestScore() ?: 0f
        )
    }
}