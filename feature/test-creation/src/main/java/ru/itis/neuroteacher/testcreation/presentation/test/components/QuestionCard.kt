package ru.itis.neuroteacher.testcreation.presentation.test.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.testcreation.domain.model.Question
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
internal fun QuestionCard(
    question: Question,
    selectedOptionIndex: Int?,
    onOptionSelected: (Int) -> Unit,
    isEnabled: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.spacing_lg)),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.cardBackground
        ),
        shape = AppTheme.shapes.cardCorner,
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.spacing_xxxs))
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.spacing_xl))
        ) {
            Text(
                text = question.text,
                style = AppTheme.typography.cardTitle.copy(
                    fontSize = dimensionResource(R.dimen.font_size_question_text).value.sp
                ),
                color = AppTheme.colors.textPrimary,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.spacing_xl))
            )

            question.options.forEachIndexed { index, option ->
                val isSelected = selectedOptionIndex == index
                val isCorrect = index == question.correctIndex
                val showResult = isSelected && !isEnabled

                val backgroundColor = when {
                    showResult && isCorrect -> AppTheme.colors.successBackground
                    showResult && !isCorrect -> AppTheme.colors.errorBackground
                    isSelected -> AppTheme.colors.backgroundLight
                    else -> AppTheme.colors.cardBackground
                }

                val borderColor = when {
                    showResult && isCorrect -> AppTheme.colors.successBorder
                    showResult && !isCorrect -> AppTheme.colors.errorBorder
                    isSelected -> AppTheme.colors.primary
                    else -> AppTheme.colors.borderDefault
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.spacing_xxxs))
                        .clip(AppTheme.shapes.inputCorner)
                        .background(backgroundColor)
                        .border(
                            width = dimensionResource(R.dimen.border_thickness),
                            color = borderColor,
                            shape = AppTheme.shapes.inputCorner
                        )
                        .clickable(enabled = isEnabled) { onOptionSelected(index) }
                        .padding(dimensionResource(R.dimen.spacing_lg))
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = option,
                            style = AppTheme.typography.cardSubtitle.copy(
                                fontSize = dimensionResource(R.dimen.font_size_option_text).value.sp
                            ),
                            color = if (showResult && !isCorrect)
                                AppTheme.colors.textSecondary
                            else
                                AppTheme.colors.textPrimary,
                            modifier = Modifier.weight(1f)
                        )

                        if (showResult && isCorrect) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = stringResource(R.string.cd_correct),
                                tint = AppTheme.colors.successBorder,
                                modifier = Modifier.size(dimensionResource(R.dimen.icon_size_medium))
                            )
                        }
                    }
                }
            }
        }
    }
}