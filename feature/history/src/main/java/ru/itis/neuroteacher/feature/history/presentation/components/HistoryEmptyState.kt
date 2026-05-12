package ru.itis.neuroteacher.feature.history.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ru.itis.neuroteacher.feature.history.R
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun HistoryEmptyState(
    onNavigateHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(AppTheme.dimensions.containerWidth)
            .height(AppTheme.dimensions.historyEmptyStateHeight),
        shape = RoundedCornerShape(AppTheme.dimensions.historyEmptyStateCornerRadius),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.cardBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = AppTheme.dimensions.spacingXl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(AppTheme.dimensions.historyEmptyStateIconCircleSize)
                    .background(AppTheme.colors.emptyStateIconBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_book_empty_state),
                    contentDescription = null,
                    tint = AppTheme.colors.textSecondary,
                    modifier = Modifier.size(AppTheme.dimensions.historyEmptyStateIconSize)
                )
            }

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingMd))

            Text(
                text = stringResource(id = R.string.history_empty_title),
                style = AppTheme.typography.historyEmptyStateTitle
            )

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXs))

            Text(
                text = stringResource(id = R.string.history_empty_subtitle),
                style = AppTheme.typography.historyEmptyStateSubtitle
            )

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingLg))

            Box(
                modifier = Modifier
                    .width(AppTheme.dimensions.historyEmptyStateButtonWidth)
                    .height(AppTheme.dimensions.historyEmptyStateButtonHeight)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                AppTheme.colors.emptyStateButtonGradientStart,
                                AppTheme.colors.emptyStateButtonGradientEnd
                            )
                        ),
                        shape = RoundedCornerShape(AppTheme.dimensions.historyEmptyStateButtonCornerRadius)
                    )
                    .shadow(
                        elevation = AppTheme.dimensions.buttonShadowElevation,
                        ambientColor = AppTheme.colors.shadowColor,
                        spotColor = AppTheme.colors.shadowColor,
                        shape = RoundedCornerShape(AppTheme.dimensions.historyEmptyStateButtonCornerRadius)
                    )
                    .clickable { onNavigateHome() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.history_empty_go_home),
                    style = AppTheme.typography.button.copy(
                        fontSize = AppTheme.dimensions.fontSizeButton,
                        fontWeight = FontWeight.Medium
                    ),
                    color = AppTheme.colors.white
                )
            }
        }
    }
}