package ru.itis.neuroteacher.feature.history.navigation

interface HistoryRouter {
    fun navigateUp()
    fun navigateToTestResult(testId: Long, resultId: Long)
    fun navigateToHome()
    fun navigateToProfile()
}