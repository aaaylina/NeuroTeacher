package ru.itis.neuroteacher.testcreation.navigation

interface TestCreationRouter {
    fun navigateUp()
    fun navigateToTest(testId: String)
    fun navigateToResults(resultId: String)
}