package ru.itis.neuroteacher.auth.domain.repository

import ru.itis.neuroteacher.auth.domain.model.User

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Result<User>
    suspend fun signUp(email: String, password: String): Result<User>
    suspend fun signOut(): Result<Unit>
    fun isUserLoggedIn(): Boolean
    fun getCurrentUserId(): User?
    fun getCurrentUser(): User?
}