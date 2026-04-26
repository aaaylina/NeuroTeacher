package ru.itis.neuroteacher.testtaking.domain.model

data class TestStatistics(
    val totalTests: Int,
    val completedTests: Int,
    val averageScore: Float?,
    val bestScore: Float?
)