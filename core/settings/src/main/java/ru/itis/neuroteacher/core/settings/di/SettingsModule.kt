package ru.itis.neuroteacher.core.settings.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.core.settings.data.repository.SettingsRepositoryImpl
import ru.itis.neuroteacher.core.settings.domain.repository.SettingsRepository
import ru.itis.neuroteacher.core.settings.domain.usecase.UpdateLanguageUseCase
import ru.itis.neuroteacher.core.settings.domain.usecase.UpdateLanguageUseCaseImpl
import ru.itis.neuroteacher.core.settings.domain.usecase.UpdateThemeUseCase
import ru.itis.neuroteacher.core.settings.domain.usecase.UpdateThemeUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SettingsModule {

    @Binds
    abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository

    @Binds
    abstract fun bindUpdateThemeUseCase(impl: UpdateThemeUseCaseImpl): UpdateThemeUseCase

    @Binds
    abstract fun bindUpdateLanguageUseCase(impl: UpdateLanguageUseCaseImpl): UpdateLanguageUseCase
}