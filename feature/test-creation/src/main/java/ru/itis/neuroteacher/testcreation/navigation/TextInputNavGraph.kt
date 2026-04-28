package ru.itis.neuroteacher.testcreation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.itis.neuroteacher.testcreation.navigation.model.TextInputRoute
import ru.itis.neuroteacher.testcreation.presentation.textinput.TextInputScreen

fun NavGraphBuilder.textInputNavGraph(
    router: TestCreationRouter
) {
    composable<TextInputRoute> { backStackEntry ->
        val cacheViewModel = androidx.hilt.navigation.compose.hiltViewModel<
                ru.itis.neuroteacher.testcreation.presentation.TestCacheViewModel
                >(backStackEntry)

        TextInputScreen(
            router = router,
            testCache = cacheViewModel.cache,
            viewModel = hiltViewModel()
        )
    }
}