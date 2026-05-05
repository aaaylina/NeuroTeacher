package ru.itis.neuroteacher.core.settings.domain.usecase

import ru.itis.neuroteacher.common.model.ThemeOption
import ru.itis.neuroteacher.core.settings.domain.repository.SettingsRepository
import javax.inject.Inject

internal class UpdateThemeUseCaseImpl @Inject constructor(
    private val repository: SettingsRepository
) : UpdateThemeUseCase {

    override suspend fun invoke(theme: ThemeOption) {
        repository.updateTheme(theme)
    }
}