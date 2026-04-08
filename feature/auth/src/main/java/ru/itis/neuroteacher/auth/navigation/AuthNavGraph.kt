package ru.itis.neuroteacher.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.itis.neuroteacher.auth.presentation.login.LoginScreen
import ru.itis.neuroteacher.auth.presentation.register.RegisterScreen

sealed interface AuthRoute {
    @Serializable
    data object Login : AuthRoute

    @Serializable
    data object Register : AuthRoute
}

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    onNavigateToMain: () -> Unit
) {
    composable<AuthRoute.Login> {
        LoginScreen(
            onNavigateToRegister = {
                navController.navigate(AuthRoute.Register)
            },
            onLoginSuccess = onNavigateToMain
        )
    }

    composable<AuthRoute.Register> {
        RegisterScreen(
            onNavigateToLogin = {
                navController.popBackStack()
            },
            onRegisterSuccess = onNavigateToMain
        )
    }
}