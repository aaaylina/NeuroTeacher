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
   Card(
       colors = CardDefaults.cardColors(containerColor = AppTheme.colors.cardBackground),
        shape = AppTheme.shapes.cardCorner,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = AppTheme.dimensions.profileCardTopPadding,
                vertical = AppTheme.dimensions.profileCardTopPadding
            )
        ) {
            Text(
                text = stringResource(R.string.profile_language_title),
                fontSize = AppTheme.dimensions.fontSizeProfileSectionTitle,
                fontWeight = FontWeight.Medium,
                color = AppTheme.colors.textOnCard
            )
            Spacer(modifier = Modifier.height(AppTheme.dimensions.statsRowSpacing))
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.statsRowSpacing)
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
private fun LangButton(
    isSelected: Boolean,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor = if (isSelected) AppTheme.colors.primary else AppTheme.colors.buttonUnselectedBackground
    val textColor = if (isSelected) AppTheme.colors.white else AppTheme.colors.buttonUnselectedText
    val borderColor = if (isSelected) Color.Transparent else AppTheme.colors.borderDefault

    Card(
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(AppTheme.dimensions.cornerRadiusProfileButton),
        modifier = modifier
            .height(AppTheme.dimensions.langButtonHeight)
            .border(AppTheme.dimensions.borderWidthProfileButton, borderColor, RoundedCornerShape(AppTheme.dimensions.cornerRadiusProfileButton))
            .shadow(
                elevation = if (isSelected) AppTheme.dimensions.shadowElevation else 0.dp,
                ambientColor = AppTheme.colors.shadowColor,
                spotColor = AppTheme.colors.shadowColor
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
                modifier = Modifier.size(AppTheme.dimensions.iconSizeProfileButton)
            )
            Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingIconText))
            Text(
                text = label,
                fontSize = AppTheme.dimensions.fontSizeProfileButtonLabel,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        }
    }
}