package ru.itis.neuroteacher.testcreation.domain.usecase

import ru.itis.neuroteacher.testcreation.data.model.remote.AnswerDetailDto
import ru.itis.neuroteacher.testcreation.data.model.remote.QuestionDto
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizDto
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizResultDto
import ru.itis.neuroteacher.testcreation.data.repository.FirebaseQuizRepository
import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SyncTestToFirebaseUseCase @Inject constructor(
    private val firebaseRepo: FirebaseQuizRepository,
    private val localRepo: TestRepository
) {

    suspend fun syncCompleteTest(
        test: Test,
        resultId: Long,
        answers: List<Int>,
        correctCount: Int,
        scorePercentage: Float
    ): Result<String> = runCatching {
        // 1. Сохраняем тест
        val quizDto = QuizDto(
            title = test.title,
            questions = test.questions.map { question ->
                QuestionDto(
                    text = question.text,
                    answers = question.options,
                    correctAnswer = question.correctIndex,
                    explanation = question.explanation
                )
            }
        )
        val quizId = firebaseRepo.saveQuiz(quizDto).getOrThrow()

        // 2. Получаем результат
        val result = localRepo.getResultById(resultId)
            ?: throw Exception("Результат не найден")

        // 3. Создаем DTO результата
        val answerDetails = result.questions.mapIndexed { index, q ->
            AnswerDetailDto(
                questionIndex = index,
                selectedAnswer = q.selectedOptionIndex,
                isCorrect = q.isCorrect
            )
        }

        val resultDto = QuizResultDto(
            score = result.scorePercentage.toInt(),
            totalQuestions = result.totalQuestions,
            correctCount = result.correctAnswers,
            answers = answerDetails
        )

        // 4. Сохраняем результат и обновляем статистику пользователя
        firebaseRepo.updateUserStatisticsAfterQuiz(quizId, resultDto).getOrThrow()

        quizId
    }
}