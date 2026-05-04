package ru.itis.neuroteacher.testcreation.navigation

import androidx.navigation.NavHostController
import ru.itis.neuroteacher.testcreation.navigation.model.TestResultRoute
import ru.itis.neuroteacher.testcreation.navigation.model.TestRoute

class TestCreationRouterImpl(
    private val navController: NavHostController
) : TestCreationRouter {

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigateToTest(testId: String) {
        navController.navigate(TestRoute(testId))
    }

    override fun navigateToResults(testId: Long, resultId: Long) {
        navController.navigate(
            TestResultRoute(
                testId = testId,
                resultId = resultId
            )
        )
    }
}