package ru.itis.neuroteacher.testcreation.domain.usecase

import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.repository.TestGenerationRepository
import javax.inject.Inject

internal class GenerateTestUseCaseImpl @Inject constructor(
    private val repository: TestGenerationRepository
) : GenerateTestUseCase {

    override suspend operator fun invoke(
        text: String,
        questionCount: Int
    ): Result<Test> {

        if (text.isBlank()) {
            return Result.failure(Exception("Текст не может быть пустым"))
        }

        return repository.generateTest(text, questionCount)
    }
}