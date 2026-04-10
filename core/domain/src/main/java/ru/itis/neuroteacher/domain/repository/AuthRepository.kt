package ru.itis.neuroteacher.domain.repository

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Result<Unit>
    suspend fun signUp(email: String, password: String): Result<Unit>
    suspend fun signOut(): Result<Unit>
    fun isUserLoggedIn(): Boolean
    fun getCurrentUserId(): String?
}