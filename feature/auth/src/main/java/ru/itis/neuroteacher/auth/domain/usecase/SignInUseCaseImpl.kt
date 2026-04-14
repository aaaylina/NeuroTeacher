package ru.itis.neuroteacher.auth.domain.usecase

import ru.itis.neuroteacher.auth.domain.model.User
import ru.itis.neuroteacher.auth.domain.repository.AuthRepository
import ru.itis.neuroteacher.auth.utils.validation.EmailValidator
import ru.itis.neuroteacher.auth.utils.validation.PasswordValidator
import ru.itis.neuroteacher.auth.utils.validation.ValidationResult
import javax.inject.Inject

internal class SignInUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SignInUseCase{

    override suspend operator fun invoke(email: String, password: String): Result<User> {
        val emailResult = EmailValidator.validate(email)
        if (emailResult is ValidationResult.Error) {
            return Result.failure(Exception(emailResult.message))
        }

        val passwordResult = PasswordValidator.validate(password)
        if (passwordResult is ValidationResult.Error) {
            return Result.failure(Exception(passwordResult.message))
        }

        return authRepository.signIn(email, password)
    }
}