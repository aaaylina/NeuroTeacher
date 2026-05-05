package ru.itis.neuroteacher.testcreation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.testcreation.data.mapper.TestMapper
import ru.itis.neuroteacher.testcreation.data.mapper.TestMapperImpl
import ru.itis.neuroteacher.testcreation.data.repository.TestRepositoryImpl
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import ru.itis.neuroteacher.testcreation.domain.usecase.GetTestStatisticsUseCase
import ru.itis.neuroteacher.testcreation.domain.usecase.GetTestStatisticsUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindTestMapper(impl: TestMapperImpl): TestMapper

    @Binds
    abstract fun bindTestRepository(impl: TestRepositoryImpl): TestRepository

    @Binds
    abstract fun bindGetTestStatisticsUseCase(impl: GetTestStatisticsUseCaseImpl): GetTestStatisticsUseCase
}