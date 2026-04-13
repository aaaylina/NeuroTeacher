package ru.itis.neuroteacher.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class QuestionDto(
    @SerialName("question")
    val question: String,

    @SerialName("options")
    val options: List<String>,

    @SerialName("correct")
    val correct: Int,

    @SerialName("explanation")
    val explanation: String?
)