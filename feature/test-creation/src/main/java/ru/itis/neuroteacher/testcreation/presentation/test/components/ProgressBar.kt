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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun TestProgressBar(
    currentQuestion: Int,
    totalQuestions: Int
) {
    val progress = currentQuestion.toFloat() / totalQuestions.toFloat()
    val colors = AppTheme.colors

    Text(
        text = "Вопрос $currentQuestion из $totalQuestions",
        style = AppTheme.typography.subtitle.copy(fontSize = 14.sp),
        color = Color.Gray,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(4.dp)
            .background(Color(0xFFE5E7EB))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(4.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(colors.primary, colors.primaryVariant)
                    )
                )
        )
    }
}