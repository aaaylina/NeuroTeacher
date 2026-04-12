package ru.itis.neuroteacher.auth.domain.usecase

interface SignUpUseCase {
    suspend operator fun invoke(
        email: String,
        password: String,
        confirmPassword: String
    ): Result<Unit>
}