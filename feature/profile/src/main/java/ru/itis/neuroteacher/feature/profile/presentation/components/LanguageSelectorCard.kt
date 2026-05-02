package ru.itis.neuroteacher.feature.profile.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.itis.neuroteacher.feature.profile.R
import ru.itis.neuroteacher.feature.profile.presentation.LanguageOption
import ru.itis.neuroteacher.feature.profile.presentation.ProfileUiState
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun LanguageSelectorCard(
    uiState: ProfileUiState,
    onLanguageSelected: (LanguageOption) -> Unit
) {
    val colors = AppTheme.colors
    val dimensions = AppTheme.dimensions

    Card(
        colors = CardDefaults.cardColors(containerColor = colors.cardBackground),
        shape = AppTheme.shapes.cardCorner,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = dimensions.profileCardTopPadding,
                vertical = dimensions.profileCardTopPadding
            )
        ) {
            Text(
                text = stringResource(R.string.profile_language_title),
                fontSize = dimensions.fontSizeProfileSectionTitle,
                fontWeight = FontWeight.Medium,
                color = AppTheme.colors.textOnCard
            )
            Spacer(modifier = Modifier.height(dimensions.statsRowSpacing))
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensions.statsRowSpacing)
            ) {
                LangButton(
                    isSelected = uiState.selectedLanguage == LanguageOption.RUSSIAN,
                    label = stringResource(R.string.profile_language_russian),
                    onClick = { onLanguageSelected(LanguageOption.RUSSIAN) },
                    modifier = Modifier.weight(1f)
                )
                LangButton(
                    isSelected = uiState.selectedLanguage == LanguageOption.ENGLISH,
                    label = stringResource(R.string.profile_language_english),
                    onClick = { onLanguageSelected(LanguageOption.ENGLISH) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun LangButton(
    isSelected: Boolean,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = AppTheme.colors
    val dimensions = AppTheme.dimensions
    val bgColor = if (isSelected) colors.primary else colors.buttonUnselectedBackground
    val textColor = if (isSelected) Color.White else colors.buttonUnselectedText
    val borderColor = if (isSelected) Color.Transparent else colors.borderDefault

    Card(
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(dimensions.cornerRadiusProfileButton),
        modifier = modifier
            .height(dimensions.langButtonHeight)
            .border(dimensions.borderWidthProfileButton, borderColor, RoundedCornerShape(dimensions.cornerRadiusProfileButton))
            .shadow(
                elevation = if (isSelected) 4.dp else 0.dp,
                ambientColor = colors.shadowColor,
                spotColor = colors.shadowColor
            ),
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.Language,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(dimensions.iconSizeProfileButton)
            )
            Spacer(modifier = Modifier.width(dimensions.spacingIconText))
            Text(
                text = label,
                fontSize = dimensions.fontSizeProfileButtonLabel,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        }
    }
}