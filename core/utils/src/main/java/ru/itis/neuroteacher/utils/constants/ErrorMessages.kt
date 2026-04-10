package ru.itis.neuroteacher.utils.constants

object ErrorMessages {
    const val EMAIL_EMPTY = "Email не может быть пустым"
    const val EMAIL_INVALID = "Введите корректный email"

    const val PASSWORD_EMPTY = "Пароль не может быть пустым"
    const val PASSWORD_MIN = "Пароль должен быть минимум"
    const val SYMBOLS = "символов"
    const val PASSWORD_CONFIRM_EMPTY = "Подтвердите пароль"
    const val PASSWORD_MISMATCH = "Пароли не совпадают"

    const val NETWORK_ERROR = "Нет подключения к интернету"

    const val UNKNOWN_ERROR = "Неизвестная ошибка. Попробуйте позже"

    const val AUTHORIZATION_ERROR = "Ошибка авторизации. Попробуйте позже"

    const val SIGN_OUT_ERROR = "Ошибка выхода из аккаунта"

    const val FIREBASE_INVALID_EMAIL = "Неверный формат email"
    const val FIREBASE_EMAIL_ALREADY_IN_USE = "Пользователь с таким email уже существует"
    const val FIREBASE_WEAK_PASSWORD = "Пароль должен быть минимум 6 символов"
    const val FIREBASE_USER_NOT_FOUND = "Пользователь не найден"
    const val FIREBASE_WRONG_PASSWORD = "Неверный пароль"
}

object FirebaseErrorCodes {
    const val INVALID_EMAIL = "ERROR_INVALID_EMAIL"
    const val EMAIL_ALREADY_IN_USE = "ERROR_EMAIL_ALREADY_IN_USE"
    const val WEAK_PASSWORD = "ERROR_WEAK_PASSWORD"
    const val USER_NOT_FOUND = "ERROR_USER_NOT_FOUND"
    const val WRONG_PASSWORD = "ERROR_WRONG_PASSWORD"
}