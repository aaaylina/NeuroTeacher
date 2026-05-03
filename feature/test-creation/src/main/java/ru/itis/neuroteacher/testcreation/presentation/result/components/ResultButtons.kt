package ru.itis.neuroteacher.testcreation.presentation.result.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
internal fun ResultButtons(
    onRetryClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = AppTheme.dimensions.spacingLg),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.spacingMd),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onRetryClick,
            modifier = Modifier
                .weight(1f)
                .height(AppTheme.dimensions.buttonLargeHeight),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTheme.colors.disabled.copy(alpha = 0.8f)
            ),
            shape =  AppTheme.shapes.buttonCornerSmall,
            contentPadding = PaddingValues(0.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = stringResource(R.string.test_result_cd_retry),
                    modifier = Modifier.size(AppTheme.dimensions.iconSizeResult),
                    tint = AppTheme.colors.white
                )
                Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingSm))
                Text(
                    text = stringResource(R.string.test_result_retry_button),
                    style = AppTheme.typography.button,
                    color = AppTheme.colors.white
                )
            }
        }

        Button(
            onClick = onHomeClick,
            modifier = Modifier
                .weight(1f)
                .height(AppTheme.dimensions.buttonLargeHeight)
                .shadow(
                    elevation = AppTheme.dimensions.buttonShadowElevation,
                    shape = AppTheme.shapes.buttonCornerSmall,
                    ambientColor = AppTheme.colors.shadowColor,
                    spotColor = AppTheme.colors.shadowColor
                ),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            shape = AppTheme.shapes.buttonCornerSmall,
            contentPadding = PaddingValues(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(colors = AppTheme.colors.backgroundGradientMain),
                        shape = AppTheme.shapes.buttonCornerSmall
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = stringResource(R.string.test_result_cd_home),
                        modifier = Modifier.size(AppTheme.dimensions.iconSizeResult),
                        tint = AppTheme.colors.white
                    )
                    Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingSm))
                    Text(
                        text = stringResource(R.string.test_result_home_button),
                        style = AppTheme.typography.button,
                        color = AppTheme.colors.white
                    )
                }
            }
        }
    }
}