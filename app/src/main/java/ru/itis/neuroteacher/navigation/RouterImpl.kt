package ru.itis.neuroteacher.navigation

import androidx.navigation.NavHostController
import ru.itis.neuroteacher.auth.navigation.AuthRouter
import ru.itis.neuroteacher.auth.navigation.model.AuthRoute
import ru.itis.neuroteacher.feature.history.navigation.HistoryRoute
import ru.itis.neuroteacher.feature.history.navigation.HistoryRouter
import ru.itis.neuroteacher.feature.profile.navigation.ProfileRoute
import ru.itis.neuroteacher.feature.profile.navigation.ProfileRouter
import ru.itis.neuroteacher.home.navigation.HomeRouter
import ru.itis.neuroteacher.home.navigation.model.HomeRoute
import ru.itis.neuroteacher.testcreation.navigation.TestCreationRouter
import ru.itis.neuroteacher.testcreation.navigation.model.CameraRoute
import ru.itis.neuroteacher.testcreation.navigation.model.PhotoPreviewRoute
import ru.itis.neuroteacher.testcreation.navigation.model.TestResultRoute
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
    override fun navigateToCamera() = navController.navigate(CameraRoute)
    override fun navigateToText() = navController.navigate(TextInputRoute)

    override fun navigateToHistory() {
        navController.navigate(HistoryRoute) {
            launchSingleTop = true
        }
    }
    override fun navigateToProfile() {
        navController.navigate(ProfileRoute) {
            launchSingleTop = true
        }
    }
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

    override fun navigateToTest(testId: String) {
        navController.navigate(TestRoute(testId))
    }

    override fun navigateToResults(testId: Long, resultId: Long) {
        navController.navigate(TestResultRoute(testId = testId, resultId = resultId))
    }
    override fun navigateToPhotoDemo(imageUri: String, recognizedText: String) =
        navController.navigate(PhotoPreviewRoute(imageUri = imageUri, recognizedText = recognizedText))
}

class HistoryRouterImpl(
    private val navController: NavHostController
) : HistoryRouter {

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigateToTestResult(testId: Long, resultId: Long) {
        navController.navigate(TestResultRoute(testId, resultId))
    }
    override fun navigateToHome() {
        navController.navigate(HomeRoute) {
            launchSingleTop = true
            popUpTo(HomeRoute) { inclusive = false }
        }
    }

    override fun navigateToProfile() {
        navController.navigate(ProfileRoute) {
            launchSingleTop = true
            popUpTo(ProfileRoute) { inclusive = false }
        }
    }
}

class ProfileRouterImpl(
    private val navController: NavHostController
) : ProfileRouter {

    override fun navigateToLogin() {
        navController.navigate(AuthRoute.Login) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigateToHome() {
        navController.navigate(HomeRoute) {
            launchSingleTop = true
            popUpTo(HomeRoute) { inclusive = false }
        }
    }

    override fun navigateToHistory() {
        navController.navigate(HistoryRoute) {
            launchSingleTop = true
            popUpTo(HistoryRoute) { inclusive = false }
        }
    }
}
