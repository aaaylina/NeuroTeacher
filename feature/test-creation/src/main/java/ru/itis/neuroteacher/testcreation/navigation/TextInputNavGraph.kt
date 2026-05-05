package ru.itis.neuroteacher.testcreation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.itis.neuroteacher.testcreation.navigation.model.TextInputRoute
import ru.itis.neuroteacher.testcreation.presentation.textinput.TextInputScreen

fun NavGraphBuilder.textInputNavGraph(
    router: TestCreationRouter,
) {
    composable<TextInputRoute> {
        TextInputScreen(
            router = router,
            viewModel = hiltViewModel()
        )
    }
}