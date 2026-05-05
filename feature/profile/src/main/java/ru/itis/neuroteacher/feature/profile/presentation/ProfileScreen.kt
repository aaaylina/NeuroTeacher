package ru.itis.neuroteacher.feature.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import ru.itis.neuroteacher.feature.profile.R
import androidx.hilt.navigation.compose.hiltViewModel
import ru.itis.neuroteacher.feature.profile.presentation.components.*
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.clearError()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.refreshStatistics()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = AppTheme.colors.backgroundGradientMain)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = AppTheme.dimensions.paddingHorizontal,
                    vertical = AppTheme.dimensions.profileCardTopPadding
                )
        ) {
            Text(
                text = stringResource(R.string.profile_title),
                style = AppTheme.typography.profileTitle,
                modifier = Modifier.padding(bottom = AppTheme.dimensions.profileHeaderBottomPadding)
            )

            ProfileHeaderCard()
            Spacer(modifier = Modifier.height(AppTheme.dimensions.statsRowSpacing))
            StatsGrid(uiState = uiState)
            Spacer(modifier = Modifier.height(AppTheme.dimensions.statsRowSpacing))
            ThemeSelectorCard(
                uiState = uiState,
                onThemeSelected = viewModel::onThemeSelected
            )
            Spacer(modifier = Modifier.height(AppTheme.dimensions.statsRowSpacing))
            LanguageSelectorCard(
                uiState = uiState,
                onLanguageSelected = viewModel::onLanguageSelected
            )
            Spacer(modifier = Modifier.height(AppTheme.dimensions.statsRowSpacing))
            ActionsCard(
                onClearData = { viewModel.onClearData(onSuccess = {}) },
                onLogout = { viewModel.onLogout(onSuccess = onNavigateToLogin) }
            )
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}