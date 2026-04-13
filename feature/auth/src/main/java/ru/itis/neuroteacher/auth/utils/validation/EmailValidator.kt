package ru.itis.neuroteacher.auth.utils.validation

import android.util.Patterns
import ru.itis.neuroteacher.auth.utils.constants.ErrorMessages.EMAIL_EMPTY
import ru.itis.neuroteacher.auth.utils.constants.ErrorMessages.EMAIL_INVALID

object EmailValidator {

    fun validate(email: String): ValidationResult {
        return when {
            email.isBlank() -> ValidationResult.Error(EMAIL_EMPTY)
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> ValidationResult.Error(EMAIL_INVALID)
            else -> ValidationResult.Success
        }
    }

}