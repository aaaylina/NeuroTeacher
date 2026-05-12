package ru.itis.neuroteacher.testcreation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.domain.usecase.GetAllRecentTestsUseCase
import ru.itis.neuroteacher.testcreation.domain.usecase.GetAllRecentTestsUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RecentTestsModule {
    @Binds
    abstract fun bindGetAllRecentTestsUseCase(
        impl: GetAllRecentTestsUseCaseImpl
    ): GetAllRecentTestsUseCase
}