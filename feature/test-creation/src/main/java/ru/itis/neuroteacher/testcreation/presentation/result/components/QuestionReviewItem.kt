package ru.itis.neuroteacher.testcreation.presentation.result.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.testcreation.domain.model.TestResult
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
internal fun QuestionReviewItem(
    questionResult: TestResult.QuestionResult
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AppTheme.dimensions.spacingXs),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.backgroundLight),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.spacingMd)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.spacingSm)
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (questionResult.isCorrect) R.drawable.ic_correct else R.drawable.ic_incorrect),
                        contentDescription = if (questionResult.isCorrect)
                            stringResource(R.string.test_result_correct)
                        else
                            stringResource(R.string.test_result_incorrect),
                        tint = if (questionResult.isCorrect) AppTheme.colors.successBorder else AppTheme.colors.errorBorder,
                        modifier = Modifier.size(24.dp)
                    )

                    Text(
                        text = stringResource(R.string.test_result_question_number, questionResult.questionNumber),
                        style = AppTheme.typography.cardTitle,
                        color = AppTheme.colors.textPrimary
                    )
                }

                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = if (isExpanded)
                            stringResource(R.string.test_result_collapse)
                        else
                            stringResource(R.string.test_result_expand),
                        tint = AppTheme.colors.textSecondary,
                        modifier = Modifier.rotate(if (isExpanded) 180f else 0f)
                    )
                }
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingMd))

                Text(
                    text = questionResult.questionText,
                    style = AppTheme.typography.cardSubtitle,
                    color = AppTheme.colors.textPrimary,
                    modifier = Modifier.padding(bottom = AppTheme.dimensions.spacingSm)
                )

                questionResult.explanation?.let { explanation ->
                    Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingSm))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.explanationBackground),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(modifier = Modifier.padding(AppTheme.dimensions.spacingMd)) {
                            Text(
                                text = stringResource(R.string.test_result_explanation_label),
                                style = AppTheme.typography.label.copy(
                                    fontSize = AppTheme.dimensions.fontSizeExplanationTitle
                                ),
                                color = AppTheme.colors.primary
                            )
                            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXs))
                            Text(
                                text = explanation,
                                style = AppTheme.typography.placeholder.copy(
                                    fontSize = AppTheme.dimensions.fontSizeExplanationText
                                ),
                                color = AppTheme.colors.explanationText
                            )
                        }
                    }
                }
            }
        }
    }
}