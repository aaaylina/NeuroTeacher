package ru.itis.neuroteacher.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeneratedTestDto(
    @SerialName("title")
    val title: String,

    @SerialName("questions")
    val questions: List<QuestionDto>
)