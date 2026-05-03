package ru.itis.neuroteacher.feature.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ru.itis.neuroteacher.feature.profile.R
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun ProfileHeaderCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.cardBackground),
        shape = AppTheme.shapes.cardCorner,
        modifier = Modifier
            .fillMaxWidth()
            .height(AppTheme.dimensions.profileHeaderHeight)
            .shadow(
                elevation = AppTheme.dimensions.shadowElevation,
                ambientColor = AppTheme.colors.shadowColor,
                spotColor = AppTheme.colors.shadowColor
            )
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.spacingLg),
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(AppTheme.dimensions.profileIconSize)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(AppTheme.colors.iconGradientStart, AppTheme.colors.iconGradientEnd)
                            ),
                            shape = AppTheme.shapes.iconRound
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = stringResource(R.string.profile_cd_avatar),
                        tint = AppTheme.colors.white,
                        modifier = Modifier.size(AppTheme.dimensions.profileIconInnerSize)
                    )
                }
                Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingMd))
                Text(
                    text = stringResource(R.string.profile_title),
                    fontSize = AppTheme.dimensions.fontSizeProfileHeaderTitle,
                    fontWeight = FontWeight.Bold,
                    color = AppTheme.colors.textPrimary
                )
            }
        }
    }
}