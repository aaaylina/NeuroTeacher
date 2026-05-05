package ru.itis.neuroteacher.testcreation.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.itis.neuroteacher.testcreation.data.db.model.TestEntity

@Dao
interface TestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTest(test: TestEntity): Long

    @Query("select * from tests where id = :testId")
    suspend fun getTestById(testId: Long): TestEntity?

    @Query("SELECT * FROM tests ORDER BY dateCreated DESC")
    suspend fun getAllTestsSortedByDateDesc(): List<TestEntity>

    @Query("select * from tests order by dateCreated desc")
    fun getAllTestsFlowSortedByDateDesc(): Flow<List<TestEntity>>

    @Query("select * from tests where title like '%' || :query || '%'  order by dateCreated desc")
    fun searchTestsFlowSortedByDateDesc(query: String): Flow<List<TestEntity>>

    @Query("delete from tests where id = :testId")
    suspend fun deleteTest(testId: Long)

    @Query("delete from tests")
    suspend fun deleteAllTests()

    @Query("SELECT COUNT(*) FROM tests")
    suspend fun getTotalTestsCount(): Int

    @Update
    suspend fun updateTest(test: TestEntity)
}