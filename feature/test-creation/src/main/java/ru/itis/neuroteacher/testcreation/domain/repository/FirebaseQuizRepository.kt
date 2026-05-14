package ru.itis.neuroteacher.testcreation.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizDto
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizResultDto
import ru.itis.neuroteacher.testcreation.data.model.remote.UserDto
import javax.inject.Singleton

@Singleton
interface FirebaseQuizRepository {
    suspend fun createUser(userDto: UserDto): Result<Unit>
    suspend fun updateUserLastActive(): Result<Unit>
    suspend fun getUserStatistics(): Result<UserDto?>
    suspend fun updateUserStatistics(userDto: UserDto): Result<Unit>

    suspend fun saveQuiz(quiz: QuizDto): Result<String>
    suspend fun getUserQuizzes(): Result<List<QuizDto>>
    suspend fun getQuizById(quizId: String): Result<QuizDto?>
    suspend fun deleteQuiz(quizId: String): Result<Unit>

    suspend fun saveQuizResult(quizId: String, result: QuizResultDto): Result<String>
    suspend fun getQuizResults(): Result<List<QuizResultDto>>
    suspend fun getQuizResultsForQuiz(quizId: String): Result<List<QuizResultDto>>

    fun observeUserStatistics(): Flow<UserDto?>
    fun observeUserQuizzes(): Flow<List<QuizDto>>
    fun observeQuizResults(): Flow<List<QuizResultDto>>
}