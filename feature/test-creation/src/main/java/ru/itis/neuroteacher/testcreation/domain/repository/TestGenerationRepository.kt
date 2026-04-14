package ru.itis.neuroteacher.testcreation.domain.repository

import ru.itis.neuroteacher.testcreation.domain.model.Test

interface TestGenerationRepository {
    suspend fun generateTest(
        text: String,
        questionCount: Int
    ): Result<Test>
}