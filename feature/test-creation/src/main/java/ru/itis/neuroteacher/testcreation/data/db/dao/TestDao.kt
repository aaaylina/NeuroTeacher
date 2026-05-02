package ru.itis.neuroteacher.testcreation.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.itis.neuroteacher.testcreation.data.db.model.TestEntity
import ru.itis.neuroteacher.testcreation.data.db.model.TestResultEntity

@Dao
interface TestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTest(test: TestEntity): Long

    @Query("select * from tests where id = :testId")
    suspend fun getTestById(testId: Long): TestEntity?

    @Query("SELECT * FROM tests ORDER BY dateCreated DESC")
    suspend fun getAllTests(): List<TestEntity>

    @Query("select * from tests order by dateCreated desc")
    fun getAllTestsFlow(): Flow<List<TestEntity>>

    @Query("select * from tests where title like '%' || :query || '%'  order by dateCreated desc")
    fun searchTestsFlow(query: String): Flow<List<TestEntity>>

    @Query("delete from tests where id = :testId")
    suspend fun deleteTest(testId: Long)

    @Query("delete from tests")
    suspend fun deleteAllTests()

    @Update
    suspend fun updateTest(test: TestEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: TestResultEntity): Long

    @Query("SELECT * FROM test_results WHERE testId = :testId ORDER BY dateCompleted DESC LIMIT 1")
    suspend fun getLatestResultForTest(testId: Long): TestResultEntity?

    @Query("SELECT * FROM test_results WHERE testId = :testId")
    fun getResultsForTestFlow(testId: Long): Flow<List<TestResultEntity>>

    @Query("SELECT * FROM test_results ORDER BY dateCompleted DESC")
    fun getAllResultsFlow(): Flow<List<TestResultEntity>>

    @Query("SELECT COUNT(*) FROM tests")
    suspend fun getTotalTestsCount(): Int

    @Query("SELECT COUNT(*) FROM test_results")
    suspend fun getTotalCompletedTestsCount(): Int

    @Query("SELECT AVG(scorePercentage) FROM test_results")
    suspend fun getAverageScore(): Float?

    @Query("SELECT MAX(scorePercentage) FROM test_results")
    suspend fun getBestScore(): Float?

    @Query("SELECT * FROM test_results WHERE id = :resultId")
    suspend fun getResultById(resultId: Long): TestResultEntity?

    @Query("SELECT * FROM test_results WHERE testId = :testId ORDER BY dateCompleted DESC")
    suspend fun getResultsByTestId(testId: Long): List<TestResultEntity>

    @Query("SELECT * FROM test_results WHERE testId = :testId ORDER BY dateCompleted DESC")
    fun getResultsByTestIdFlow(testId: Long): Flow<List<TestResultEntity>>

}