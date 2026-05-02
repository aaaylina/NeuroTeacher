package ru.itis.neuroteacher.testcreation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.itis.neuroteacher.testcreation.data.TestCache
import ru.itis.neuroteacher.testcreation.navigation.model.RetryTestRoute
import ru.itis.neuroteacher.testcreation.presentation.test.TestScreen

fun NavGraphBuilder.retryTestNavGraph(
    router: TestCreationRouter,
    testCache: TestCache
) {
    composable<RetryTestRoute> { backStackEntry ->
        val testId = backStackEntry.toRoute<RetryTestRoute>().testId
        TestScreen(
            router = router,
            testCache = testCache,
            testId = testId,
            isRetry = true
        )
    }
}