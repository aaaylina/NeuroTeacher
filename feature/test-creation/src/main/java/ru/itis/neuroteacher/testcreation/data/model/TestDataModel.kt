package ru.itis.neuroteacher.testcreation.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TestDataModel(
    @SerialName("title")
    val title: String,
    @SerialName("questions")
    val questions: List<QuestionDataModel>
)