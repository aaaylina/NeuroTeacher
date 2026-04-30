package ru.itis.neuroteacher.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.itis.neuroteacher.auth.navigation.model.AuthRoute
import ru.itis.neuroteacher.auth.presentation.login.LoginScreen
import ru.itis.neuroteacher.auth.presentation.register.RegisterScreen

fun NavGraphBuilder.authNavGraph(router: AuthRouter) {
    composable<AuthRoute.Login> {
        LoginScreen(router = router)
    }
    composable<AuthRoute.Register> {
        RegisterScreen(router = router)
    }
}