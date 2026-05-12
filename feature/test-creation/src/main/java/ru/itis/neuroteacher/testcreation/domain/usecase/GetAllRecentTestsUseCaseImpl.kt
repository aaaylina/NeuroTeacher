package ru.itis.neuroteacher.testcreation.domain.usecase

import ru.itis.neuroteacher.domain.model.RecentTestItem
import ru.itis.neuroteacher.domain.usecase.GetAllRecentTestsUseCase
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import javax.inject.Inject

class GetAllRecentTestsUseCaseImpl @Inject constructor(
    private val repository: TestRepository
) : GetAllRecentTestsUseCase {
    override suspend fun invoke(): List<RecentTestItem> {
        return repository.getAllTestsForHome()
    }
}