package ru.itis.neuroteacher.domain.usecase.auth

import ru.itis.neuroteacher.domain.repository.AuthRepository
import ru.itis.neuroteacher.utils.validation.EmailValidator
import ru.itis.neuroteacher.utils.validation.PasswordValidator
import ru.itis.neuroteacher.utils.validation.ValidationResult
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        when (val result = EmailValidator.validate(email)) {
            is ValidationResult.Error -> return Result.failure(Exception(result.message))
            else -> {}
        }

        when (val result = PasswordValidator.validate(password)) {
            is ValidationResult.Error -> return Result.failure(Exception(result.message))
            else -> {}
        }

        return authRepository.signIn(email, password)
    }
}