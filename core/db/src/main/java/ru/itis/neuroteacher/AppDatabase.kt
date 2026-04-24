package ru.itis.neuroteacher

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.itis.neuroteacher.db.dao.TestDao
import ru.itis.neuroteacher.db.model.TestEntity
import ru.itis.neuroteacher.db.model.TestResultEntity

@Database(
    entities = [
        TestEntity::class,
        TestResultEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun testDao(): TestDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "neuro_teacher_db"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                INSTANCE = instance
                instance
            }

        }
    }
}