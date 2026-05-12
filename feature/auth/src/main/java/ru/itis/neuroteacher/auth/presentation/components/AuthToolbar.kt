package ru.itis.neuroteacher.auth.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun AuthToolbar(
    secondaryText: String,
    onSecondaryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = AppTheme.dimensions.spacingMd),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = secondaryText,
            style = AppTheme.typography.authLinkStyle,
            modifier = Modifier.clickable { onSecondaryClick() }
        )
    }
}