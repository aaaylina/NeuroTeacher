package ru.itis.neuroteacher.core.settings.domain.usecase

import ru.itis.neuroteacher.common.model.ThemeOption

interface UpdateThemeUseCase {
    suspend operator fun invoke(theme: ThemeOption)
}