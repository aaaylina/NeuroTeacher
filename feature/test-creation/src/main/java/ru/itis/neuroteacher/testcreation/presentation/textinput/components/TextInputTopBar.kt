package ru.itis.neuroteacher.testcreation.presentation.textinput.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TextInputTopBar(
    onNavigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.text_input_title),
                style = AppTheme.typography.title.copy(
                    fontSize = dimensionResource(R.dimen.font_size_top_bar_title).value.sp
                )
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