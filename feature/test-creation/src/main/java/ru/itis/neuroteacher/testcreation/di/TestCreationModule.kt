package ru.itis.neuroteacher.testcreation.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.testcreation.data.datasource.TestRemoteDataSource
import ru.itis.neuroteacher.testcreation.data.datasource.TestRemoteDataSourceImpl
import ru.itis.neuroteacher.testcreation.data.mapper.TestMapper
import ru.itis.neuroteacher.testcreation.data.repository.TestGenerationRepositoryImpl
import ru.itis.neuroteacher.testcreation.domain.repository.TestGenerationRepository
import ru.itis.neuroteacher.testcreation.domain.usecase.GenerateTestUseCase
import ru.itis.neuroteacher.testcreation.domain.usecase.GenerateTestUseCaseImpl
import ru.itis.neuroteacher.testcreation.utils.prompt.TestPromptBuilder

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TestCreationModule {

    @Binds
    abstract fun bindTestRemoteDataSource(
        impl: TestRemoteDataSourceImpl
    ): TestRemoteDataSource

    @Binds
    abstract fun bindTestGenerationRepository(
        impl: TestGenerationRepositoryImpl
    ): TestGenerationRepository

    @Binds
    abstract fun bindGenerateTestUseCase(
        impl: GenerateTestUseCaseImpl
    ): GenerateTestUseCase
}

@Module
@InstallIn(SingletonComponent::class)
internal object TestCreationDataModule {

    @Provides
    fun provideTestPromptBuilder(): TestPromptBuilder = TestPromptBuilder()

    @Provides
    fun provideTestMapper(): TestMapper = TestMapper()
}