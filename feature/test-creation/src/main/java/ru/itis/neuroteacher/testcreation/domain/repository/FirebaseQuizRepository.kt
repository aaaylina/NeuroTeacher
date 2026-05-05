package ru.itis.neuroteacher.testcreation.domain.repository

import ru.itis.neuroteacher.testcreation.data.model.remote.QuizDto
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizResultDto
import ru.itis.neuroteacher.testcreation.data.model.remote.UserDto
import javax.inject.Singleton

@Singleton
interface FirebaseQuizRepository {
    suspend fun createUser(userDto: UserDto): Result<Unit>
    suspend fun updateUserLastActive(): Result<Unit>
    suspend fun getUser(userId: String): Result<UserDto?>
    suspend fun saveQuiz(quiz: QuizDto): Result<String>
    suspend fun saveQuizResult(quizId: String, result: QuizResultDto): Result<String>
    suspend fun updateUserStatisticsAfterQuiz(quizId: String, resultDto: QuizResultDto): Result<Unit>
    suspend fun getUserQuizzes(): Result<List<QuizDto>>
    suspend fun getUserStatistics(): Result<UserDto?>
}