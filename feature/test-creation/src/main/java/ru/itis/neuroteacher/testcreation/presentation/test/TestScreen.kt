package ru.itis.neuroteacher.testcreation.presentation.test

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.serialization.json.Json
import ru.itis.neuroteacher.testcreation.presentation.test.components.*
import ru.itis.neuroteacher.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(
    testTitle: String,
    questionsJson: String,
    onNavigateBack: () -> Unit,
    onTestCompleted: (String) -> Unit,
    viewModel: TestViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val colors = AppTheme.colors

    Scaffold(
        topBar = {
            TestTopBar(
                testTitle = testTitle,
                onNavigateBack = onNavigateBack
            )
        },
        containerColor = Color(0xFFF5F5F7)
    ) { padding ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = colors.primary
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
                    color = Color.Red
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

            Spacer(modifier = Modifier.height(20.dp))

            QuestionCard(
                question = currentQuestion,
                selectedOptionIndex = uiState.selectedOptionIndex,
                onOptionSelected = { viewModel.selectOption(it) },
                isEnabled = uiState.selectedOptionIndex == null
            )

            if (uiState.selectedOptionIndex != null) {
                ExplanationCard(explanation = currentQuestion.explanation)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (uiState.currentQuestionIndex > 0) {
                    OutlinedButton(
                        onClick = { /* TODO: предыдущий вопрос */ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        shape = MaterialTheme.shapes.medium,
                        enabled = false
                    ) {
                        Text("Назад")
                    }
                }

                Button(
                    onClick = {
                        if (uiState.currentQuestionIndex < uiState.questions.size - 1) {
                            viewModel.nextQuestion()
                        } else {
                            val result = viewModel.finishTest()
                            val resultJson = Json.encodeToString(result)
                            onTestCompleted(resultJson)
                        }
                    },
                    modifier = Modifier
                        .weight(if (uiState.currentQuestionIndex > 0) 1.5f else 1f)
                        .height(50.dp),
                    enabled = uiState.selectedOptionIndex != null,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (uiState.selectedOptionIndex != null)
                            colors.primary
                        else
                            Color.LightGray
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = if (uiState.currentQuestionIndex < uiState.questions.size - 1)
                            "Далее"
                        else
                            "Завершить",
                        style = AppTheme.typography.button
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}