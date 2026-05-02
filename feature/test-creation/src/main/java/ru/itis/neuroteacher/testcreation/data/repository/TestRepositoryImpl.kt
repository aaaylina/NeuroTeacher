package ru.itis.neuroteacher.testcreation.data.repository

import ru.itis.neuroteacher.testcreation.data.db.dao.TestDao
import ru.itis.neuroteacher.testcreation.data.db.model.SourceType
import ru.itis.neuroteacher.testcreation.data.mapper.TestMapper
import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.model.TestResult
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val dao: TestDao,
    private val mapper: TestMapper
) : TestRepository {

    override suspend fun saveTest(test: Test, sourceType: SourceType): Long {
        val entity = mapper.toEntity(test, sourceType)
        return dao.insertTest(entity)
    }

    override suspend fun getTestById(id: Long): Test? {
        val entity = dao.getTestById(id) ?: return null
        return mapper.toDomain(entity)
    }

    override suspend fun getAllTests(): List<Test> {
        return dao.getAllTests().map { mapper.toDomain(it) }
    }

    override suspend fun saveResult(
        testId: Long,
        totalQuestions: Int,
        correctAnswers: Int,
        scorePercentage: Float,
        answers: List<Int>
    ): Long {
        val entity = mapper.toResultEntity(
            testId = testId,
            totalQuestions = totalQuestions,
            correctAnswers = correctAnswers,
            scorePercentage = scorePercentage,
            answers = answers
        )
        return dao.insertResult(entity)
    }

    override suspend fun getResultById(resultId: Long): TestResult? {
        val resultEntity = dao.getResultById(resultId) ?: return null
        val testEntity = dao.getTestById(resultEntity.testId) ?: return null
        return mapper.toDomainResult(testEntity, resultEntity)
    }

    override suspend fun getResultsByTestId(testId: Long): List<TestResult> {
        val testEntity = dao.getTestById(testId) ?: return emptyList()
        val resultEntities = dao.getResultsByTestId(testId)
        return resultEntities.map { mapper.toDomainResult(testEntity, it) }
    }
}