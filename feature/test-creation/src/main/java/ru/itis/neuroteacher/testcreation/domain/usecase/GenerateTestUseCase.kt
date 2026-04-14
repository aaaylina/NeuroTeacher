package ru.itis.neuroteacher.testcreation.domain.usecase

import ru.itis.neuroteacher.testcreation.domain.model.Test

interface GenerateTestUseCase {
    suspend operator fun invoke(
        text: String,
        questionCount: Int
    ): Result<Test>
}