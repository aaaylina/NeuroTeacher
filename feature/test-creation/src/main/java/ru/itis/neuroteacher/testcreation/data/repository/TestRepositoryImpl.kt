package ru.itis.neuroteacher.testcreation.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ru.itis.neuroteacher.testcreation.data.db.dao.TestDao
import ru.itis.neuroteacher.testcreation.data.db.dao.TestResultDao
import ru.itis.neuroteacher.testcreation.data.db.model.SourceType
import ru.itis.neuroteacher.testcreation.data.mapper.TestMapper
import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.model.TestResult
import ru.itis.neuroteacher.testcreation.domain.model.TestStatistics
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import javax.inject.Inject

internal class TestRepositoryImpl @Inject constructor(
    private val dao: TestDao,
    private val testResultDao: TestResultDao,
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
        return dao.getAllTestsSortedByDateDesc().map { mapper.toDomain(it) }
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
        return testResultDao.insertResult(entity)
    }

    override suspend fun getResultById(resultId: Long): TestResult? {
        val resultEntity = testResultDao.getResultById(resultId) ?: return null
        val testEntity = dao.getTestById(resultEntity.testId) ?: return null
        return mapper.toDomainResult(testEntity, resultEntity)
    }

    override suspend fun getResultsByTestId(testId: Long): List<TestResult> {
        val testEntity = dao.getTestById(testId) ?: return emptyList()
        val resultEntities = testResultDao.getResultsByTestIdSortedByDateDesc(testId)
        return resultEntities.map { mapper.toDomainResult(testEntity, it) }
    }

    override suspend fun getTotalTestsCount(): Int {
        return dao.getTotalTestsCount()
    }

    override suspend fun getTotalCompletedTestsCount(): Int {
        return testResultDao.getTotalCompletedTestsCount()
    }

    override suspend fun getAverageScore(): Float? {
        return testResultDao.getAverageScore()
    }

    override suspend fun getBestScore(): Float? {
        return testResultDao.getBestScore()
    }

    override suspend fun getTestStatistics(): TestStatistics {
        return TestStatistics(
            totalTests = getTotalTestsCount(),
            completedTests = getTotalCompletedTestsCount(),
            averageScore = getAverageScore() ?: 0f,
            bestScore = getBestScore() ?: 0f
        )
    }

    override suspend fun clearAllData() {
        dao.deleteAllTests()
        testResultDao.deleteAllResults()
    }

    override suspend fun getAllTestResults(): List<TestResult> {
        val results = testResultDao.getAllResultsFlowSortedByDateDesc().first()
        return results.mapNotNull { resultEntity ->
            val testEntity = dao.getTestById(resultEntity.testId) ?: return@mapNotNull null
            mapper.toDomainResult(testEntity, resultEntity)
        }
    }
    override fun getTestResultsFlow(query: String): Flow<List<TestResult>> {
        return if (query.isBlank()) {
            testResultDao.getAllResultsFlowSortedByDateDesc()
        } else {
            testResultDao.searchResultsByTestTitle(query)
        }.map { entities ->
            entities.mapNotNull { resultEntity ->
                val testEntity = dao.getTestById(resultEntity.testId)
                if (testEntity != null) mapper.toDomainResult(testEntity, resultEntity) else null
            }
        }
    }
}