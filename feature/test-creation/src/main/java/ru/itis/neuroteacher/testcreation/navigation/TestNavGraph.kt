package ru.itis.neuroteacher.testcreation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.itis.neuroteacher.testcreation.navigation.model.TestRoute
import ru.itis.neuroteacher.testcreation.presentation.test.TestScreen

fun NavGraphBuilder.testNavGraph(
    router: TestCreationRouter,
) {
    composable<TestRoute> { backStackEntry ->
        val route = backStackEntry.toRoute<TestRoute>()

        val cacheViewModel = androidx.hilt.navigation.compose.hiltViewModel<
                ru.itis.neuroteacher.testcreation.presentation.TestCacheViewModel
                >(backStackEntry)

        TestScreen(
            router = router,
            testCache = cacheViewModel.cache,
            viewModel = androidx.hilt.navigation.compose.hiltViewModel()
        )
    }
}