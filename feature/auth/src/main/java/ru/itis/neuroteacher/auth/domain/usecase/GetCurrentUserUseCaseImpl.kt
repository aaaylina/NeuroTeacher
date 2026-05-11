package ru.itis.neuroteacher.auth.domain.usecase

import ru.itis.neuroteacher.auth.domain.model.User
import ru.itis.neuroteacher.auth.domain.repository.AuthRepository
import javax.inject.Inject

internal class GetCurrentUserUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : GetCurrentUserUseCase {
    override fun invoke(): User? {
        val user =  authRepository.getCurrentUser()
        return user
    }
}