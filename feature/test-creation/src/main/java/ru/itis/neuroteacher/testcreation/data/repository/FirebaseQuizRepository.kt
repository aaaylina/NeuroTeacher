package ru.itis.neuroteacher.testcreation.data.repository

import android.util.Log
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
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseQuizRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {

    companion object {
        private const val QUIZZES_COLLECTION = "quizzes"
        private const val RESULTS_COLLECTION = "quizResults"
        private const val USERS_COLLECTION = "users"
        private const val TAG = "FirebaseRepo"
    }

    // ==================== ПОЛЬЗОВАТЕЛЬ ====================

    suspend fun createUser(userDto: UserDto): Result<Unit> = runCatching {
        val userId = auth.currentUser?.uid ?: throw Exception("Пользователь не авторизован")
        userDto.userId = userId
        userDto.createdAt = Date()
        userDto.lastActive = Date()

        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .set(userDto)
            .await()

        Log.d(TAG, "✅ Пользователь создан: $userId")
    }

    suspend fun updateUserLastActive(): Result<Unit> = runCatching {
        val userId = auth.currentUser?.uid ?: throw Exception("Пользователь не авторизован")

        val updates = hashMapOf<String, Any>(
            UsersFields.LAST_ACTIVE to Date()
        )

        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .update(updates)
            .await()

        Log.d(TAG, "✅ Обновлен lastActive для: $userId")
    }

    suspend fun getUser(userId: String): Result<UserDto?> = runCatching {
        val document = firestore.collection(USERS_COLLECTION)
            .document(userId)
            .get()
            .await()

        document.toObject(UserDto::class.java)
    }

    // ==================== ТЕСТЫ ====================

    suspend fun saveQuiz(quiz: QuizDto): Result<String> = runCatching {
        val userId = auth.currentUser?.uid ?: throw Exception("Пользователь не авторизован")
        quiz.userId = userId
        quiz.createdAt = Date()

        Log.d(TAG, "📝 Сохраняем тест: ${quiz.title}")

        val docRef = firestore.collection(QUIZZES_COLLECTION).add(quiz).await()

        Log.d(TAG, "✅ Тест сохранен! ID: ${docRef.id}")
        docRef.id
    }

    // ==================== РЕЗУЛЬТАТЫ ====================

    suspend fun saveQuizResult(quizId: String, result: QuizResultDto): Result<String> = runCatching {
        val userId = auth.currentUser?.uid ?: throw Exception("Пользователь не авторизован")
        result.userId = userId
        result.quizId = quizId
        result.completedAt = Date()

        Log.d(TAG, "📊 Сохраняем результат для quizId: $quizId, score: ${result.score}")

        val docRef = firestore.collection(RESULTS_COLLECTION).add(result).await()

        Log.d(TAG, "✅ Результат сохранен! ID: ${docRef.id}")
        docRef.id
    }

    suspend fun updateUserStatisticsAfterQuiz(quizId: String, resultDto: QuizResultDto): Result<Unit> = runCatching {
        val userId = auth.currentUser?.uid ?: throw Exception("Пользователь не авторизован")

        // 1. Сохраняем результат
        saveQuizResult(quizId, resultDto).getOrThrow()

        // 2. Получаем СВОИ результаты пользователя (исправлено)
        val userRef = firestore.collection(USERS_COLLECTION).document(userId)

        // Проверяем, существует ли пользователь
        val userDoc = userRef.get().await()
        if (!userDoc.exists()) {
            // Если пользователь не существует, создаем его
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

        // 3. Получаем все результаты пользователя
        val allResults = firestore.collection(RESULTS_COLLECTION)
            .whereEqualTo(QuizResultsFields.USER_ID, userId)
            .get()
            .await()
            .documents
            .mapNotNull { doc ->
                try {
                    doc.toObject(QuizResultDto::class.java)
                } catch (e: Exception) {
                    Log.e(TAG, "Ошибка десериализации результата: ${e.message}")
                    null
                }
            }

        if (allResults.isEmpty()) {
            // Если нет результатов, создаем пользователя
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

        // 4. Вычисляем статистику
        val totalQuizzes = allResults.distinctBy { it.quizId }.size
        val totalCompleted = allResults.size
        val averageScore = allResults.map { it.score }.average().toFloat()
        val bestScore = allResults.maxOfOrNull { it.score } ?: 0

        // 5. Обновляем пользователя
        val updates = hashMapOf<String, Any>(
            UsersFields.TOTAL_QUIZZES to totalQuizzes,
            UsersFields.TOTAL_COMPLETED to totalCompleted,
            UsersFields.AVERAGE_SCORE to averageScore,
            UsersFields.BEST_SCORE to bestScore,
            UsersFields.LAST_ACTIVE to Date()
        )

        userRef.update(updates).await()

        Log.d(TAG, "📊 Статистика обновлена: totalQuizzes=$totalQuizzes, bestScore=$bestScore, averageScore=$averageScore")
    }

    // ==================== ПОЛУЧЕНИЕ ДАННЫХ ====================

    suspend fun getUserQuizzes(): Result<List<QuizDto>> = runCatching {
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

    suspend fun getUserStatistics(): Result<UserDto?> = runCatching {
        val userId = auth.currentUser?.uid ?: return@runCatching null

        val document = firestore.collection(USERS_COLLECTION)
            .document(userId)
            .get()
            .await()

        document.toObject(UserDto::class.java)
    }
}