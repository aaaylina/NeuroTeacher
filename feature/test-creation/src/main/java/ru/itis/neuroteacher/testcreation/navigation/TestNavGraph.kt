package ru.itis.neuroteacher.testcreation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.itis.neuroteacher.testcreation.navigation.model.TestRoute
import ru.itis.neuroteacher.testcreation.presentation.test.TestScreen

fun NavGraphBuilder.testNavGraph(
    router: TestCreationRouter,
) {
    composable<TestRoute> { backStackEntry ->
        val testRoute = backStackEntry.toRoute<TestRoute>()
        TestScreen(
            router = router,
            testId = testRoute.testId,
            viewModel = hiltViewModel()
        )
    }
}