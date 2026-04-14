package ru.itis.neuroteacher.auth.utils.validation

import ru.itis.neuroteacher.auth.utils.constants.ErrorMessages.PASSWORD_CONFIRM_EMPTY
import ru.itis.neuroteacher.auth.utils.constants.ErrorMessages.PASSWORD_EMPTY
import ru.itis.neuroteacher.auth.utils.constants.ErrorMessages.PASSWORD_MIN
import ru.itis.neuroteacher.auth.utils.constants.ErrorMessages.PASSWORD_MISMATCH
import ru.itis.neuroteacher.auth.utils.constants.ErrorMessages.SYMBOLS

object PasswordValidator {

    private const val MIN_LENGTH = 6

    fun validate(password: String): ValidationResult {
        return when {
            password.isBlank() -> ValidationResult.Error(PASSWORD_EMPTY)
            password.length < MIN_LENGTH -> ValidationResult.Error(PASSWORD_MIN + MIN_LENGTH + SYMBOLS)
            else -> ValidationResult.Success
        }
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult{
        return when {
            confirmPassword.isBlank() -> ValidationResult.Error(PASSWORD_CONFIRM_EMPTY)
            password != confirmPassword -> ValidationResult.Error(PASSWORD_MISMATCH)
            else -> ValidationResult.Success
        }
    }
}