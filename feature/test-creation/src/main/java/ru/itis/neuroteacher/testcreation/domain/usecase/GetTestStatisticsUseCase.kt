package ru.itis.neuroteacher.testcreation.domain.usecase

import ru.itis.neuroteacher.testcreation.domain.model.TestStatistics

interface GetTestStatisticsUseCase {
    suspend operator fun invoke(): TestStatistics
}