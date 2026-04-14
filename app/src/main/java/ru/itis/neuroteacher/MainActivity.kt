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
                            onNavigateToText = { /* TODO */ },
                            onNavigateToHistory = { /* TODO */ },
                            onNavigateToProfile = { /* TODO */ }
                        )
                    }
                }
            }
        }
    }
}