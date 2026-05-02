package ru.itis.neuroteacher.testcreation.presentation.result


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.testcreation.navigation.TestTakingRouter
import ru.itis.neuroteacher.testcreation.presentation.result.components.CircularProgressChart
import ru.itis.neuroteacher.testcreation.presentation.result.components.QuestionReviewItem
import ru.itis.neuroteacher.testcreation.presentation.result.components.ResultButtons
import ru.itis.neuroteacher.testcreation.presentation.result.components.ResultHeader
import ru.itis.neuroteacher.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TestResultScreen(
    router: TestTakingRouter,
    viewModel: TestResultViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val testId = viewModel.testId

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = AppTheme.colors.backgroundGradientMain
                    )
                )
                .padding(padding)
        ) {

            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = AppTheme.colors.primary)
                }
                return@Scaffold
            }

            uiState.error?.let { error ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = error,
                        color = AppTheme.colors.error,
                        style = AppTheme.typography.subtitle
                    )
                }
                return@Scaffold
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.dimensions.spacingLg),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ResultHeader(
                        percentage = uiState.scorePercentage,
                        correctCount = uiState.correctAnswers,
                        totalCount = uiState.totalQuestions
                    )

                    Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingLg))

                    CircularProgressChart(
                        percentage = uiState.scorePercentage,
                        modifier = Modifier.padding(AppTheme.dimensions.spacingLg)
                    )

                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(
                        topStart = 48.dp,
                        topEnd = 48.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = AppTheme.colors.cardBackground
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = AppTheme.dimensions.spacingLg,
                                vertical = AppTheme.dimensions.spacingXl
                            )
                    ) {
                        Text(
                            text = stringResource(R.string.test_result_review_title),
                            style = AppTheme.typography.sectionTitle.copy(
                                fontSize = AppTheme.dimensions.fontSizeTopBarTitle,
                                color = AppTheme.colors.textOnWhite
                            )
                        )

                        Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingLg))

                        uiState.questions.forEachIndexed { index, question ->
                            QuestionReviewItem(
                                questionResult = question,
                                questionNumber = index + 1
                            )
                            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXs))
                        }

                        ResultButtons(
                            onRetryClick = {  router.navigateToRetryTest(testId = testId) },
                            onHomeClick = { router.navigateToHome() }
                        )

                        Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingLg))
                    }
                }
            }
        }
    }
}