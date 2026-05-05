package ru.itis.neuroteacher.testcreation.presentation.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.testcreation.navigation.TestCreationRouter
import ru.itis.neuroteacher.testcreation.presentation.test.components.ExplanationCard
import ru.itis.neuroteacher.testcreation.presentation.test.components.QuestionCard
import ru.itis.neuroteacher.testcreation.presentation.test.components.TestProgressBar
import ru.itis.neuroteacher.testcreation.presentation.test.components.TestTopBar
import ru.itis.neuroteacher.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TestScreen(
    router: TestCreationRouter,
    testId: String,
    viewModel: TestViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is TestEvent.NavigateToResults -> {
                    router.navigateToResults(
                        testId = event.testId,
                        resultId = event.resultId
                    )
                }
            }
        }
    }

    LaunchedEffect(testId) {
        if (uiState.questions.isEmpty() && !uiState.isLoading) {
            val idAsLong = testId.toLongOrNull()

            if (idAsLong != null) {
                viewModel.loadTestFromDatabase(idAsLong)
            } else {
                viewModel.loadTestFromCache(testId)
            }
        }
    }

    Scaffold(
        topBar = {
            TestTopBar(
                testTitle = uiState.testTitle,
                onNavigateBack = { router.navigateUp() }
            )
        },
        containerColor = AppTheme.colors.backgroundLight
    ) { padding ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = AppTheme.colors.primary
                )
            }
            return@Scaffold
        }

        uiState.error?.let { error ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = error,
                    color = AppTheme.colors.error
                )
            }
            return@Scaffold
        }

        val currentQuestion = viewModel.getCurrentQuestion() ?: return@Scaffold

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            TestProgressBar(
                currentQuestion = uiState.currentQuestionIndex + 1,
                totalQuestions = uiState.questions.size
            )

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXl))

            QuestionCard(
                question = currentQuestion,
                selectedOptionIndex = uiState.selectedOptionIndex,
                onOptionSelected = { viewModel.selectOption(it) },
                isEnabled = uiState.selectedOptionIndex == null
            )

            if (uiState.selectedOptionIndex != null) {
                ExplanationCard(explanation = currentQuestion.explanation)
            }

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXl))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppTheme.dimensions.spacingLg),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.spacingMd)
            ) {
                if (uiState.currentQuestionIndex > 0) {
                    OutlinedButton(
                        onClick = {
                            viewModel.previousQuestion()
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = AppTheme.colors.cardBackground,
                            contentColor = AppTheme.colors.textPrimary
                        ),
                        shape = AppTheme.shapes.buttonCorner,
                        enabled = true
                    ) {
                        Text(stringResource(R.string.test_button_previous))
                    }
                }

                Button(
                    onClick = {
                        if (uiState.currentQuestionIndex < uiState.questions.lastIndex) {
                            viewModel.nextQuestion()
                        } else {
                            viewModel.finishTest()
                        }
                    },
                    modifier = Modifier
                        .weight(if (uiState.currentQuestionIndex > 0) 1.5f else 1f)
                        .height(AppTheme.dimensions.buttonHeight),
                    enabled = uiState.selectedOptionIndex != null,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (uiState.selectedOptionIndex != null)
                            AppTheme.colors.primary
                        else
                            AppTheme.colors.disabled,
                        disabledContainerColor = AppTheme.colors.disabled
                    ),
                    shape = AppTheme.shapes.buttonCorner
                ) {
                    Text(
                        text = if (uiState.currentQuestionIndex < uiState.questions.lastIndex)
                            stringResource(R.string.test_button_next)
                        else
                            stringResource(R.string.test_button_finish),
                        style = AppTheme.typography.button
                    )
                }
            }

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXl))
        }
    }
}