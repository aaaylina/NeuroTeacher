package ru.itis.neuroteacher.network.model.request
import com.google.gson.annotations.SerializedName


data class OpenRouterRequest(
    @SerializedName("model")
    val model: String = "openrouter/free",

    @SerializedName("messages")
    val messages: List<ChatMessage>,

    @SerializedName("temperature")
    val temperature: Double = 0.7,

    @SerializedName("max_tokens")
    val maxTokens: Int = 8000,

    @SerializedName("response_format")
    val responseFormat: ResponseFormat? = null
)

data class ChatMessage(
    @SerializedName("role")
    val role: String,

    @SerializedName("content")
    val content: String
)

data class ResponseFormat(
    @SerializedName("type")
    val type: String = "json_object"
)
