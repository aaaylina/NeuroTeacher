package ru.itis.neuroteacher.domain.model

data class Test(
    val title: String,
    val questions: List<Question>
)