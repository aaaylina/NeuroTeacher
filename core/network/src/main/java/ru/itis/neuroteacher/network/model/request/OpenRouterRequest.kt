package ru.itis.neuroteacher.network.model.request
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenRouterRequest(
    @SerialName("model")
    val model: String,

    @SerialName("messages")
    val messages: List<ChatMessage>,

    @SerialName("temperature")
    val temperature: Double,

    @SerialName("max_tokens")
    val maxTokens: Int,

    @SerialName("response_format")
    val responseFormat: ResponseFormat?
)

@Serializable
data class ChatMessage(
    @SerialName("role")
    val role: String,

    @SerialName("content")
    val content: String
)

@Serializable
data class ResponseFormat(
    @SerialName("type")
    val type: String
)