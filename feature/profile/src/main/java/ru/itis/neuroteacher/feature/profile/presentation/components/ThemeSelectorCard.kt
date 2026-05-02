package ru.itis.neuroteacher.feature.profile.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.itis.neuroteacher.feature.profile.R
import ru.itis.neuroteacher.feature.profile.presentation.ProfileUiState
import ru.itis.neuroteacher.feature.profile.presentation.ThemeOption
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun ThemeSelectorCard(
    uiState: ProfileUiState,
    onThemeSelected: (ThemeOption) -> Unit
) {
    val colors = AppTheme.colors
    val dimensions = AppTheme.dimensions

    Card(
        colors = CardDefaults.cardColors(containerColor = colors.cardBackground),
        shape = AppTheme.shapes.cardCorner,
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = dimensions.spacingCardHorizontalPadding)
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = dimensions.profileCardTopPadding,
                vertical = dimensions.profileCardTopPadding
            )
        ) {
            Text(
                text = stringResource(R.string.profile_theme_title),
                fontSize = dimensions.fontSizeProfileSectionTitle,
                fontWeight = FontWeight.Medium,
                color = colors.textOnCard
            )
            Spacer(modifier = Modifier.height(dimensions.statsRowSpacing))
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = dimensions.statsRowSpacing,
                    alignment = Alignment.CenterHorizontally
                ),
            ) {
                ThemeButton(
                    isSelected = uiState.selectedTheme == ThemeOption.LIGHT,
                    label = stringResource(R.string.profile_theme_light),
                    icon = Icons.Default.WbSunny,
                    onClick = { onThemeSelected(ThemeOption.LIGHT) }
                )
                ThemeButton(
                    isSelected = uiState.selectedTheme == ThemeOption.DARK,
                    label = stringResource(R.string.profile_theme_dark),
                    icon = Icons.Default.NightsStay,
                    onClick = { onThemeSelected(ThemeOption.DARK) }
                )
                ThemeButton(
                    isSelected = uiState.selectedTheme == ThemeOption.SYSTEM,
                    label = stringResource(R.string.profile_theme_system),
                    icon = Icons.Default.Computer,
                    onClick = { onThemeSelected(ThemeOption.SYSTEM) }
                )
            }
        }
    }
}

@Composable
private fun ThemeButton(
    isSelected: Boolean,
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    val colors = AppTheme.colors
    val dimensions = AppTheme.dimensions
    val bgColor = if (isSelected) colors.primary else colors.buttonUnselectedBackground
    val textColor = if (isSelected) Color.White else colors.buttonUnselectedText

    Card(
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(dimensions.cornerRadiusProfileButton),
        modifier = Modifier
            .height(dimensions.themeButtonHeight)
            .width(dimensions.themeButtonWidth)
            .shadow(
                elevation = if (isSelected) 4.dp else 0.dp,
                ambientColor = colors.shadowColor,
                spotColor = colors.shadowColor
            ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = dimensions.spacingThemeButtonVerticalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(dimensions.iconSizeThemeButton)
            )
            Spacer(modifier = Modifier.height(dimensions.spacingThemeButtonGap))
            Text(
                text = label,
                fontSize = dimensions.fontSizeProfileButtonLabel,
                fontWeight = FontWeight.Medium,
                color = textColor,
                textAlign = TextAlign.Center
            )
        }
    }
}