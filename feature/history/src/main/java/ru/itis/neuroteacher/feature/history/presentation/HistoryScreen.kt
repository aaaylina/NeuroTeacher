package ru.itis.neuroteacher.feature.history.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import ru.itis.neuroteacher.feature.history.navigation.HistoryRouter
import ru.itis.neuroteacher.feature.history.presentation.components.*
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun HistoryScreen(
    router: HistoryRouter,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = AppTheme.colors.backgroundGradientMain,
                    start = androidx.compose.ui.geometry.Offset.Zero,
                    end = androidx.compose.ui.geometry.Offset.Infinite
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HistoryTopBar(onBackClick = { router.navigateUp() })

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingMd))
            HistorySearchField(
                query = uiState.searchQuery,
                onQueryChanged = viewModel::onSearchQueryChanged
            )

            Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingLg))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = AppTheme.dimensions.paddingHorizontal),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.spacingMd),
                contentPadding = PaddingValues(bottom = AppTheme.dimensions.buttonHeight + AppTheme.dimensions.spacingLg)
            ) {
                items(uiState.historyItems) { item ->
                    HistoryItemCard(
                        item = item,
                        onClick = { router.navigateToTestResult(testId = item.testId, resultId = item.resultId) }
                    )
                }
            }
        }
    }
}