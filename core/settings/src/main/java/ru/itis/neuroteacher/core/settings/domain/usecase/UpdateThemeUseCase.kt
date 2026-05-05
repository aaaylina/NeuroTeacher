package ru.itis.neuroteacher.core.settings.domain.usecase

import ru.itis.neuroteacher.common.model.ThemeOption
import ru.itis.neuroteacher.core.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateThemeUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(theme: ThemeOption) {
        repository.updateTheme(theme)
    }
}