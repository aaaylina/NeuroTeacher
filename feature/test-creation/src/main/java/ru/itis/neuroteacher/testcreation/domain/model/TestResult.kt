package ru.itis.neuroteacher.testcreation.domain.model

data class TestResult(
    val testTitle: String,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val scorePercentage: Float,
    val questions: List<QuestionResult>
) {
    data class QuestionResult(
        val questionText: String,
        val selectedOptionIndex: Int,
        val correctOptionIndex: Int,
        val isCorrect: Boolean,
        val explanation: String?
    )
}