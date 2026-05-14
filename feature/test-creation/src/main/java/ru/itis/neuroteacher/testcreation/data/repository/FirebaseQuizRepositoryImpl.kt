package ru.itis.neuroteacher.testcreation.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizDto
import ru.itis.neuroteacher.testcreation.data.model.remote.QuizResultDto
import ru.itis.neuroteacher.testcreation.data.model.remote.UserDto
import ru.itis.neuroteacher.testcreation.data.model.remote.UsersFields
import ru.itis.neuroteacher.testcreation.domain.repository.FirebaseQuizRepository
import java.util.Date
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirebaseQuizRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : FirebaseQuizRepository {

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val QUIZZES_SUBCOLLECTION = "quizzes"
        private const val RESULTS_SUBCOLLECTION = "quizResults"
    }

    private val currentUserId: String?
        get() = auth.currentUser?.uid

    private fun getUserDocumentRef() = currentUserId?.let { userId ->
        firestore.collection(USERS_COLLECTION).document(userId)
    }

    private fun getUserQuizzesSubcollection() = getUserDocumentRef()?.collection(QUIZZES_SUBCOLLECTION)

    private fun getUserQuizResultsSubcollection() = getUserDocumentRef()?.collection(RESULTS_SUBCOLLECTION)


    override suspend fun createUser(userDto: UserDto): Result<Unit> = runCatching {
        val userId = currentUserId ?: throw Exception("Пользователь не авторизован")
        userDto.userId = userId
        userDto.createdAt = Date()
        userDto.lastActive = Date()

        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .set(userDto)
            .await()

    }

    override suspend fun updateUserLastActive(): Result<Unit> = runCatching {
        val userId = currentUserId ?: throw Exception("Пользователь не авторизован")

        val updates = hashMapOf<String, Any>(
            UsersFields.LAST_ACTIVE to Date()
        )

        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .update(updates)
            .await()

    }

    override suspend fun getUserStatistics(): Result<UserDto?> = runCatching {
        val userId = currentUserId ?: return@runCatching null

        val document = firestore.collection(USERS_COLLECTION)
            .document(userId)
            .get()
            .await()

        document.toObject(UserDto::class.java)
    }

    override suspend fun updateUserStatistics(userDto: UserDto): Result<Unit> = runCatching {
        val userId = currentUserId ?: throw Exception("Пользователь не авторизован")
        userDto.userId = userId
        userDto.lastActive = Date()

        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .set(userDto)
            .await()

    }

    override suspend fun saveQuiz(quiz: QuizDto): Result<String> = runCatching {
        val userId = currentUserId ?: throw Exception("Пользователь не авторизован")
        quiz.userId = userId
        quiz.createdAt = Date()

        val quizzesRef = getUserQuizzesSubcollection()
            ?: throw Exception("Не удалось получить ссылку на quizzes")

        val quizId = UUID.randomUUID().toString()
        quiz.id = quizId

        quizzesRef.document(quizId).set(quiz).await()

        quiz.id


    }

    override suspend fun getUserQuizzes(): Result<List<QuizDto>> = runCatching {
        val quizzesRef = getUserQuizzesSubcollection()
        if (quizzesRef == null) {
            return@runCatching emptyList()
        }

        val snapshot = quizzesRef.get().await()

        val quizzes = snapshot.documents.mapNotNull { document ->
            document.toObject(QuizDto::class.java)?.apply {
                id = document.id
            }
        }.sortedWith(compareByDescending { it.createdAt.time })

        quizzes
    }

    override suspend fun getQuizById(quizId: String): Result<QuizDto?> = runCatching {
        val quizzesRef = getUserQuizzesSubcollection() ?: return@runCatching null
        val document = quizzesRef.document(quizId).get().await()
        document.toObject(QuizDto::class.java)?.apply {
            id = document.id
        }
    }

    override suspend fun deleteQuiz(quizId: String): Result<Unit> = runCatching {
        val quizzesRef = getUserQuizzesSubcollection()
            ?: throw Exception("Пользователь не авторизован")

        quizzesRef.document(quizId).delete().await()
    }


    override suspend fun saveQuizResult(quizId: String, result: QuizResultDto): Result<String> = runCatching {
        val userId = currentUserId ?: throw Exception("Пользователь не авторизован")
        result.userId = userId
        result.quizId = quizId
        result.completedAt = Date()

        val resultsRef = getUserQuizResultsSubcollection()
            ?: throw Exception("Не удалось получить ссылку на quizResults")

        val docRef = resultsRef.document()
        result.id = docRef.id
        docRef.set(result).await()

        result.id
    }

    override suspend fun getQuizResults(): Result<List<QuizResultDto>> = runCatching {
        val resultsRef = getUserQuizResultsSubcollection()
        if (resultsRef == null) {
            return@runCatching emptyList()
        }

        val snapshot = resultsRef
            .orderBy("completedAt", Query.Direction.DESCENDING)
            .get()
            .await()

        val results = snapshot.documents.mapNotNull { document ->
            document.toObject(QuizResultDto::class.java)?.apply {
                id = document.id
            }
        }

        results
    }

    override suspend fun getQuizResultsForQuiz(quizId: String): Result<List<QuizResultDto>> = runCatching {
        val resultsRef = getUserQuizResultsSubcollection()
        if (resultsRef == null) {
            return@runCatching emptyList()
        }

        val snapshot = resultsRef
            .whereEqualTo("quizId", quizId)
            .orderBy("completedAt", Query.Direction.DESCENDING)
            .get()
            .await()

        snapshot.documents.mapNotNull { document ->
            document.toObject(QuizResultDto::class.java)?.apply {
                id = document.id
            }
        }
    }

    override fun observeUserStatistics(): Flow<UserDto?> = callbackFlow {
        val userId = currentUserId
        if (userId == null) {
            trySend(null)
            awaitClose { }
            return@callbackFlow
        }

        val listener = firestore.collection(USERS_COLLECTION)
            .document(userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val userStats = snapshot?.toObject(UserDto::class.java)
                trySend(userStats)
            }

        awaitClose {
            listener.remove()
        }
    }

    override fun observeUserQuizzes(): Flow<List<QuizDto>> = callbackFlow {
        val quizzesRef = getUserQuizzesSubcollection()
        if (quizzesRef == null) {
            trySend(emptyList())
            awaitClose { }
            return@callbackFlow
        }

        val listener = quizzesRef.addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val quizzes = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(QuizDto::class.java)?.apply { id = doc.id }
                }?.sortedWith(compareByDescending { it.createdAt.time }) ?: emptyList()

                trySend(quizzes)
            }

        awaitClose {
            listener.remove()
        }
    }

    override fun observeQuizResults(): Flow<List<QuizResultDto>> = callbackFlow {
        val resultsRef = getUserQuizResultsSubcollection()
        if (resultsRef == null) {
            trySend(emptyList())
            awaitClose { }
            return@callbackFlow
        }

        val listener = resultsRef
            .orderBy("completedAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val results = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(QuizResultDto::class.java)?.apply { id = doc.id }
                } ?: emptyList()

                trySend(results)
            }

        awaitClose {
            listener.remove()
        }
    }
}