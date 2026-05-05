package ru.itis.neuroteacher.core.settings.data.repository

import ru.itis.neuroteacher.common.model.AppSettings
import ru.itis.neuroteacher.common.model.LanguageOption
import ru.itis.neuroteacher.common.model.ThemeOption
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.itis.neuroteacher.core.settings.domain.repository.SettingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context) : SettingsRepository {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME, Context.MODE_PRIVATE
    )

    private val _settingsFlow = MutableStateFlow(
        AppSettings(
            theme = getSavedTheme(),
            language = getSavedLanguage()
        )
    )

    override fun getSettingsFlow(): Flow<AppSettings> = _settingsFlow.asStateFlow()

    override suspend fun updateTheme(theme: ThemeOption) {
        prefs.edit { putString(KEY_THEME, theme.name) }
        _settingsFlow.value = _settingsFlow.value.copy(theme = theme)
    }

    override suspend fun updateLanguage(language: LanguageOption) {
        prefs.edit { putString(KEY_LANGUAGE, language.name) }
        _settingsFlow.value = _settingsFlow.value.copy(language = language)
        android.util.Log.d("SettingsDebug", "Language updated to: ${language.name}")

    }

    override suspend fun clearAllData() {
        prefs.edit {
            clear()
        }
        _settingsFlow.value = AppSettings()
    }

    override fun getSavedTheme(): ThemeOption = runCatching {
        prefs.getString(KEY_THEME, null)?.let {
            ThemeOption.valueOf(it)
        } ?: ThemeOption.SYSTEM
    }.getOrElse {
        ThemeOption.SYSTEM
    }


    override fun getSavedLanguage(): LanguageOption = runCatching {
        prefs.getString(KEY_LANGUAGE, null)?.let {
            LanguageOption.valueOf(it)
        } ?: LanguageOption.RUSSIAN
    }.getOrElse {
        LanguageOption.RUSSIAN
    }

    companion object {
        private const val PREFS_NAME = "app_settings"
        private const val KEY_THEME = "theme"
        private const val KEY_LANGUAGE = "language"
    }
}