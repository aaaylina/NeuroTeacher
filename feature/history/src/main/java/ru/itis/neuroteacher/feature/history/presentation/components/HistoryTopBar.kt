package ru.itis.neuroteacher.feature.history.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ru.itis.neuroteacher.feature.history.R
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun HistoryTopBar(onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        color = AppTheme.colors.cardBackground,
        shadowElevation = AppTheme.dimensions.shadowElevation,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.dimensions.historyHeaderHeight)
                .padding(horizontal = AppTheme.dimensions.paddingHorizontal),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.history_cd_back),
                    tint = AppTheme.colors.textPrimary
                )
            }
            Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingMd))

            Text(
                text = stringResource(R.string.history_title),
                style = AppTheme.typography.profileTitle.copy(
                    fontSize = AppTheme.dimensions.fontSizeTopBarTitle,
                    fontWeight = FontWeight.SemiBold
                ),
                color = AppTheme.colors.textPrimary
            )
        }
    }
}