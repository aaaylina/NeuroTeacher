package ru.itis.neuroteacher.testcreation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.itis.neuroteacher.testcreation.navigation.model.TestResultRoute
import ru.itis.neuroteacher.testcreation.presentation.result.TestResultScreen

fun NavGraphBuilder.testResultNavGraph(router: TestTakingRouter) {
    composable<TestResultRoute> {
        TestResultScreen(
            router = router,
            viewModel = hiltViewModel()
        )
    }
}