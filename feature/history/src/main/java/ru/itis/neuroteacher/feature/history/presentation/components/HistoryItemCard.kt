package ru.itis.neuroteacher.feature.history.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.itis.neuroteacher.feature.history.presentation.HistoryItem
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun HistoryItemCard(
    item: HistoryItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = AppTheme.dimensions.statsCardShadowElevation,
                ambientColor = AppTheme.colors.shadowColor,
                spotColor = AppTheme.colors.shadowColor
            )
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(AppTheme.dimensions.buttonCornerRadius),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.cardBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.spacingMd)
        ) {
            Text(
                text = item.testTitle,
                style = AppTheme.typography.cardTitle.copy(
                    fontSize = AppTheme.dimensions.fontSizeQuestionText,
                    fontWeight = FontWeight.Medium
                ),
                color = AppTheme.colors.textPrimary,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingSm))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.date,
                    style = AppTheme.typography.subtitle.copy(
                        fontSize = AppTheme.dimensions.fontSizeButton,
                        fontWeight = FontWeight.Medium
                    ),
                    color = AppTheme.colors.textSecondary
                )
                Text(
                    text = "${item.totalQuestions} вопросов",
                    style = AppTheme.typography.subtitle.copy(
                        fontSize = AppTheme.dimensions.fontSizeButton,
                        fontWeight = FontWeight.Medium
                    ),
                    color = AppTheme.colors.textSecondary
                )
            }

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingSm))

            Row(
                modifier = Modifier.align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(
                            color = getScoreColor(item.scorePercentage),
                            shape = RoundedCornerShape(50)
                        )
                )
                Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingXs))
                Text(
                    text = "${item.scorePercentage}%",
                    style = AppTheme.typography.subtitle.copy(
                        fontSize = AppTheme.dimensions.fontSizeButton,
                        fontWeight = FontWeight.Medium
                    ),
                    color = AppTheme.colors.textSecondary
                )
            }
        }
    }
}

@Composable
private fun getScoreColor(percentage: Int): Color {
    return if (percentage >= 70) {
        AppTheme.colors.successBorder
    } else {
        AppTheme.colors.errorBorder
    }
}