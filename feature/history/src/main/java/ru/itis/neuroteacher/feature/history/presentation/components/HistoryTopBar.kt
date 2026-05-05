package ru.itis.neuroteacher.feature.history.presentation.components

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ru.itis.neuroteacher.feature.history.R
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun HistoryTopBar(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.cardBackground)
            .padding(
                top = AppTheme.dimensions.profileCardTopPadding,
                start = AppTheme.dimensions.paddingHorizontal,
                end = AppTheme.dimensions.paddingHorizontal,
                bottom = 1.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.dimensions.headerHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.history_cd_back),
                tint = AppTheme.colors.textPrimary,
                modifier = Modifier
                    .size(AppTheme.dimensions.iconSizeMedium)
                    .clickable(onClick = onBackClick)
            )
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