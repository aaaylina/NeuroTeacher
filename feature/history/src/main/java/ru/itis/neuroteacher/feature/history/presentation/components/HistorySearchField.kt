package ru.itis.neuroteacher.feature.history.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import ru.itis.neuroteacher.feature.history.R
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun HistorySearchField(
    query: String,
    onQueryChanged: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        placeholder = {
            Text(
                text = stringResource(R.string.history_search_placeholder),
                style = TextStyle(
                    fontSize = AppTheme.dimensions.fontSizeButton,
                    color = AppTheme.colors.textSecondary
                )
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = AppTheme.colors.bottomBarUnselectedColor,
                modifier = Modifier.size(AppTheme.dimensions.iconSizeMedium)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(AppTheme.dimensions.buttonHeight)
            .clip(RoundedCornerShape(AppTheme.dimensions.buttonCornerRadius))
            .border(
                width = AppTheme.dimensions.borderWidthProfileButton,
                color = AppTheme.colors.borderDefault,
                shape = RoundedCornerShape(AppTheme.dimensions.buttonCornerRadius)
            ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = AppTheme.colors.cardBackground,
            unfocusedContainerColor = AppTheme.colors.cardBackground,
            disabledContainerColor = AppTheme.colors.cardBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = AppTheme.colors.primary
        ),
        textStyle = TextStyle(
            fontSize = AppTheme.dimensions.fontSizeButton,
            color = AppTheme.colors.textPrimary
        ),
        singleLine = true
    )
}