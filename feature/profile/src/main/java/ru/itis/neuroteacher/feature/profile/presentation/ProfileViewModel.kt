package ru.itis.neuroteacher.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.itis.neuroteacher.auth.domain.usecase.LogoutUseCase
import ru.itis.neuroteacher.common.model.LanguageOption
import ru.itis.neuroteacher.common.model.ThemeOption
import ru.itis.neuroteacher.core.settings.domain.repository.SettingsRepository
import ru.itis.neuroteacher.feature.profile.domain.usecase.ClearUserDataUseCase
import ru.itis.neuroteacher.core.settings.domain.usecase.UpdateLanguageUseCase
import ru.itis.neuroteacher.core.settings.domain.usecase.UpdateThemeUseCase
import ru.itis.neuroteacher.testcreation.domain.usecase.GetTestStatisticsUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getTestStatisticsUseCase: GetTestStatisticsUseCase,
    private val updateThemeUseCase: UpdateThemeUseCase,
    private val updateLanguageUseCase: UpdateLanguageUseCase,
    private val clearUserDataUseCase: ClearUserDataUseCase,
    private val settingsRepository: SettingsRepository,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadInitialSettings()
        loadStatistics()
    }

    private fun loadInitialSettings() {
        viewModelScope.launch {
            val savedTheme = settingsRepository.getSavedTheme()
            val savedLanguage = settingsRepository.getSavedLanguage()
            _uiState.update {
                it.copy(
                    selectedTheme = savedTheme,
                    selectedLanguage = savedLanguage
                )
            }
        }
    }

    private fun loadStatistics() {
        viewModelScope.launch {
            val statistics = getTestStatisticsUseCase()
            _uiState.update {
                it.copy(
                    totalTests = statistics.totalTests,
                    completedTests = statistics.completedTests,
                    avgScore = formatPercentage(statistics.averageScore),
                    bestScore = formatPercentage(statistics.bestScore)
                )
            }
        }
    }

    private fun formatPercentage(value: Float): String {
        return "${value.toInt()}%"
    }

    fun onThemeSelected(theme: ThemeOption) {
        viewModelScope.launch {
            updateThemeUseCase(theme)
            _uiState.update { it.copy(selectedTheme = theme) }
        }
    }

    fun onLanguageSelected(language: LanguageOption) {
        viewModelScope.launch {
            updateLanguageUseCase(language)
            _uiState.update { it.copy(selectedLanguage = language) }
        }
    }

    fun onClearData(onSuccess: () -> Unit) {
        viewModelScope.launch {
            clearUserDataUseCase()
            _uiState.update {
                it.copy(
                    totalTests = 0,
                    completedTests = 0,
                    avgScore = "0%",
                    bestScore = "0%"
                )
            }
            onSuccess()
        }
    }

    fun onLogout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            logoutUseCase().fold(
                onSuccess = {
                    _uiState.update { it.copy(errorMessage = null) }
                    onSuccess()
                },
                onFailure = { exception ->
                    _uiState.update {
                        it.copy(errorMessage = exception.message ?: "Ошибка выхода из аккаунта")
                    }
                }
            )
        }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun refreshStatistics() {
        loadStatistics()
    }
}

data class ProfileUiState(
    val selectedTheme: ThemeOption = ThemeOption.LIGHT,
    val selectedLanguage: LanguageOption = LanguageOption.RUSSIAN,
    val totalTests: Int = 0,
    val completedTests: Int = 0,
    val avgScore: String = "0%",
    val bestScore: String = "0%",
    val errorMessage: String? = null
)
