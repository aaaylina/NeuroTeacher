package ru.itis.neuroteacher.home.navigation

interface HomeRouter {
    fun navigateToCamera()
    fun navigateToText()
    fun navigateToHistory()
    fun navigateToProfile()
    fun navigateUp()
}