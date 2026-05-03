package ru.itis.neuroteacher.testcreation.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.testcreation.data.db.TestTakingDatabase
import ru.itis.neuroteacher.testcreation.data.db.dao.TestDao
import ru.itis.neuroteacher.testcreation.data.db.dao.TestResultDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestDatabaseModule {

    @Provides
    @Singleton
    fun provideTestTakingDatabase(@ApplicationContext context: Context): TestTakingDatabase {
        return Room.databaseBuilder(
            context,
            TestTakingDatabase::class.java,
            "neuro_teacher_db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideTestDao(database: TestTakingDatabase): TestDao {
        return database.testDao()
    }

    @Provides
    fun provideTestResultDao(database: TestTakingDatabase): TestResultDao {
        return database.testResultDao()
    }
}