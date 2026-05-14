package ru.itis.neuroteacher.testcreation.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.itis.neuroteacher.db.converter.DateConverters
import ru.itis.neuroteacher.testcreation.data.db.converter.TestConverters
import ru.itis.neuroteacher.testcreation.data.db.dao.TestDao
import ru.itis.neuroteacher.testcreation.data.db.dao.TestResultDao
import ru.itis.neuroteacher.testcreation.data.db.model.TestEntity
import ru.itis.neuroteacher.testcreation.data.db.model.TestResultEntity


@Database(
    entities = [
        TestEntity::class,
        TestResultEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(DateConverters::class, TestConverters::class)
abstract class TestTakingDatabase : RoomDatabase() {

    abstract fun testDao(): TestDao
    abstract fun testResultDao(): TestResultDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE tests ADD COLUMN isSynced INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE tests ADD COLUMN firestoreId TEXT")
            }
        }
    }
}