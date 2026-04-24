package ru.itis.neuroteacher.db.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.neuroteacher.db.model.TestEntity
import ru.itis.neuroteacher.db.model.TestResultEntity

interface TestLocalRepository {
    suspend fun saveTest(test: TestEntity): Long
    suspend fun getTestById(testId: String): TestEntity?
    fun getAllTests(): Flow<List<TestEntity>>
    fun searchTests(query: String): Flow<List<TestEntity>>
    suspend fun deleteTest(testId: String)
    suspend fun deleteAllTests()

    suspend fun saveResult(result: TestResultEntity): Long
    suspend fun getLatestResult(testId: String): TestResultEntity?
    fun getResultsForTest(testId: String): Flow<List<TestResultEntity>>

    suspend fun saveTestWithResult(test: TestEntity, result: TestResultEntity)

    suspend fun getStatistics(): UserStatistics
}

data class UserStatistics(
    val totalTests: Int = 0,
    val completedTests: Int = 0,
    val averageScore: Float = 0f,
    val bestScore: Float = 0f
)