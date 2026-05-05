package ru.itis.neuroteacher.core.settings.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.neuroteacher.common.model.AppSettings
import ru.itis.neuroteacher.common.model.LanguageOption
import ru.itis.neuroteacher.common.model.ThemeOption

interface SettingsRepository {
    fun getSettingsFlow(): Flow<AppSettings>

    suspend fun updateTheme(theme: ThemeOption)

    suspend fun updateLanguage(language: LanguageOption)

    suspend fun clearAllData()

    fun getSavedTheme(): ThemeOption

    fun getSavedLanguage(): LanguageOption
}