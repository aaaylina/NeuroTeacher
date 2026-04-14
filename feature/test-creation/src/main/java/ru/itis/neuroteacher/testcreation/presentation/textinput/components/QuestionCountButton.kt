package ru.itis.neuroteacher.testcreation.presentation.textinput.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun QuestionCountButton(
    count: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val colors = AppTheme.colors

    Box(
        modifier = Modifier
            .width(64.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = when {
                    isSelected -> Brush.horizontalGradient(
                        colors = listOf(
                            colors.primary,
                            colors.primaryVariant
                        )
                    )
                    else -> Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF0F2F5),
                            Color(0xFFF0F2F5)
                        )
                    )
                }
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = count.toString(),
            color = if (isSelected) Color.White else Color.Black,
            fontSize = 16.sp,
            style = if (isSelected) AppTheme.typography.button else AppTheme.typography.cardTitle
        )
    }
}