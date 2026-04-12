package ru.itis.neuroteacher.auth.domain.usecase

import ru.itis.neuroteacher.auth.domain.model.User

interface SignInUseCase {
    suspend operator fun invoke(email: String, password: String): Result<User>
}