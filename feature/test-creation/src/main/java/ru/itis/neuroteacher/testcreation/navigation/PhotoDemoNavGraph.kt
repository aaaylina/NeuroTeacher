package ru.itis.neuroteacher.testcreation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.itis.neuroteacher.testcreation.navigation.model.PhotoDemoRoute
import ru.itis.neuroteacher.testcreation.presentation.camera.PhotoDemoScreen


fun NavGraphBuilder.photoDemoNavGraph(
    router: TestCreationRouter
) {
    composable<PhotoDemoRoute> {
        PhotoDemoScreen(
            router = router,
            viewModel = hiltViewModel()
        )
    }
}