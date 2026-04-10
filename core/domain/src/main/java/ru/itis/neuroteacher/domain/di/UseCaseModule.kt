package ru.itis.neuroteacher.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.domain.repository.AuthRepository
import ru.itis.neuroteacher.domain.usecase.auth.SignInUseCase
import ru.itis.neuroteacher.domain.usecase.auth.SignUpUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSignInUseCase(
        authRepository: AuthRepository
    ): SignInUseCase = SignInUseCase(authRepository)

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        authRepository: AuthRepository
    ): SignUpUseCase = SignUpUseCase(authRepository)
}