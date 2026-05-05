package ru.itis.neuroteacher.auth.domain.usecase

interface LogoutUseCase {
    suspend operator fun invoke(): Result<Unit>
}