package ru.itis.neuroteacher.auth.domain.usecase

interface SignInUseCase {
    suspend operator fun invoke(email: String, password: String): Result<Unit>
}