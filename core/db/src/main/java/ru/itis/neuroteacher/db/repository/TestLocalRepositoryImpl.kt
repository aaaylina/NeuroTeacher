package ru.itis.neuroteacher.db.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.neuroteacher.db.dao.TestDao
import ru.itis.neuroteacher.db.model.TestEntity
import ru.itis.neuroteacher.db.model.TestResultEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestLocalRepositoryImpl @Inject constructor(
    private val testDao: TestDao
) : TestLocalRepository {

    override suspend fun saveTest(test: TestEntity): Long {
        return testDao.insertTest(test)
    }

    override suspend fun getTestById(testId: String): TestEntity? {
        return testDao.getTestById(testId)
    }

    override fun getAllTests(): Flow<List<TestEntity>> {
        return testDao.getAllTestsFlow()
    }

    override fun searchTests(query: String): Flow<List<TestEntity>> {
        return testDao.searchTestsFlow(query)
    }

    override suspend fun deleteTest(testId: String) {
        testDao.deleteTest(testId)
    }

    override suspend fun deleteAllTests() {
        testDao.deleteAllTests()
    }

    override suspend fun saveResult(result: TestResultEntity): Long {
        return testDao.insertResult(result)
    }

    override suspend fun getLatestResult(testId: String): TestResultEntity? {
        return testDao.getLatestResultForTest(testId)
    }

    override fun getResultsForTest(testId: String): Flow<List<TestResultEntity>> {
        return testDao.getResultsForTestFlow(testId)
    }

    override suspend fun saveTestWithResult(test: TestEntity, result: TestResultEntity) {
        testDao.saveTestWithResult(test, result)
    }

    override suspend fun getStatistics(): UserStatistics {
        return UserStatistics(
            totalTests = testDao.getTotalTestsCount(),
            completedTests = testDao.getTotalCompletedTestsCount(),
            averageScore = testDao.getAverageScore() ?: 0f,
            bestScore = testDao.getBestScore() ?: 0f
        )
    }
}