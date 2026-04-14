package ru.itis.neuroteacher.testcreation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.itis.neuroteacher.testcreation.navigation.model.TestRoute
import ru.itis.neuroteacher.testcreation.presentation.test.TestScreen

fun NavGraphBuilder.testNavGraph(
    onNavigateBack: () -> Unit,
    onTestCompleted: (String) -> Unit
) {
    composable<TestRoute> { backStackEntry ->
        val route = backStackEntry.toRoute<TestRoute>()
        TestScreen(
            testTitle = route.testTitle,
            questionsJson = route.questionsJson,
            onNavigateBack = onNavigateBack,
            onTestCompleted = onTestCompleted
        )
    }
}