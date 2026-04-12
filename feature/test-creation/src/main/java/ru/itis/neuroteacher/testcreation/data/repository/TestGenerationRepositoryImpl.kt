package ru.itis.neuroteacher.testcreation.data.repository

import ru.itis.neuroteacher.testcreation.data.datasource.TestRemoteDataSource
import ru.itis.neuroteacher.testcreation.data.mapper.TestMapper
import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.repository.TestGenerationRepository
import javax.inject.Inject

internal class TestGenerationRepositoryImpl  @Inject constructor(
    private val remoteDataSource: TestRemoteDataSource,
    private val mapper: TestMapper
) : TestGenerationRepository{

    override suspend fun generateTest(text: String, questionCount: Int): Result<Test> {
        return remoteDataSource.generateTest(text, questionCount).fold(
            onSuccess = { dto ->
                val dataModel = mapper.toDataModel(dto)
                Result.success(mapper.toDomain(dataModel))
            },
            onFailure = { e ->
                Result.failure(Exception("Test generation failed: ${e.message}"))
            }
        )
    }
}