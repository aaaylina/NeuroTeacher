package ru.itis.neuroteacher.domain.model

data class Question(
    val text: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String?
)