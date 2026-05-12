package ru.itis.neuroteacher.domain.usecase

import ru.itis.neuroteacher.domain.model.RecentTestItem

interface GetAllRecentTestsUseCase {
    suspend operator fun invoke(): List<RecentTestItem>
}