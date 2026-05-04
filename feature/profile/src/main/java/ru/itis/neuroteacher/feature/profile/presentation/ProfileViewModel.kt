package ru.itis.neuroteacher.feature.profile.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onThemeSelected(theme: ThemeOption) {
        _uiState.update { it.copy(selectedTheme = theme) }
    }

    fun onLanguageSelected(language: LanguageOption) {
        _uiState.update { it.copy(selectedLanguage = language) }
    }

    fun onClearData() {
        // TODO: UseCase для очистки данных
    }

    fun onLogout() {
        // TODO: UseCase для выхода
    }
}

data class ProfileUiState(
    val selectedTheme: ThemeOption = ThemeOption.LIGHT,
    val selectedLanguage: LanguageOption = LanguageOption.RUSSIAN,
    val totalTests: Int = 0,
    val avgScore: String = "0%",
    val bestScore: String = "0%"
)

enum class ThemeOption {
    LIGHT, DARK, SYSTEM
}
enum class LanguageOption {
    RUSSIAN, ENGLISH
}