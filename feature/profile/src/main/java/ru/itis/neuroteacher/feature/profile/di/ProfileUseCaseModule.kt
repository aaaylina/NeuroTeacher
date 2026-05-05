package ru.itis.neuroteacher.feature.profile.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.feature.profile.domain.usecase.ClearUserDataUseCase
import ru.itis.neuroteacher.testcreation.domain.usecase.GetTestStatisticsUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileUseCaseModule {

    @Binds
    abstract fun bindClearUserDataUseCase(impl: ClearUserDataUseCase): ClearUserDataUseCase
    @Binds
    abstract fun bindGetTestStatisticsUseCase(impl: GetTestStatisticsUseCase): GetTestStatisticsUseCase
}