package ru.itis.neuroteacher.testcreation.data.model.remote

import com.google.firebase.firestore.PropertyName
import java.util.Date

object QuizResultsFields {
    const val USER_ID = "userid"
    const val QUIZ_ID = "quizId"
    const val COMPLETED_AT = "completedAt"
    const val SCORE = "score"
    const val TOTAL_QUESTIONS = "totalQuestions"
    const val CORRECT_COUNT = "correctCount"
    const val ANSWERS = "answers"
}

data class AnswerDetailDto(
    @PropertyName("questionIndex")
    val questionIndex: Int = 0,

    @PropertyName("selectedAnswer")
    val selectedAnswer: Int = 0,

    @PropertyName("isCorrect")
    val isCorrect: Boolean = false
){
    constructor() : this(0, 0, false)
}

data class QuizResultDto(
    @get:PropertyName(QuizResultsFields.USER_ID)
    @set:PropertyName(QuizResultsFields.USER_ID)
    var userId: String = "",

    @get:PropertyName(QuizResultsFields.QUIZ_ID)
    @set:PropertyName(QuizResultsFields.QUIZ_ID)
    var quizId: String = "",

    @get:PropertyName(QuizResultsFields.COMPLETED_AT)
    @set:PropertyName(QuizResultsFields.COMPLETED_AT)
    var completedAt: Date = Date(),

    @get:PropertyName(QuizResultsFields.SCORE)
    @set:PropertyName(QuizResultsFields.SCORE)
    var score: Int = 0,

    @get:PropertyName(QuizResultsFields.TOTAL_QUESTIONS)
    @set:PropertyName(QuizResultsFields.TOTAL_QUESTIONS)
    var totalQuestions: Int = 0,

    @get:PropertyName(QuizResultsFields.CORRECT_COUNT)
    @set:PropertyName(QuizResultsFields.CORRECT_COUNT)
    var correctCount: Int = 0,

    var answers: List<AnswerDetailDto> = emptyList()
)