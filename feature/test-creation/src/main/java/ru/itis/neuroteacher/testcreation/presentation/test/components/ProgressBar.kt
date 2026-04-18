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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
internal fun TestProgressBar(
    currentQuestion: Int,
    totalQuestions: Int
) {
    val progress = currentQuestion.toFloat() / totalQuestions.toFloat()

    Text(
        text = stringResource(R.string.test_progress_label, currentQuestion, totalQuestions),
        style = AppTheme.typography.subtitle.copy(
            fontSize = dimensionResource(R.dimen.font_size_helper).value.sp
        ),
        color = AppTheme.colors.textSecondary,
        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.spacing_lg))
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.spacing_lg))
            .height(dimensionResource(R.dimen.progress_height))
            .background(AppTheme.colors.progressBarTrack)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(dimensionResource(R.dimen.progress_height))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = AppTheme.colors.backgroundGradient
                    )
                )
        )
    }
}