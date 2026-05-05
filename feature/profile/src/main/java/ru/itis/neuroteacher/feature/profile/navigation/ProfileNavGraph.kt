package ru.itis.neuroteacher.feature.profile.navigation
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.itis.neuroteacher.feature.profile.presentation.ProfileScreen

fun NavGraphBuilder.profileNavGraph(router: ProfileRouter) {
    composable<ProfileRoute> {
        ProfileScreen(
            router = router,
            viewModel = hiltViewModel()
        )
    }
}