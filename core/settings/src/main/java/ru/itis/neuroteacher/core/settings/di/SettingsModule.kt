package ru.itis.neuroteacher.core.settings.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.neuroteacher.core.settings.data.repository.SettingsRepositoryImpl
import ru.itis.neuroteacher.core.settings.domain.repository.SettingsRepository
import ru.itis.neuroteacher.core.settings.domain.usecase.ChangeLanguageUseCase
import ru.itis.neuroteacher.core.settings.domain.usecase.UpdateThemeUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Binds
    abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository

    @Binds
    abstract fun bindUpdateThemeUseCase(impl: UpdateThemeUseCase): UpdateThemeUseCase

    @Binds
    abstract fun bindChangeLanguageUseCase(impl: ChangeLanguageUseCase): ChangeLanguageUseCase
}