package ru.itis.neuroteacher.testcreation.data.datasource

import kotlinx.serialization.json.Json
import ru.itis.neuroteacher.network.api.OpenRouterApi
import ru.itis.neuroteacher.network.model.request.ChatMessage
import ru.itis.neuroteacher.network.model.request.OpenRouterRequest
import ru.itis.neuroteacher.network.model.request.ResponseFormat
import ru.itis.neuroteacher.network.model.response.GeneratedTestDto
import ru.itis.neuroteacher.testcreation.utils.constants.TestGenerationConstants
import ru.itis.neuroteacher.testcreation.utils.prompt.TestPromptBuilder
import javax.inject.Inject

internal class TestRemoteDataSourceImpl @Inject constructor(
    private val api: OpenRouterApi,
    private val json: Json,
    private val promptBuilder: TestPromptBuilder
) : TestRemoteDataSource {

    override suspend fun generateTest(text: String, questionCount: Int): Result<GeneratedTestDto> {
        return runCatching {
            val request = OpenRouterRequest(
                model = TestGenerationConstants.DEFAULT_MODEL,
                messages = listOf(
                    ChatMessage(
                        role = TestGenerationConstants.ROLE_SYSTEM,
                        content = promptBuilder.buildSystemPrompt()
                    ),
                    ChatMessage(
                        role = TestGenerationConstants.ROLE_USER,
                        content = promptBuilder.buildGenerationPrompt(text, questionCount)
                    )
                ),
                temperature = TestGenerationConstants.DEFAULT_TEMPERATURE,
                maxTokens = TestGenerationConstants.DEFAULT_MAX_TOKENS,
                responseFormat = ResponseFormat(type = TestGenerationConstants.RESPONSE_FORMAT_TYPE)
            )

            val response = api.generateTest(request)

            if (!response.isSuccessful) {
                throw Exception("API Error: ${response.code()} - ${response.message()}")
            }

            val body = response.body()
                ?: throw Exception("Empty response body")

            val content = body.choices.firstOrNull()?.message?.content
                ?: throw Exception("No content in response")

            json.decodeFromString<GeneratedTestDto>(content)
        }.fold(
            onSuccess = { Result.success(it) },
            onFailure = { e ->
                Result.failure(Exception("Failed to generate test: ${e.message}"))
            }
        )
    }
}