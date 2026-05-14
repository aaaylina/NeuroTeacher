package ru.itis.neuroteacher.testcreation.data.model.remote

import com.google.firebase.firestore.PropertyName
import java.util.Date
class QuestionDto(
    @get:PropertyName("text")
    @set:PropertyName("text")
    var text: String = "",

    @get:PropertyName("answers")
    @set:PropertyName("answers")
    var answers: List<String> = emptyList(),

    @get:PropertyName("correctAnswer")
    @set:PropertyName("correctAnswer")
    var correctAnswer: Int = 0,

    @get:PropertyName("explanation")
    @set:PropertyName("explanation")
    var explanation: String? = null
)

data class QuizDto @JvmOverloads constructor(
    @get:PropertyName("title")
    @set:PropertyName("title")
    var title: String = "",

    @get:PropertyName("userId")
    @set:PropertyName("userId")
    var userId: String = "",

    @get:PropertyName("createdAt")
    @set:PropertyName("createdAt")
    var createdAt: Date = Date(),

    @get:PropertyName("questions")
    @set:PropertyName("questions")
    var questions: List<QuestionDto> = emptyList(),

    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String = ""
)
