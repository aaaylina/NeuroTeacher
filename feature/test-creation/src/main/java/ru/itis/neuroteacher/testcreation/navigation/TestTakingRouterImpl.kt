package ru.itis.neuroteacher.testcreation.navigation

import androidx.navigation.NavHostController
import ru.itis.neuroteacher.home.navigation.model.HomeRoute
import ru.itis.neuroteacher.testcreation.navigation.model.RetryTestRoute
import ru.itis.neuroteacher.testcreation.navigation.model.TestResultRoute

class TestTakingRouterImpl(
    private val navController: NavHostController
) : TestTakingRouter {

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigateToTestResult(testId: Long, resultId: Long) {
        navController.navigate(TestResultRoute(testId, resultId))
    }

    override fun navigateToHome() {
        navController.navigate(HomeRoute) {
            popUpTo(HomeRoute) { inclusive = true }
        }
    }

    override fun navigateToRetryTest(testId: Long) {
        navController.navigate(RetryTestRoute(testId)) {
            launchSingleTop = true
            popUpTo(RetryTestRoute(testId)) { inclusive = true }
        }
    }
}
