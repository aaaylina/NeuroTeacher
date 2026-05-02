package ru.itis.neuroteacher.feature.profile.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.itis.neuroteacher.feature.profile.R
import ru.itis.neuroteacher.ui.theme.AppTheme
import ru.itis.neuroteacher.ui.theme.AppTheme.colors

@Composable
fun ActionsCard(
    onClearData: () -> Unit,
    onLogout: () -> Unit
) {
    val colors = AppTheme.colors
    val dimensions = AppTheme.dimensions

    Card(
        colors = CardDefaults.cardColors(containerColor = colors.cardBackground),
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
                icon = Icons.Default.Logout,
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
    val dimensions = AppTheme.dimensions

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensions.actionItemHeight)
                .padding(horizontal = dimensions.spacingMd)
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(dimensions.iconSizeSmall)
                )
                Spacer(modifier = Modifier.width(dimensions.spacingMd))
                Text(
                    text = text,
                    fontSize = dimensions.fontSizeButton,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.textOnCard
                )
            }
        }
        if (divider) {
            HorizontalDivider(Modifier, thickness = 1.dp, color = colors.borderDefault)
        }
    }
}