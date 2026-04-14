package ru.itis.neuroteacher.testcreation.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionDataModel(
    @SerialName("text")
    val text: String,
    @SerialName("options")
    val options: List<String>,
    @SerialName("correctIndex")
    val correctIndex: Int,
    @SerialName("explanation")
    val explanation: String?
)