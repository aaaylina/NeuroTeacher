package ru.itis.neuroteacher.feature.history.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.itis.neuroteacher.feature.history.presentation.HistoryScreen

fun NavGraphBuilder.historyNavGraph(router: HistoryRouter) {
    composable<HistoryRoute> {
        HistoryScreen(
            router = router,
            viewModel = hiltViewModel()
        )
    }
}