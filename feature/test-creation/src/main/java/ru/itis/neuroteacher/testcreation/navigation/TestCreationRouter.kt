package ru.itis.neuroteacher.testcreation.navigation

interface TestCreationRouter {
    fun navigateUp()
    fun navigateToTest(testTitle: String, questionsJson: String)
    fun navigateToResults(resultJson: String)
}