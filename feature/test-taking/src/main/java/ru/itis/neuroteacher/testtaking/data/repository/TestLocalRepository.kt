package ru.itis.neuroteacher.testtaking.data.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.neuroteacher.testtaking.data.db.model.TestEntity
import ru.itis.neuroteacher.testtaking.data.db.model.TestResultEntity
import ru.itis.neuroteacher.testtaking.domain.model.TestStatistics

interface TestLocalRepository {
    suspend fun saveTest(test: TestEntity): Long
    suspend fun getTestById(testId: Long): TestEntity?
    fun getAllTests(): Flow<List<TestEntity>>
    fun searchTests(query: String): Flow<List<TestEntity>>
    suspend fun deleteTest(testId: Long)
    suspend fun deleteAllTests()

    suspend fun saveResult(result: TestResultEntity): Long
    suspend fun getLatestResult(testId: Long): TestResultEntity?
    fun getResultsForTest(testId: Long): Flow<List<TestResultEntity>>

    suspend fun saveTestWithResult(test: TestEntity, result: TestResultEntity)

    suspend fun getStatistics(): TestStatistics
}