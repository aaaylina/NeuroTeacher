package ru.itis.neuroteacher.auth.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.auth.data.mapper.UserMapper
import ru.itis.neuroteacher.auth.data.repository.AuthRepositoryImpl
import ru.itis.neuroteacher.auth.data.repository.FireBaseErrorHandler
import ru.itis.neuroteacher.auth.domain.repository.AuthErrorHandler
import ru.itis.neuroteacher.auth.domain.repository.AuthRepository
import ru.itis.neuroteacher.auth.domain.usecase.SignInUseCase
import ru.itis.neuroteacher.auth.domain.usecase.SignInUseCaseImpl
import ru.itis.neuroteacher.auth.domain.usecase.SignUpUseCase
import ru.itis.neuroteacher.auth.domain.usecase.SignUpUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AuthModule {

    @Binds
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindAuthErrorHandler(
        impl: FireBaseErrorHandler
    ): AuthErrorHandler

    @Binds
    abstract fun bindSignInUseCase(
        impl: SignInUseCaseImpl
    ): SignInUseCase

    @Binds
    abstract fun bindSignUpUseCase(
        impl: SignUpUseCaseImpl
    ): SignUpUseCase
}

@Module
@InstallIn(SingletonComponent::class)
internal object MapperModule {

    @Provides
    fun provideUserMapper(): UserMapper = UserMapper()

}