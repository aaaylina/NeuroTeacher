package ru.itis.neuroteacher.testcreation.presentation.result.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
internal fun ResultHeader(
    percentage: Float,
    correctCount: Int,
    totalCount: Int
) {
    val message = if (percentage >= 50) {
        stringResource(R.string.test_result_good_message)
    } else {
        stringResource(R.string.test_result_can_be_better_message)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = AppTheme.dimensions.spacingXl),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.test_result_percentage_format, percentage.toInt()),
            style = AppTheme.typography.title.copy(
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                color = if (percentage >= 50) AppTheme.colors.chartSuccessBorder else AppTheme.colors.chartErrorBorder
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingSm))

        Text(
            text = message,
            style = AppTheme.typography.subtitle.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            ),
            color = AppTheme.colors.white,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXs))

        Text(
            text = stringResource(R.string.test_result_correct_format, correctCount, totalCount),
            style = AppTheme.typography.placeholder.copy(
                fontSize = AppTheme.dimensions.fontSizeHelper
            ),
            color = AppTheme.colors.white.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )
    }
}