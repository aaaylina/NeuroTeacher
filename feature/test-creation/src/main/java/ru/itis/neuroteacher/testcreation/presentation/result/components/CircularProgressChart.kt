package ru.itis.neuroteacher.testcreation.presentation.result.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
internal fun CircularProgressChart(
    percentage: Float,
    modifier: Modifier = Modifier,
    size: Dp = 160.dp,
    strokeWidth: Dp = 16.dp
) {
    val correctColor = AppTheme.colors.chartSuccessBorder
    val incorrectColor = AppTheme.colors.chartErrorBorder
    val trackColor = Color.White.copy(alpha = 0.3f)

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(size)) {
            val diameter = size.toPx() - strokeWidth.toPx()

            drawCircle(
                color = trackColor,
                radius = diameter / 2,
                center = Offset(size.toPx() / 2, size.toPx() / 2),
                style = Stroke(width = strokeWidth.toPx())
            )

            if (percentage > 0) {
                drawArc(
                    brush = Brush.verticalGradient(
                        colors = listOf(correctColor, correctColor.copy(alpha = 0.8f))
                    ),
                    startAngle = -90f,
                    sweepAngle = (percentage / 100) * 360,
                    useCenter = false,
                    size = Size(diameter, diameter),
                    topLeft = Offset((size.toPx() - diameter) / 2, (size.toPx() - diameter) / 2),
                    style = Stroke(width = strokeWidth.toPx())
                )
            }

            if (percentage < 100) {
                drawArc(
                    brush = Brush.verticalGradient(
                        colors = listOf(incorrectColor, incorrectColor.copy(alpha = 0.8f))
                    ),
                    startAngle = -90f + (percentage / 100) * 360,
                    sweepAngle = (100 - percentage) / 100 * 360,
                    useCenter = false,
                    size = Size(diameter, diameter),
                    topLeft = Offset((size.toPx() - diameter) / 2, (size.toPx() - diameter) / 2),
                    style = Stroke(width = strokeWidth.toPx())
                )
            }
        }

    }
}
