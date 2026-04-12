package ru.itis.neuroteacher.auth.domain.usecase

import ru.itis.neuroteacher.auth.domain.model.User

interface SignUpUseCase {
    suspend operator fun invoke(
        email: String,
        password: String,
        confirmPassword: String
    ): Result<User>
}