package ru.itis.neuroteacher.testcreation.domain.repository

import ru.itis.neuroteacher.testcreation.data.db.model.SourceType
import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.model.TestResult

interface TestRepository {
    suspend fun saveTest(test: Test, sourceType: SourceType): Long
    suspend fun getTestById(id: Long): Test?
    suspend fun getAllTests(): List<Test>

    suspend fun saveResult(
        testId: Long,
        totalQuestions: Int,
        correctAnswers: Int,
        scorePercentage: Float,
        answers: List<Int>
    ): Long

    suspend fun getResultById(resultId: Long): TestResult?
    suspend fun getResultsByTestId(testId: Long): List<TestResult>
}