package ru.itis.neuroteacher.testcreation.presentation.test.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import ru.itis.neuroteacher.ui.theme.AppTheme
import ru.itis.neuroteacher.testcreation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TestTopBar(
    testTitle: String,
    onNavigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = testTitle,
                style = AppTheme.typography.title.copy(
                    fontSize = AppTheme.dimensions.topBarTitleTestSize
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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