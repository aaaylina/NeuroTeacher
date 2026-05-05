package ru.itis.neuroteacher.testcreation.data.model.remote

import com.google.firebase.firestore.PropertyName
import java.util.Date

object QuizzesFields {
    const val TITLE = "title"
    const val USER_ID = "userid"
    const val CREATED_AT = "createdAt"
    const val QUESTIONS = "questions"
}

data class QuestionDto(
    @PropertyName("text")
    val text: String,

    @PropertyName("answers")
    val answers: List<String>,

    @PropertyName("correctAnswer")
    val correctAnswer: Int,

    @PropertyName("explanation")
    val explanation: String? = null
)

data class QuizDto(
    @get:PropertyName(QuizzesFields.TITLE)
    @set:PropertyName(QuizzesFields.TITLE)
    var title: String = "",

    @get:PropertyName(QuizzesFields.USER_ID)
    @set:PropertyName(QuizzesFields.USER_ID)
    var userId: String = "",

    @get:PropertyName(QuizzesFields.CREATED_AT)
    @set:PropertyName(QuizzesFields.CREATED_AT)
    var createdAt: Date = Date(),

    var questions: List<QuestionDto> = emptyList()
)