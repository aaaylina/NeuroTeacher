package ru.itis.neuroteacher.testcreation.domain.model

data class TestStatistics(
    val totalTests: Int = 0,
    val completedTests: Int = 0,
    val averageScore: Float = 0f,
    val bestScore: Float = 0f
)