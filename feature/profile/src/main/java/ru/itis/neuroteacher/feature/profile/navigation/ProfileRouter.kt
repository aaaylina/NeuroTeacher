package ru.itis.neuroteacher.feature.profile.navigation

interface ProfileRouter {
    fun navigateToLogin()
    fun navigateUp()
    fun navigateToHome()
    fun navigateToHistory()
}