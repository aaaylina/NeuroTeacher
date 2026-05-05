package ru.itis.neuroteacher.core.settings.domain.usecase

import ru.itis.neuroteacher.common.model.LanguageOption
import ru.itis.neuroteacher.core.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateLanguageUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(language: LanguageOption) {
        repository.updateLanguage(language)
    }
}