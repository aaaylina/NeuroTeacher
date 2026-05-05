package ru.itis.neuroteacher.testcreation.data.model.remote

import com.google.firebase.firestore.PropertyName
import java.util.Date

object UsersFields {
    const val USER_ID = "userid"
    const val EMAIL = "email"
    const val CREATED_AT = "createdAt"
    const val LAST_ACTIVE = "lastActive"
    const val TOTAL_QUIZZES = "totalQuizzes"
    const val TOTAL_COMPLETED = "totalCompleted"
    const val AVERAGE_SCORE = "averageScore"
    const val BEST_SCORE = "bestScore"
}

data class UserDto(
    @get:PropertyName(UsersFields.USER_ID)
    @set:PropertyName(UsersFields.USER_ID)
    var userId: String = "",

    @get:PropertyName(UsersFields.EMAIL)
    @set:PropertyName(UsersFields.EMAIL)
    var email: String = "",

    @get:PropertyName(UsersFields.CREATED_AT)
    @set:PropertyName(UsersFields.CREATED_AT)
    var createdAt: Date = Date(),

    @get:PropertyName(UsersFields.LAST_ACTIVE)
    @set:PropertyName(UsersFields.LAST_ACTIVE)
    var lastActive: Date = Date(),

    @get:PropertyName(UsersFields.TOTAL_QUIZZES)
    @set:PropertyName(UsersFields.TOTAL_QUIZZES)
    var totalQuizzes: Int = 0,

    @get:PropertyName(UsersFields.TOTAL_COMPLETED)
    @set:PropertyName(UsersFields.TOTAL_COMPLETED)
    var totalCompleted: Int = 0,

    @get:PropertyName(UsersFields.AVERAGE_SCORE)
    @set:PropertyName(UsersFields.AVERAGE_SCORE)
    var averageScore: Float = 0f,

    @get:PropertyName(UsersFields.BEST_SCORE)
    @set:PropertyName(UsersFields.BEST_SCORE)
    var bestScore: Int = 0
)