package ru.itis.neuroteacher.testcreation.presentation.test.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import ru.itis.neuroteacher.ui.theme.AppTheme
import ru.itis.neuroteacher.testcreation.R

@Composable
internal fun TestProgressBar(
    currentQuestion: Int,
    totalQuestions: Int
) {
    val progress = currentQuestion.toFloat() / totalQuestions.toFloat()

    Text(
        text = stringResource(R.string.test_progress_label, currentQuestion, totalQuestions),
        style = AppTheme.typography.subtitle.copy(
            fontSize = AppTheme.dimensions.fontSizeHelper
        ),
        color = AppTheme.colors.textSecondary,
        modifier = Modifier.padding(horizontal = AppTheme.dimensions.spacingLg)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.dimensions.spacingLg)
            .height(AppTheme.dimensions.progressHeight)
            .background(AppTheme.colors.progressBarTrack)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(AppTheme.dimensions.progressHeight)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = AppTheme.colors.backgroundGradient
                    )
                )
        )
    }
}