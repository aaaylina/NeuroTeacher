package ru.itis.neuroteacher.home.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.itis.neuroteacher.home.navigation.model.HomeRoute
import ru.itis.neuroteacher.home.presentation.HomeScreen

fun NavGraphBuilder.homeNavGraph(router: HomeRouter) {
    composable<HomeRoute> {
        HomeScreen(router = router)
    }
}