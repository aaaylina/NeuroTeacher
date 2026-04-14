package ru.itis.neuroteacher.auth.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import ru.itis.neuroteacher.auth.data.mapper.UserMapper
import ru.itis.neuroteacher.auth.domain.model.User
import ru.itis.neuroteacher.auth.domain.repository.AuthErrorHandler
import ru.itis.neuroteacher.auth.domain.repository.AuthRepository
import ru.itis.neuroteacher.auth.utils.constants.ErrorMessages
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val errorHandler: AuthErrorHandler,
    private val userMapper: UserMapper
) : AuthRepository {

    private val auth: FirebaseAuth = Firebase.auth

    override suspend fun signIn(email: String, password: String): Result<User> {
        return runCatching {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user ?: throw Exception("User is null")
            val entity = userMapper.toEntity(firebaseUser)
            userMapper.toDomain(entity)
        }.fold(
            onSuccess = { user ->
                Result.success(user)
            },
            onFailure = { e ->
                Result.failure(Exception(errorHandler.handle(e)))
            }
        )
    }

    override suspend fun signUp(email: String, password: String): Result<User> {
        return runCatching {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user ?: throw Exception("User is null")
            val entity = userMapper.toEntity(firebaseUser)
            userMapper.toDomain(entity)
        }.fold(
            onSuccess = { user ->
                Result.success(user)
            },
            onFailure = { e ->
                Result.failure(Exception(errorHandler.handle(e)))
            }
        )
    }

    override suspend fun signOut(): Result<Unit> {
        return runCatching {
            auth.signOut()
        }.fold(
            onSuccess = { Result.success(Unit)},
            onFailure = {
                Result.failure(Exception(ErrorMessages.SIGN_OUT_ERROR))
            }
        )
    }

    override fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    override fun getCurrentUserId(): User? {
        val firebaseUser = auth.currentUser ?: return null
        val entity = userMapper.toEntity(firebaseUser)
        return userMapper.toDomain(entity)
    }

}