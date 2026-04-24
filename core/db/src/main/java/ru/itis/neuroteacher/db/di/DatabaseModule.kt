package ru.itis.neuroteacher.db.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.AppDatabase
import ru.itis.neuroteacher.db.dao.TestDao
import ru.itis.neuroteacher.db.repository.TestLocalRepository
import ru.itis.neuroteacher.db.repository.TestLocalRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideTestDao(database: AppDatabase): TestDao {
        return database.testDao()
    }

    @Provides
    fun provideTestLocalRepository(impl: TestLocalRepositoryImpl): TestLocalRepository {
        return impl
    }
}