package ru.itis.neuroteacher.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.data.error.FireBaseErrorHandler
import ru.itis.neuroteacher.data.repository.AuthRepositoryImpl
import ru.itis.neuroteacher.domain.repository.AuthErrorHandler
import ru.itis.neuroteacher.domain.repository.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindAuthErrorHandler(
        impl: FireBaseErrorHandler
    ): AuthErrorHandler
}