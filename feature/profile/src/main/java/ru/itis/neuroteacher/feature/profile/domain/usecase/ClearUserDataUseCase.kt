package ru.itis.neuroteacher.feature.profile.domain.usecase

import ru.itis.neuroteacher.core.settings.domain.repository.SettingsRepository
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import javax.inject.Inject

class ClearUserDataUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val testRepository: TestRepository
) {
    suspend operator fun invoke() {
        testRepository.clearAllData()
        settingsRepository.clearAllData()
    }
}