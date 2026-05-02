package ru.itis.neuroteacher.testcreation.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.itis.neuroteacher.db.converter.DateConverters
import ru.itis.neuroteacher.testcreation.data.db.converter.TestConverters
import ru.itis.neuroteacher.testcreation.data.db.dao.TestDao
import ru.itis.neuroteacher.testcreation.data.db.model.TestEntity
import ru.itis.neuroteacher.testcreation.data.db.model.TestResultEntity


@Database(
    entities = [
        TestEntity::class,
        TestResultEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverters::class, TestConverters::class)
abstract class TestTakingDatabase : RoomDatabase() {

    abstract fun testDao(): TestDao
}