package ru.itis.neuroteacher.testcreation.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.itis.neuroteacher.testcreation.data.db.model.TestResultEntity

@Dao
interface TestResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: TestResultEntity): Long

    @Query("SELECT * FROM test_results WHERE testId = :testId ORDER BY dateCompleted DESC LIMIT 1")
    suspend fun getLatestResultForTest(testId: Long): TestResultEntity?

    @Query("SELECT * FROM test_results WHERE testId = :testId")
    fun getResultsForTestFlow(testId: Long): Flow<List<TestResultEntity>>

    @Query("SELECT * FROM test_results ORDER BY dateCompleted DESC")
    fun getAllResultsFlowSortedByDateDesc(): Flow<List<TestResultEntity>>

    @Query("SELECT COUNT(*) FROM test_results")
    suspend fun getTotalCompletedTestsCount(): Int

    @Query("SELECT AVG(scorePercentage) FROM test_results")
    suspend fun getAverageScore(): Float?

    @Query("SELECT MAX(scorePercentage) FROM test_results")
    suspend fun getBestScore(): Float?

    @Query("SELECT * FROM test_results WHERE id = :resultId")
    suspend fun getResultById(resultId: Long): TestResultEntity?

    @Query("SELECT * FROM test_results WHERE testId = :testId ORDER BY dateCompleted DESC")
    suspend fun getResultsByTestIdSortedByDateDesc(testId: Long): List<TestResultEntity>

    @Query("SELECT * FROM test_results WHERE testId = :testId ORDER BY dateCompleted DESC")
    fun getResultsByTestIdFlowSortedByDateDesc(testId: Long): Flow<List<TestResultEntity>>
}