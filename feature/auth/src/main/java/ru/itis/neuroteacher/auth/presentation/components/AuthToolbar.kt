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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun AuthToolbar(
    primaryText: String,
    secondaryText: String,
    onSecondaryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = primaryText,
            style = AppTheme.typography.subtitle
        )
        Text(
            text = secondaryText,
            style = AppTheme.typography.subtitle.copy(
                color = AppTheme.colors.primary,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier.clickable { onSecondaryClick() }
        )
    }
}