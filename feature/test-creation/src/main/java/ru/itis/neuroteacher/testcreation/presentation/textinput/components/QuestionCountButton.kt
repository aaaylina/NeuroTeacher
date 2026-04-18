package ru.itis.neuroteacher.testcreation.presentation.textinput.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
internal fun QuestionCountButton(
    count: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(dimensionResource(R.dimen.question_button_width))
            .height(dimensionResource(R.dimen.question_button_height))
            .clip(AppTheme.shapes.cardCorner)
            .background(
                brush = if (isSelected) {
                    Brush.horizontalGradient(AppTheme.colors.backgroundGradient)
                } else {
                    Brush.verticalGradient(
                        colors = listOf(
                            AppTheme.colors.backgroundLight,
                            AppTheme.colors.backgroundLight
                        )
                    )
                }
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = count.toString(),
            color = if (isSelected) {
                AppTheme.colors.textPrimary.copy(alpha = 1f)
            } else {
                AppTheme.colors.textSecondary
            },
            style = AppTheme.typography.cardTitle
        )
    }
}