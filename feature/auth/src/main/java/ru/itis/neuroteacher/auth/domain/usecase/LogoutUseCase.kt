package ru.itis.neuroteacher.auth.domain.usecase

import ru.itis.neuroteacher.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.signOut()
    }
}