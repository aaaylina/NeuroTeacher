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
    val colors = AppTheme.colors
    val dimensions = AppTheme.dimensions

    Column {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensions.statsRowSpacing),
            modifier = Modifier.fillMaxWidth()
        ) {
            StatCard(
                title = stringResource(R.string.profile_total_tests),
                value = uiState.totalTests.toString(),
                icon = Icons.AutoMirrored.Filled.MenuBook,
                iconColor = colors.iconBookStroke,
                height = dimensions.statsCardHeightSmall,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                title = stringResource(R.string.profile_avg_score),
                value = uiState.avgScore,
                icon = Icons.AutoMirrored.Filled.TrendingUp,
                iconColor = colors.iconArrowStroke,
                height = dimensions.statsCardHeightSmall,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(dimensions.statsRowSpacing))
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensions.statsRowSpacing),
            modifier = Modifier.fillMaxWidth()
        ) {
            StatCard(
                title = stringResource(R.string.profile_best_result),
                value = uiState.bestScore,
                icon = Icons.Default.EmojiEvents,
                iconColor = colors.iconTrophyStroke,
                height = dimensions.statsCardHeightLarge,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                title = stringResource(R.string.profile_completed),
                value = "10",
                icon = Icons.Default.CheckCircle,
                iconColor = colors.iconCheckStroke,
                height = dimensions.statsCardHeightLarge,
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
    val colors = AppTheme.colors
    val dimensions = AppTheme.dimensions

    Card(
        colors = CardDefaults.cardColors(containerColor = colors.cardBackground),
        shape = AppTheme.shapes.cardCorner,
        modifier = modifier
            .height(height)
            .shadow(
                elevation = dimensions.statsCardShadowElevation,
                ambientColor = colors.shadowColor,
                spotColor = colors.shadowColor
            )
    ) {
        Column(
            modifier = Modifier.padding(dimensions.spacingMd)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensions.statsCardIconSpacing)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(dimensions.statsCardIconSize)
                )
                Text(
                    text = title,
                    fontSize = dimensions.fontSizeStatsTitle,
                    color = colors.textSecondary,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.height(dimensions.spacingXs))
            Text(
                text = value,
                fontSize = dimensions.fontSizeStatsValue,
                fontWeight = FontWeight.Bold,
                color = colors.textOnCard
            )
        }
    }
}