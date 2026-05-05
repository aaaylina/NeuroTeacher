package ru.itis.neuroteacher.testcreation.data.usecase

import ru.itis.neuroteacher.testcreation.data.model.remote.AnswerDetailDto
import ru.itis.neuroteacher.testcreation.data.model.remote.QuestionDto
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizDto
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizResultDto
import ru.itis.neuroteacher.testcreation.domain.model.Test
import ru.itis.neuroteacher.testcreation.domain.repository.FirebaseQuizRepository
import ru.itis.neuroteacher.testcreation.domain.repository.TestRepository
import ru.itis.neuroteacher.testcreation.domain.usecase.SyncTestToFirebaseUseCase
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

        val result = localRepo.getResultById(resultId)
            ?: throw Exception("Результат не найден")

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

        firebaseRepo.updateUserStatisticsAfterQuiz(quizId, resultDto).getOrThrow()

        quizId
    }
}