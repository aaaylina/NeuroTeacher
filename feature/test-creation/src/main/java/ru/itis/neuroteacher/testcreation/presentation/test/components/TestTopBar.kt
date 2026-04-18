package ru.itis.neuroteacher.testcreation.presentation.test.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.ui.theme.AppTheme

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
                    fontSize = dimensionResource(R.dimen.font_size_top_bar_title_test).value.sp
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