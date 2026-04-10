package ru.itis.neuroteacher.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import ru.itis.neuroteacher.domain.repository.AuthErrorHandler
import ru.itis.neuroteacher.domain.repository.AuthRepository
import ru.itis.neuroteacher.utils.constants.ErrorMessages
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val errorHandler: AuthErrorHandler
) : AuthRepository {

    private val auth: FirebaseAuth = Firebase.auth

    override suspend fun signIn(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            val message = errorHandler.handle(e)
            Result.failure(Exception(message))
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Unit> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            val message = errorHandler.handle(e)
            Result.failure(Exception(message))
        }
    }

    override suspend fun signOut(): Result<Unit> {
        return try {
            auth.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception(ErrorMessages.SIGN_OUT_ERROR))
        }
    }

    override fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    override fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }

}