package ru.itis.neuroteacher.testcreation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.itis.neuroteacher.testcreation.data.TestCache
import ru.itis.neuroteacher.testcreation.navigation.model.CameraRoute
import ru.itis.neuroteacher.testcreation.presentation.camera.CameraScreen

fun NavGraphBuilder.cameraNavGraph(
    router: TestCreationRouter,
    testCache: TestCache
) {
    composable<CameraRoute> {
        CameraScreen(
            router = router,
            testCache = testCache,
            viewModel = hiltViewModel()
        )
    }
}