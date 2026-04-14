package ru.itis.neuroteacher.testcreation.data.datasource

import ru.itis.neuroteacher.network.model.response.GeneratedTestDto

interface TestRemoteDataSource {
    suspend fun generateTest(text: String, questionCount: Int): Result<GeneratedTestDto>
}