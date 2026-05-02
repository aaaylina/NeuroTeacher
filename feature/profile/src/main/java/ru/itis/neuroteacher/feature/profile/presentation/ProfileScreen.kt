package ru.itis.neuroteacher.feature.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    val colors = AppTheme.colors
    val dimensions = AppTheme.dimensions

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors.backgroundGradientMain)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = dimensions.paddingHorizontal,
                    vertical = dimensions.profileCardTopPadding
                )
        ) {
            Text(
                text = stringResource(R.string.profile_title),
                style = AppTheme.typography.profileTitle,
                modifier = Modifier.padding(bottom = dimensions.profileHeaderBottomPadding)
            )

            ProfileHeaderCard()
            Spacer(modifier = Modifier.height(dimensions.statsRowSpacing))
            StatsGrid(uiState = uiState)
            Spacer(modifier = Modifier.height(dimensions.statsRowSpacing))
            ThemeSelectorCard(
                uiState = uiState,
                onThemeSelected = viewModel::onThemeSelected
            )
            Spacer(modifier = Modifier.height(dimensions.statsRowSpacing))
            LanguageSelectorCard(
                uiState = uiState,
                onLanguageSelected = viewModel::onLanguageSelected
            )
            Spacer(modifier = Modifier.height(dimensions.statsRowSpacing))
            ActionsCard(
                onClearData = viewModel::onClearData,
                onLogout = viewModel::onLogout
            )
        }
    }
}