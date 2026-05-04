package ru.itis.neuroteacher.testcreation.navigation

interface TestCreationRouter {
    fun navigateUp()
    fun navigateToTest(testId: String)
    fun navigateToResults(testId: Long, resultId: Long)
    fun navigateToPhotoDemo(imageUri: String, recognizedText: String)
}