package ru.itis.neuroteacher.navigation

import androidx.navigation.NavHostController
import ru.itis.neuroteacher.auth.navigation.AuthRouter
import ru.itis.neuroteacher.auth.navigation.model.AuthRoute
import ru.itis.neuroteacher.home.navigation.HomeRouter
import ru.itis.neuroteacher.home.navigation.model.HomeRoute
import ru.itis.neuroteacher.testcreation.navigation.TestCreationRouter
import ru.itis.neuroteacher.testcreation.navigation.model.TestRoute
import ru.itis.neuroteacher.testcreation.navigation.model.TextInputRoute

class AuthRouterImpl(
    private val navController: NavHostController
) : AuthRouter {
    override fun navigateToLogin() {
        navController.navigate(AuthRoute.Login) {
            popUpTo(AuthRoute.Register) { inclusive = true }
        }
    }

    override fun navigateToRegister() {
        navController.navigate(AuthRoute.Register)
    }

    override fun navigateToMain() {
        navController.navigate(HomeRoute) {
            popUpTo(AuthRoute.Login) { inclusive = true }
        }
    }

    override fun navigateUp() {
        navController.navigateUp()
    }
}

class HomeRouterImpl(
    private val navController: NavHostController
) : HomeRouter {
    override fun navigateToCamera() {
        // TODO: навигация на камеру
    }

    override fun navigateToText() {
        navController.navigate(TextInputRoute)
    }

    override fun navigateToHistory() { /* TODO */ }
    override fun navigateToProfile() { /* TODO */ }
    override fun navigateUp() {
        navController.navigateUp()
    }
}

class TestCreationRouterImpl(
    private val navController: NavHostController
) : TestCreationRouter {
    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigateToTest(testTitle: String, questionsJson: String) {
        navController.navigate(TestRoute(testTitle, questionsJson))
    }

    override fun navigateToResults(resultJson: String) {
        // TODO: навигация на экран результатов
    }
}