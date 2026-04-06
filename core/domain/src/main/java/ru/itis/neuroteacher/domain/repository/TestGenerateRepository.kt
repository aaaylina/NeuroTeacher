package ru.itis.neuroteacher.domain.repository

import ru.itis.neuroteacher.domain.model.Test

interface TestGenerationRepository {
    suspend fun generateTest(text: String,
                             questionCount: Int = 5
    ): Result<Test>
}