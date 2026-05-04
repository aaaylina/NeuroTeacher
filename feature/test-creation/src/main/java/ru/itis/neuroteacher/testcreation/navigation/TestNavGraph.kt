package ru.itis.neuroteacher.testcreation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.itis.neuroteacher.testcreation.data.TestCache
import ru.itis.neuroteacher.testcreation.navigation.model.TestRoute
import ru.itis.neuroteacher.testcreation.presentation.test.TestScreen

fun NavGraphBuilder.testNavGraph(
    router: TestCreationRouter,
    testCache: TestCache
) {
    composable<TestRoute> {
        TestScreen(
            router = router,
            testCache = testCache,
            viewModel = hiltViewModel()
        )
    }

}