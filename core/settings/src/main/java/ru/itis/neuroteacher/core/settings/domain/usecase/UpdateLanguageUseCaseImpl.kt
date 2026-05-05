package ru.itis.neuroteacher.core.settings.domain.usecase

import ru.itis.neuroteacher.common.model.LanguageOption
import ru.itis.neuroteacher.core.settings.domain.repository.SettingsRepository
import javax.inject.Inject

internal class UpdateLanguageUseCaseImpl @Inject constructor(
    private val repository: SettingsRepository
) : UpdateLanguageUseCase {

    override suspend fun invoke(language: LanguageOption) {
        repository.updateLanguage(language)
    }
}