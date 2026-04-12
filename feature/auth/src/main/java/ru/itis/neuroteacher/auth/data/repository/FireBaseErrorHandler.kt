package ru.itis.neuroteacher.auth.data.repository

import com.google.firebase.auth.FirebaseAuthException
import ru.itis.neuroteacher.auth.domain.repository.AuthErrorHandler
import ru.itis.neuroteacher.auth.utils.constants.ErrorMessages
import ru.itis.neuroteacher.auth.utils.constants.FirebaseErrorCodes
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FireBaseErrorHandler @Inject constructor() : AuthErrorHandler {

    override fun handle(exception: Throwable): String {
        return when (exception) {
            is UnknownHostException -> ErrorMessages.NETWORK_ERROR
            is FirebaseAuthException -> when (exception.errorCode) {
                FirebaseErrorCodes.INVALID_EMAIL -> ErrorMessages.FIREBASE_INVALID_EMAIL
                FirebaseErrorCodes.EMAIL_ALREADY_IN_USE -> ErrorMessages.FIREBASE_EMAIL_ALREADY_IN_USE
                FirebaseErrorCodes.WEAK_PASSWORD -> ErrorMessages.FIREBASE_WEAK_PASSWORD
                FirebaseErrorCodes.USER_NOT_FOUND -> ErrorMessages.FIREBASE_USER_NOT_FOUND
                FirebaseErrorCodes.WRONG_PASSWORD -> ErrorMessages.FIREBASE_WRONG_PASSWORD
                else -> ErrorMessages.AUTHORIZATION_ERROR
            }
            else -> ErrorMessages.UNKNOWN_ERROR
        }
    }
}