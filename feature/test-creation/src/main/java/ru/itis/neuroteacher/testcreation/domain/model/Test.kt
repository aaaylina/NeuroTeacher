package ru.itis.neuroteacher.testcreation.domain.model

data class Test(
    val title: String,
    val questions: List<Question>
)