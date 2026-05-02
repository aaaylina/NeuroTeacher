package ru.itis.neuroteacher.testcreation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import ru.itis.neuroteacher.testcreation.data.db.dao.TestDao
import ru.itis.neuroteacher.testcreation.data.mapper.TestMapper
import ru.itis.neuroteacher.testcreation.data.repository.TestRepositoryImpl
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideTestMapper(json: Json): TestMapper {
        return TestMapper(json)
    }

    @Provides
    fun provideTestRepository(
        dao: TestDao,
        mapper: TestMapper
    ): TestRepository {
        return TestRepositoryImpl(dao, mapper)
    }
}