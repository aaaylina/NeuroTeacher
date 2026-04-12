package ru.itis.neuroteacher.testcreation.data.model

data class QuestionDataModel(
    val text: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String?
)
