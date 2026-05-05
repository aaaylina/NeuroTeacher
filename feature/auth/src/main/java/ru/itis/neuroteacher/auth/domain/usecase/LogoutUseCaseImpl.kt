package ru.itis.neuroteacher.auth.domain.usecase

import ru.itis.neuroteacher.auth.domain.repository.AuthRepository
import javax.inject.Inject

internal class LogoutUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : LogoutUseCase {

    override suspend fun invoke(): Result<Unit> {
        return repository.signOut()
    }
}