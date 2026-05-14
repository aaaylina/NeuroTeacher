package ru.itis.neuroteacher.testcreation.domain.usecase

import ru.itis.neuroteacher.testcreation.data.model.remote.AnswerDetailDto
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizResultDto
import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.repository.FirebaseQuizRepository
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import javax.inject.Inject

internal class SyncTestToFirebaseUseCaseImpl @Inject constructor(
    private val firebaseRepo: FirebaseQuizRepository,
    private val localRepo: TestRepository
) : SyncTestToFirebaseUseCase {

    override suspend fun syncCompleteTest(
        test: Test,
        resultId: Long,
        answers: List<Int>,
        correctCount: Int,
        scorePercentage: Float
    ): Result<String> = runCatching {

        val existingResult = localRepo.getResultById(resultId)
            ?: throw Exception("Result not found locally")

        val quizId = localRepo.getOrCreateRemoteQuizId(existingResult.testId, test).getOrThrow()

        val answerDetails = existingResult.questions.mapIndexed { index, q ->
            AnswerDetailDto(
                questionIndex = index,
                selectedAnswer = q.selectedOptionIndex,
                correct = q.isCorrect
            )
        }

        val resultDto = QuizResultDto(
            quizId = quizId,
            score = existingResult.scorePercentage.toInt(),
            totalQuestions = existingResult.totalQuestions,
            correctCount = existingResult.correctAnswers,
            answers = answerDetails
        )

        firebaseRepo.saveQuizResult(quizId, resultDto).getOrThrow()
        updateUserStatistics()
        quizId
    }

    private suspend fun updateUserStatistics() {
        try {
            val allResults = firebaseRepo.getQuizResults().getOrNull() ?: emptyList()

            if (allResults.isEmpty()) {
                return
            }

            val uniqueQuizIds = allResults.map { it.quizId }.distinct()
            val totalQuizzes = uniqueQuizIds.size
            val totalCompleted = allResults.size
            val averageScore = allResults.map { it.score }.average().toFloat()
            val bestScore = allResults.maxOfOrNull { it.score } ?: 0

            val currentUser = firebaseRepo.getUserStatistics().getOrNull()

            if (currentUser != null) {
                val updatedUser = currentUser.copy(
                    totalQuizzes = totalQuizzes,
                    totalCompleted = totalCompleted,
                    averageScore = averageScore,
                    bestScore = bestScore,
                    lastActive = java.util.Date()
                )
                firebaseRepo.updateUserStatistics(updatedUser)
            }
        } catch (e: Exception) {
        }
    }
}