package ru.itis.neuroteacher.auth.domain.repository

interface AuthErrorHandler {
    fun handle(exception: Throwable): String
}