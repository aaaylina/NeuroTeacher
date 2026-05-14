package ru.itis.neuroteacher.testcreation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.testcreation.data.sync.InitialSyncManager
import ru.itis.neuroteacher.testcreation.domain.repository.FirebaseQuizRepository
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SyncModule {

    @Provides
    @Singleton
    fun provideInitialSyncManager(
        firebaseRepo: FirebaseQuizRepository,
        localRepo: TestRepository
    ): InitialSyncManager {
        return InitialSyncManager(firebaseRepo, localRepo)
    }
}