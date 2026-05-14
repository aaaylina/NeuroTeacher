package ru.itis.neuroteacher.testcreation.data.model.remote

import com.google.firebase.firestore.PropertyName
import java.util.Date

class AnswerDetailDto(
    @get:PropertyName("questionIndex")
    @set:PropertyName("questionIndex")
    var questionIndex: Int = 0,

    @get:PropertyName("selectedAnswer")
    @set:PropertyName("selectedAnswer")
    var selectedAnswer: Int = 0,

    @get:PropertyName("correct")
    @set:PropertyName("correct")
    var correct: Boolean = false
)

data class QuizResultDto @JvmOverloads constructor(
    @get:PropertyName("quizId")
    @set:PropertyName("quizId")
    var quizId: String = "",

    @get:PropertyName("userId")
    @set:PropertyName("userId")
    var userId: String = "",

    @get:PropertyName("score")
    @set:PropertyName("score")
    var score: Int = 0,

    @get:PropertyName("totalQuestions")
    @set:PropertyName("totalQuestions")
    var totalQuestions: Int = 0,

    @get:PropertyName("correctCount")
    @set:PropertyName("correctCount")
    var correctCount: Int = 0,

    @get:PropertyName("completedAt")
    @set:PropertyName("completedAt")
    var completedAt: Date = Date(),

    @get:PropertyName("answers")
    @set:PropertyName("answers")
    var answers: List<AnswerDetailDto> = emptyList(),

    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String = ""
)
