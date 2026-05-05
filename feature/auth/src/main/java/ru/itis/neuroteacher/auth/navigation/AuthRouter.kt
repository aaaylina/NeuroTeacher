package ru.itis.neuroteacher.auth.navigation

interface AuthRouter {
    fun navigateToLogin()
    fun navigateToRegister()
    fun navigateToMain()
    fun navigateUp()
}