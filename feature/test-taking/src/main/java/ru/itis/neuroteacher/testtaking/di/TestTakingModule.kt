package ru.itis.neuroteacher.testtaking.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.testtaking.data.repository.TestLocalRepository
import ru.itis.neuroteacher.testtaking.data.repository.TestLocalRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class TestTakingModule {

    @Binds
    abstract fun bindTestLocalRepository(
        impl: TestLocalRepositoryImpl
    ): TestLocalRepository
}
