package ru.itis.neuroteacher.network.model.response

import com.google.gson.annotations.SerializedName

data class QuestionDto(
    @SerializedName("question")
    val question: String,

    @SerializedName("options")
    val options: List<String>,

    @SerializedName("correct")
    val correct: Int,

    @SerializedName("explanation")
    val explanation: String?
)