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
                shape = RoundedCornerShape(AppTheme.dimensions.historyItemCornerRadius),
                clip = false,
                ambientColor = AppTheme.colors.shadowColor,
                spotColor = AppTheme.colors.shadowColor
            )
            .shadow(
                elevation = AppTheme.dimensions.statsCardShadowElevation,
                shape = RoundedCornerShape(AppTheme.dimensions.historyItemCornerRadius),
                clip = false,
                ambientColor = AppTheme.colors.shadowColor,
                spotColor = AppTheme.colors.shadowColor
            )
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(AppTheme.dimensions.historyItemCornerRadius),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.cardBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.dimensions.historyItemCardHeight)
                .padding(AppTheme.dimensions.spacingMd)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text(
                    text = item.testTitle,
                    fontSize = AppTheme.dimensions.fontSizeQuestionText,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.textPrimary,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXs))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.spacingSm),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.date,
                        fontSize = AppTheme.dimensions.fontSizeButton,
                        fontWeight = FontWeight.Medium,
                        color = AppTheme.colors.textSecondary
                    )
                    Text(
                        text = "${item.totalQuestions} вопросов",
                        fontSize = AppTheme.dimensions.fontSizeButton,
                        fontWeight = FontWeight.Medium,
                        color = AppTheme.colors.textSecondary
                    )
                }
            }

            Row(
                modifier = Modifier.align(Alignment.TopEnd),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(AppTheme.dimensions.historyStatusDotSize)
                        .background(
                            color = getScoreColor(item.scorePercentage),
                            shape = RoundedCornerShape(50)
                        )
                )
                Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingXxxs))
                Text(
                    text = "${item.scorePercentage}%",
                    fontSize = AppTheme.dimensions.fontSizeButton,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.textPrimary
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