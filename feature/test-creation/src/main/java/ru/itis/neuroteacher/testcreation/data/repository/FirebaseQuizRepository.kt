package ru.itis.neuroteacher.testcreation.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizDto
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizResultDto
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizResultsFields
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizzesFields
import ru.itis.neuroteacher.testcreation.data.model.remote.UserDto
import ru.itis.neuroteacher.testcreation.data.model.remote.UsersFields
import ru.itis.neuroteacher.testcreation.domain.repository.FirebaseQuizRepository
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirebaseQuizRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
): FirebaseQuizRepository {

    companion object {
        private const val QUIZZES_COLLECTION = "quizzes"
        private const val RESULTS_COLLECTION = "quizResults"
        private const val USERS_COLLECTION = "users"
    }

    override suspend fun createUser(userDto: UserDto): Result<Unit> = runCatching {
        val userId = auth.currentUser?.uid ?: throw Exception("Пользователь не авторизован")
        userDto.userId = userId
        userDto.createdAt = Date()
        userDto.lastActive = Date()

        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .set(userDto)
            .await()
    }

    override suspend fun updateUserLastActive(): Result<Unit> = runCatching {
        val userId = auth.currentUser?.uid ?: throw Exception("Пользователь не авторизован")

        val updates = hashMapOf<String, Any>(
            UsersFields.LAST_ACTIVE to Date()
        )

        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .update(updates)
            .await()
    }

    override suspend fun getUser(userId: String): Result<UserDto?> = runCatching {
        val document = firestore.collection(USERS_COLLECTION)
            .document(userId)
            .get()
            .await()

        document.toObject(UserDto::class.java)
    }

    override suspend fun saveQuiz(quiz: QuizDto): Result<String> = runCatching {
        val userId = auth.currentUser?.uid ?: throw Exception("Пользователь не авторизован")
        quiz.userId = userId
        quiz.createdAt = Date()

        val docRef = firestore.collection(QUIZZES_COLLECTION).add(quiz).await()

        docRef.id
    }

    override suspend fun saveQuizResult(quizId: String, result: QuizResultDto): Result<String> = runCatching {
        val userId = auth.currentUser?.uid ?: throw Exception("Пользователь не авторизован")
        result.userId = userId
        result.quizId = quizId
        result.completedAt = Date()

        val docRef = firestore.collection(RESULTS_COLLECTION).add(result).await()
        docRef.id
    }

    override suspend fun updateUserStatisticsAfterQuiz(quizId: String, resultDto: QuizResultDto): Result<Unit> = runCatching {
        val userId = auth.currentUser?.uid ?: throw Exception("Пользователь не авторизован")

        saveQuizResult(quizId, resultDto).getOrThrow()

        val userRef = firestore.collection(USERS_COLLECTION).document(userId)

        val userDoc = userRef.get().await()
        if (!userDoc.exists()) {
            val newUser = UserDto(
                userId = userId,
                email = auth.currentUser?.email ?: "",
                createdAt = Date(),
                lastActive = Date(),
                totalQuizzes = 1,
                totalCompleted = 1,
                averageScore = resultDto.score.toFloat(),
                bestScore = resultDto.score
            )
            userRef.set(newUser).await()
            return@runCatching
        }

        val allResults = firestore.collection(RESULTS_COLLECTION)
            .whereEqualTo(QuizResultsFields.USER_ID, userId)
            .get()
            .await()
            .documents
            .mapNotNull { doc ->
                try {
                    doc.toObject(QuizResultDto::class.java)
                } catch (e: Exception) {
                    null
                }
            }

        if (allResults.isEmpty()) {
            val newUser = UserDto(
                userId = userId,
                email = auth.currentUser?.email ?: "",
                createdAt = Date(),
                lastActive = Date(),
                totalQuizzes = 1,
                totalCompleted = 1,
                averageScore = resultDto.score.toFloat(),
                bestScore = resultDto.score
            )
            userRef.set(newUser).await()
            return@runCatching
        }

        val totalQuizzes = allResults.distinctBy { it.quizId }.size
        val totalCompleted = allResults.size
        val averageScore = allResults.map { it.score }.average().toFloat()
        val bestScore = allResults.maxOfOrNull { it.score } ?: 0

        val updates = hashMapOf<String, Any>(
            UsersFields.TOTAL_QUIZZES to totalQuizzes,
            UsersFields.TOTAL_COMPLETED to totalCompleted,
            UsersFields.AVERAGE_SCORE to averageScore,
            UsersFields.BEST_SCORE to bestScore,
            UsersFields.LAST_ACTIVE to Date()
        )

        userRef.update(updates).await()
    }

    override suspend fun getUserQuizzes(): Result<List<QuizDto>> = runCatching {
        val userId = auth.currentUser?.uid
        if (userId == null) {
            return@runCatching emptyList()
        }

        firestore.collection(QUIZZES_COLLECTION)
            .whereEqualTo(QuizzesFields.USER_ID, userId)
            .orderBy(QuizzesFields.CREATED_AT, Query.Direction.DESCENDING)
            .get()
            .await()
            .documents
            .mapNotNull { document ->
                document.toObject(QuizDto::class.java)
            }
    }

    override suspend fun getUserStatistics(): Result<UserDto?> = runCatching {
        val userId = auth.currentUser?.uid ?: return@runCatching null

        val document = firestore.collection(USERS_COLLECTION)
            .document(userId)
            .get()
            .await()

        document.toObject(UserDto::class.java)
    }
}