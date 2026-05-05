package ru.itis.neuroteacher.core.settings.domain.usecase
import ru.itis.neuroteacher.common.model.LanguageOption

interface UpdateLanguageUseCase {
    suspend operator fun invoke(language: LanguageOption)
}