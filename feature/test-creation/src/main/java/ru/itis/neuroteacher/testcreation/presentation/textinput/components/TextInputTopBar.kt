package ru.itis.neuroteacher.testcreation.presentation.textinput.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.itis.neuroteacher.ui.theme.AppTheme
import ru.itis.neuroteacher.testcreation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TextInputTopBar(
    onNavigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.text_input_title),
                style = AppTheme.typography.title.copy(fontSize = AppTheme.dimensions.fontSizeTopBarTitle)
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.cd_back),
                    tint = AppTheme.colors.textPrimary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppTheme.colors.cardBackground
        )
    )
}