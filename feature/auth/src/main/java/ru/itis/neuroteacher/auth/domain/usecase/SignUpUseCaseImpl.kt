package ru.itis.neuroteacher.auth.domain.usecase

import ru.itis.neuroteacher.auth.domain.repository.AuthRepository
import ru.itis.neuroteacher.auth.utils.validation.EmailValidator
import ru.itis.neuroteacher.auth.utils.validation.PasswordValidator
import ru.itis.neuroteacher.auth.utils.validation.ValidationResult
import javax.inject.Inject

internal class SignUpUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignUpUseCase {

    override suspend operator fun invoke(
        email: String,
        password: String,
        confirmPassword: String
    ): Result<Unit> {
        val emailResult = EmailValidator.validate(email)
        if (emailResult is ValidationResult.Error) {
            return Result.failure(Exception(emailResult.message))
        }

        val passwordResult = PasswordValidator.validate(password)
        if (passwordResult is ValidationResult.Error) {
            return Result.failure(Exception(passwordResult.message))
        }

        val confirmResult = PasswordValidator.validateConfirmPassword(password, confirmPassword)
        if (confirmResult is ValidationResult.Error) {
            return Result.failure(Exception(confirmResult.message))
        }

        return authRepository.signUp(email, password)
    }
}