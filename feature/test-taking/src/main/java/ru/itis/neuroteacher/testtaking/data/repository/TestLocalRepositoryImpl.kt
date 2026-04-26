package ru.itis.neuroteacher.testtaking.data.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.neuroteacher.testtaking.data.db.dao.TestDao
import ru.itis.neuroteacher.testtaking.data.db.model.TestEntity
import ru.itis.neuroteacher.testtaking.data.db.model.TestResultEntity
import ru.itis.neuroteacher.testtaking.domain.model.TestStatistics
import javax.inject.Inject

class TestLocalRepositoryImpl @Inject constructor(
    private val testDao: TestDao
) : TestLocalRepository {
    override suspend fun saveTest(test: TestEntity): Long {
        return testDao.insertTest(test)
    }

    override suspend fun getTestById(testId: Long): TestEntity? {
        return testDao.getTestById(testId)
    }

    override fun getAllTests(): Flow<List<TestEntity>> {
        return testDao.getAllTestsFlow()
    }

    override fun searchTests(query: String): Flow<List<TestEntity>> {
        return testDao.searchTestsFlow(query)
    }

    override suspend fun deleteTest(testId: Long) {
        testDao.deleteTest(testId)
    }

    override suspend fun deleteAllTests() {
        testDao.deleteAllTests()
    }

    override suspend fun saveResult(result: TestResultEntity): Long {
        return testDao.insertResult(result)
    }

    override suspend fun getLatestResult(testId: Long): TestResultEntity? {
        return testDao.getLatestResultForTest(testId)
    }

    override fun getResultsForTest(testId: Long): Flow<List<TestResultEntity>> {
        return testDao.getResultsForTestFlow(testId)
    }

    override suspend fun saveTestWithResult(test: TestEntity, result: TestResultEntity) {
        testDao.saveTestWithResult(test, result)
    }

    override suspend fun getStatistics(): TestStatistics {
        return TestStatistics(
            totalTests = testDao.getTotalTestsCount(),
            completedTests = testDao.getTotalCompletedTestsCount(),
            averageScore = testDao.getAverageScore(),
            bestScore = testDao.getBestScore()
        )
    }
}