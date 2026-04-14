package ru.itis.neuroteacher.testcreation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.itis.neuroteacher.testcreation.navigation.model.TextInputRoute
import ru.itis.neuroteacher.testcreation.presentation.textinput.TextInputScreen

fun NavGraphBuilder.textInputNavGraph(
    onNavigateBack: () -> Unit,
    onGenerateTest: (String, Int) -> Unit
) {
    composable<TextInputRoute> {
        TextInputScreen(
            onNavigateBack = onNavigateBack,
            onGenerateTest = onGenerateTest
        )
    }
}