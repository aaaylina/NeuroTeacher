package ru.itis.neuroteacher.testcreation.domain.usecase

import ru.itis.neuroteacher.testcreation.domain.model.TestStatistics
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import javax.inject.Inject

internal class GetTestStatisticsUseCaseImpl @Inject constructor(
    private val repository: TestRepository
) : GetTestStatisticsUseCase {

    override suspend fun invoke(): TestStatistics {
        return try {
            val remoteStats = repository.getRemoteTestStatistics().getOrNull()
            if (remoteStats != null && remoteStats.totalTests > 0) {
                remoteStats
            } else {
                TestStatistics(
                    totalTests = repository.getTotalTestsCount(),
                    completedTests = repository.getTotalCompletedTestsCount(),
                    averageScore = repository.getAverageScore() ?: 0f,
                    bestScore = repository.getBestScore() ?: 0f
                )
            }
        } catch (e: Exception) {
            TestStatistics(
                totalTests = repository.getTotalTestsCount(),
                completedTests = repository.getTotalCompletedTestsCount(),
                averageScore = repository.getAverageScore() ?: 0f,
                bestScore = repository.getBestScore() ?: 0f
            )
        }
    }
}