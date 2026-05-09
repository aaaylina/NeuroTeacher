package ru.itis.neuroteacher.feature.history.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itis.neuroteacher.feature.history.R
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        AppTheme.colors.historyBgGradientStart,
                        AppTheme.colors.historyBgGradientMid,
                        AppTheme.colors.historyBgGradientEnd
                    ),
                    start = androidx.compose.ui.geometry.Offset.Zero,
                    end = androidx.compose.ui.geometry.Offset.Infinite
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HistoryTopBar(onBackClick = { router.navigateUp() })
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = AppTheme.dimensions.paddingHorizontal,
                        vertical = AppTheme.dimensions.spacingLg
                    )
            ) {
                HistorySearchField(
                    query = uiState.searchQuery,
                    onQueryChanged = viewModel::onSearchQueryChanged
                )
            }

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
                        onClick = {
                            router.navigateToTestResult(
                                testId = item.testId,
                                resultId = item.resultId
                            )
                        }
                    )
                }

                if (uiState.historyItems.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(top = AppTheme.dimensions.spacingLg),
                            contentAlignment = Alignment.Center
                        ) {
                            HistoryEmptyState(
                                onNavigateHome = { router.navigateUp() }
                            )
                        }
                    }
                }
            }
        }
    }
}