package ru.itis.neuroteacher.testcreation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.itis.neuroteacher.testcreation.navigation.model.PhotoPreviewRoute
import ru.itis.neuroteacher.testcreation.presentation.camera.PhotoPreviewScreen


fun NavGraphBuilder.photoPreviewNavGraph(
    router: TestCreationRouter
) {
    composable<PhotoPreviewRoute> {
        PhotoPreviewScreen(
            router = router,
            viewModel = hiltViewModel()
        )
    }
}