package ru.itis.neuroteacher.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenRouterResponse(
    @SerialName("id")
    val id: String,

    @SerialName("choices")
    val choices: List<Choice>,

    @SerialName("usage")
    val usage: Usage?,

    @SerialName("model")
    val model: String
){
    @Serializable
    data class Choice(
        @SerialName("message")
        val message: ResponseMessage,

        @SerialName("finish_reason")
        val finishReason: String?
    )
    @Serializable
    data class ResponseMessage(
        @SerialName("role")
        val role: String,

        @SerialName("content")
        val content: String?
    )

    @Serializable
    data class Usage(
        @SerialName("prompt_tokens")
        val promptTokens: Int,

        @SerialName("completion_tokens")
        val completionTokens: Int,

        @SerialName("total_tokens")
        val totalTokens: Int
    )
}