package ru.itis.neuroteacher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.neuroteacher.auth.navigation.authNavGraph
import ru.itis.neuroteacher.auth.navigation.model.AuthRoute
import ru.itis.neuroteacher.home.navigation.homeNavGraph
import ru.itis.neuroteacher.home.navigation.model.HomeRoute
import ru.itis.neuroteacher.testcreation.navigation.model.TestRoute
import ru.itis.neuroteacher.testcreation.navigation.model.TextInputRoute
import ru.itis.neuroteacher.testcreation.navigation.testNavGraph
import ru.itis.neuroteacher.testcreation.navigation.textInputNavGraph
import ru.itis.neuroteacher.ui.theme.AppTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = AuthRoute.Login
                    ) {
                        authNavGraph(
                            navController = navController,
                            onNavigateToMain = {
                                navController.navigate(HomeRoute) {
                                    popUpTo(AuthRoute.Login) { inclusive = true }
                                }
                            }
                        )

                        homeNavGraph(
                            onNavigateToCamera = { /* TODO */ },
                            onNavigateToText = {
                                navController.navigate(TextInputRoute)
                            },
                            onNavigateToHistory = { /* TODO */ },
                            onNavigateToProfile = { /* TODO */ }
                        )

                        textInputNavGraph(
                            onNavigateBack = { navController.popBackStack() },
                            onNavigateToTest = { testTitle, questionsJson ->
                                navController.navigate(TestRoute(testTitle, questionsJson))
                            }
                        )

                        testNavGraph(
                            onNavigateBack = { navController.popBackStack() },
                            onTestCompleted = { resultJson ->
                                // TODO: навигация на экран результатов
                            }
                        )
                    }
                }
            }
        }
    }
}