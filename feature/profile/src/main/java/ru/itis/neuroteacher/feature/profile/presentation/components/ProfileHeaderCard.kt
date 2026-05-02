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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.itis.neuroteacher.feature.profile.R
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun ProfileHeaderCard() {
    val colors = AppTheme.colors
    val shapes = AppTheme.shapes
    val dimensions = AppTheme.dimensions

    Card(
        colors = CardDefaults.cardColors(containerColor = colors.cardBackground),
        shape = shapes.cardCorner,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensions.profileHeaderHeight)
            .shadow(
                elevation = dimensions.shadowElevation,
                ambientColor = colors.shadowColor,
                spotColor = colors.shadowColor
            )
    ) {
        Column(
            modifier = Modifier.padding(dimensions.spacingLg),
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(dimensions.profileIconSize)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(colors.iconGradientStart, colors.iconGradientEnd)
                            ),
                            shape = shapes.iconRound
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = stringResource(R.string.profile_cd_avatar),
                        tint = colors.white,
                        modifier = Modifier.size(dimensions.profileIconInnerSize)
                    )
                }
                Spacer(modifier = Modifier.width(dimensions.spacingMd))
                Text(
                    text = stringResource(R.string.profile_title),
                    fontSize = dimensions.fontSizeProfileHeaderTitle,
                    fontWeight = FontWeight.Bold,
                    color = colors.textPrimary
                )
            }
        }
    }
}