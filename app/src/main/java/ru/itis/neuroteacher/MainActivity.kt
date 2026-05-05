package ru.itis.neuroteacher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.neuroteacher.auth.navigation.authNavGraph
import ru.itis.neuroteacher.auth.navigation.model.AuthRoute
import ru.itis.neuroteacher.home.navigation.homeNavGraph
import ru.itis.neuroteacher.navigation.AuthRouterImpl
import ru.itis.neuroteacher.navigation.HomeRouterImpl
import ru.itis.neuroteacher.navigation.TestCreationRouterImpl
import ru.itis.neuroteacher.testcreation.navigation.TestTakingRouterImpl
import ru.itis.neuroteacher.testcreation.navigation.cameraNavGraph
import ru.itis.neuroteacher.testcreation.navigation.model.TestCreationRoute
import ru.itis.neuroteacher.testcreation.navigation.model.TextInputRoute
import ru.itis.neuroteacher.testcreation.navigation.photoPreviewNavGraph
import ru.itis.neuroteacher.testcreation.navigation.retryTestNavGraph
import ru.itis.neuroteacher.testcreation.navigation.testNavGraph
import ru.itis.neuroteacher.testcreation.navigation.testResultNavGraph
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
                    val authRouter = remember(navController) { AuthRouterImpl(navController) }
                    val homeRouter = remember(navController) { HomeRouterImpl(navController) }
                    val testRouter = remember(navController) { TestCreationRouterImpl(navController) }
                    val testTakingRouter = remember(navController) { TestTakingRouterImpl(navController) }
                    NavHost(
                        navController = navController,
                        startDestination = AuthRoute.Login
                    ) {
                        authNavGraph(router = authRouter)
                        homeNavGraph(router = homeRouter)
                        cameraNavGraph(router = testRouter)
                        photoPreviewNavGraph(router = testRouter)
                        navigation<TestCreationRoute>(startDestination = TextInputRoute) {
                            textInputNavGraph(router = testRouter)
                        }
                        testNavGraph(router = testRouter)
                        retryTestNavGraph(router = testRouter)
                        testResultNavGraph(router = testTakingRouter)
                    }
                }
            }
        }
    }
}