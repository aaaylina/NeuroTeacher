package ru.itis.neuroteacher.network.model.request
import com.google.gson.annotations.SerializedName


data class OpenRouterRequest(
    @SerializedName("model")
    val model: String,

    @SerializedName("messages")
    val messages: List<ChatMessage>,

    @SerializedName("temperature")
    val temperature: Double,

    @SerializedName("max_tokens")
    val maxTokens: Int,

    @SerializedName("response_format")
    val responseFormat: ResponseFormat?
)

data class ChatMessage(
    @SerializedName("role")
    val role: String,

    @SerializedName("content")
    val content: String
)

data class ResponseFormat(
    @SerializedName("type")
    val type: String
)