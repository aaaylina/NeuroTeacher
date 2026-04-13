package ru.itis.neuroteacher.buildconfig.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.buildconfig.api.BuildConfigProvider
import ru.itis.neuroteacher.buildconfig.impl.BuildConfigProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BuildConfigModule {

    @Binds
    @Singleton
    abstract fun bindBuildConfigProvider(
        impl: BuildConfigProviderImpl
    ): BuildConfigProvider
}