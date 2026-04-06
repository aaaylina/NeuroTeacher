package ru.itis.neuroteacher.domain.usecase

import ru.itis.neuroteacher.domain.repository.TestGenerationRepository
import javax.inject.Inject

class GenerateTestUseCase @Inject constructor(
    private val repository: TestGenerationRepository
) {
    suspend operator fun invoke(
    ){
        TODO("0.0")
    }
}