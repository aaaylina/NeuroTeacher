package ru.itis.neuroteacher.domain.repository

interface AuthErrorHandler {
    fun handle(exception: Exception): String
}