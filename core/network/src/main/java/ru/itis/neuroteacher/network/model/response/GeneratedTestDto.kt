package ru.itis.neuroteacher.network.model.response

import com.google.gson.annotations.SerializedName

data class GeneratedTestDto(
    @SerializedName("title")
    val title: String,

    @SerializedName("questions")
    val questions: List<QuestionDto>
)