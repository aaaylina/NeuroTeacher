package ru.itis.neuroteacher.feature.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import ru.itis.neuroteacher.feature.profile.R
import androidx.hilt.navigation.compose.hiltViewModel
import ru.itis.neuroteacher.feature.profile.navigation.ProfileRouter
import ru.itis.neuroteacher.feature.profile.presentation.components.*
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun ProfileScreen(
    router: ProfileRouter,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    val errorMessage = uiState.errorResId?.let { stringResource(it) }

    LaunchedEffect(Unit) {
        viewModel.navigationEvents.collect { event ->
            when (event) {
                is ProfileNavigationEvent.NavigateToLogin -> {
                    router.navigateToLogin()
                }
            }
        }
    }

    LaunchedEffect(uiState.errorResId) {
        errorMessage?.let { msg ->
            snackbarHostState.showSnackbar(message = msg)
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = AppTheme.dimensions.paddingHorizontal,
                    vertical = AppTheme.dimensions.profileCardTopPadding
                ),
            contentPadding = PaddingValues(bottom = AppTheme.dimensions.buttonHeight + AppTheme.dimensions.spacingLg)
        ) {
            item {
                Text(
                    text = stringResource(R.string.profile_title),
                    style = AppTheme.typography.profileTitle,
                    modifier = Modifier.padding(bottom = AppTheme.dimensions.profileHeaderBottomPadding)
                )
            }

            item { ProfileHeaderCard() }
            item { Spacer(modifier = Modifier.height(AppTheme.dimensions.statsRowSpacing)) }
            item { StatsGrid(uiState = uiState) }
            item { Spacer(modifier = Modifier.height(AppTheme.dimensions.statsRowSpacing)) }
            item {
                ThemeSelectorCard(
                    uiState = uiState,
                    onThemeSelected = viewModel::onThemeSelected
                )
            }
            item { Spacer(modifier = Modifier.height(AppTheme.dimensions.statsRowSpacing)) }
            item {
                LanguageSelectorCard(
                    uiState = uiState,
                    onLanguageSelected = viewModel::onLanguageSelected
                )
            }
            item { Spacer(modifier = Modifier.height(AppTheme.dimensions.statsRowSpacing)) }
            item {
                ActionsCard(
                    onClearData = { viewModel.onClearData(onSuccess = {}) },
                    onLogout = { viewModel.onLogout() }
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}