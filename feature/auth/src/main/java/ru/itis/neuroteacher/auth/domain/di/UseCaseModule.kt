package ru.itis.neuroteacher.auth.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.auth.domain.repository.AuthRepository
import ru.itis.neuroteacher.auth.domain.usecase.SignInUseCase
import ru.itis.neuroteacher.auth.domain.usecase.SignInUseCaseImpl
import ru.itis.neuroteacher.auth.domain.usecase.SignUpUseCase
import ru.itis.neuroteacher.auth.domain.usecase.SignUpUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
internal object UseCaseModule {

    @Provides
    fun provideSignInUseCase(
        authRepository: AuthRepository
    ): SignInUseCase = SignInUseCaseImpl(authRepository)

    @Provides
    fun provideSignUpUseCase(
        authRepository: AuthRepository
    ): SignUpUseCase = SignUpUseCaseImpl(authRepository)
}