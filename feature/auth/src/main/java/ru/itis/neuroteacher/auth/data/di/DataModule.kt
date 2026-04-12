package ru.itis.neuroteacher.auth.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.auth.data.repository.AuthRepositoryImpl
import ru.itis.neuroteacher.auth.data.repository.FireBaseErrorHandler
import ru.itis.neuroteacher.auth.domain.repository.AuthErrorHandler
import ru.itis.neuroteacher.auth.domain.repository.AuthRepository

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    fun provideAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository = impl

    @Provides
    fun provideAuthErrorHandler(
        impl: FireBaseErrorHandler
    ): AuthErrorHandler = impl
}