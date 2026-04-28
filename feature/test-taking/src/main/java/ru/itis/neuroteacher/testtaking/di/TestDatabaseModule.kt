package ru.itis.neuroteacher.testtaking.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.testtaking.data.db.TestTakingDatabase
import ru.itis.neuroteacher.testtaking.data.db.dao.TestDao
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
}