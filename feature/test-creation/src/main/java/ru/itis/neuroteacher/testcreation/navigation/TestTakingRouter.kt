package ru.itis.neuroteacher.testcreation.navigation

interface TestTakingRouter {
    fun navigateUp()
    fun navigateToTestResult(testId: Long, resultId: Long)
    fun navigateToHome()
    fun navigateToRetryTest(testId: Long)
}