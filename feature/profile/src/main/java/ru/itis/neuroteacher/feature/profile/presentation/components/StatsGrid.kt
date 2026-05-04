package ru.itis.neuroteacher.feature.profile.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ru.itis.neuroteacher.feature.profile.R
import ru.itis.neuroteacher.feature.profile.presentation.ProfileUiState
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun StatsGrid(uiState: ProfileUiState) {
    Column {
        Row(
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.statsRowSpacing),
            modifier = Modifier.fillMaxWidth()
        ) {
            StatCard(
                title = stringResource(R.string.profile_total_tests),
                value = uiState.totalTests.toString(),
                icon = Icons.AutoMirrored.Filled.MenuBook,
                iconColor = AppTheme.colors.iconBookStroke,
                height = AppTheme.dimensions.statsCardHeightSmall,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                title = stringResource(R.string.profile_avg_score),
                value = uiState.avgScore,
                icon = Icons.AutoMirrored.Filled.TrendingUp,
                iconColor = AppTheme.colors.iconArrowStroke,
                height = AppTheme.dimensions.statsCardHeightSmall,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(AppTheme.dimensions.statsRowSpacing))
        Row(
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.statsRowSpacing),
            modifier = Modifier.fillMaxWidth()
        ) {
            StatCard(
                title = stringResource(R.string.profile_best_result),
                value = uiState.bestScore,
                icon = Icons.Default.EmojiEvents,
                iconColor = AppTheme.colors.iconTrophyStroke,
                height = AppTheme.dimensions.statsCardHeightLarge,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                title = stringResource(R.string.profile_completed),
                value = "10",
                icon = Icons.Default.CheckCircle,
                iconColor = AppTheme.colors.iconCheckStroke,
                height = AppTheme.dimensions.statsCardHeightLarge,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    icon: ImageVector,
    iconColor: Color,
    height: androidx.compose.ui.unit.Dp,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.cardBackground),
        shape = AppTheme.shapes.cardCorner,
        modifier = modifier
            .height(height)
            .shadow(
                elevation = AppTheme.dimensions.statsCardShadowElevation,
                ambientColor = AppTheme.colors.shadowColor,
                spotColor = AppTheme.colors.shadowColor
            )
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.spacingMd)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.statsCardIconSpacing)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(AppTheme.dimensions.statsCardIconSize)
                )
                Text(
                    text = title,
                    fontSize = AppTheme.dimensions.fontSizeStatsTitle,
                    color = AppTheme.colors.textSecondary,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXs))
            Text(
                text = value,
                fontSize = AppTheme.dimensions.fontSizeStatsValue,
                fontWeight = FontWeight.Bold,
                color = AppTheme.colors.textOnCard
            )
        }
    }
}