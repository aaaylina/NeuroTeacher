package ru.itis.neuroteacher.feature.profile.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ru.itis.neuroteacher.feature.profile.R
import ru.itis.neuroteacher.ui.theme.AppTheme
import ru.itis.neuroteacher.ui.theme.AppTheme.colors

@Composable
fun ActionsCard(
    onClearData: () -> Unit,
    onLogout: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.cardBackground),
        shape = AppTheme.shapes.cardCorner,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            ActionItem(
                text = stringResource(R.string.profile_clear_data),
                icon = Icons.Default.Delete,
                iconColor = colors.deleteIconColor,
                divider = true,
                onClick = onClearData
            )
            ActionItem(
                text = stringResource(R.string.profile_logout),
                icon = Icons.AutoMirrored.Filled.Logout,
                iconColor = AppTheme.colors.textOnCard,
                divider = false,
                onClick = onLogout
            )
        }
    }
}

@Composable
private fun ActionItem(
    text: String,
    icon: ImageVector,
    iconColor: Color,
    divider: Boolean,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.dimensions.actionItemHeight)
                .padding(horizontal = AppTheme.dimensions.spacingMd)
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(AppTheme.dimensions.iconSizeSmall)
                )
                Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingMd))
                Text(
                    text = text,
                    fontSize = AppTheme.dimensions.fontSizeButton,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.textOnCard
                )
            }
        }
        if (divider) {
            HorizontalDivider(
                thickness = AppTheme.dimensions.dividerThickness,
                color = AppTheme.colors.borderDefault
            )
        }
    }
}