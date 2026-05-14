package ru.itis.neuroteacher.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.itis.neuroteacher.auth.R
import ru.itis.neuroteacher.auth.navigation.AuthRouter
import ru.itis.neuroteacher.auth.presentation.components.AuthButton
import ru.itis.neuroteacher.auth.presentation.components.AuthTextField
import ru.itis.neuroteacher.auth.presentation.components.AuthToolbar
import ru.itis.neuroteacher.auth.utils.AuthConstants
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun LoginScreen(
    router: AuthRouter,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                is LoginNavigationEvent.NavigateToMain -> {
                    router.navigateToMain()
                }
            }
        }
    }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    AppTheme(darkTheme = false) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = AppTheme.colors.backgroundGradient
                        )
                    )
                    .padding(AppTheme.dimensions.spacingLg),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(AppTheme.shapes.cardCorner)
                        .background(AppTheme.colors.cardBackground)
                        .padding(AppTheme.dimensions.cardPadding)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(AppTheme.dimensions.logoContainerSize)
                            .clip(AppTheme.shapes.iconRound)
                            .background(AppTheme.colors.logoBackgroundColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logo_icon),
                            contentDescription = stringResource(id = R.string.logo_description),
                            tint = Color.Unspecified,
                            modifier = Modifier.size(AppTheme.dimensions.iconSizeDefault)
                        )
                    }

                    Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingLg))

                    Text(
                        text = stringResource(id = R.string.login_welcome),
                        style = AppTheme.typography.title,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingSm))

                    Text(
                        text = stringResource(id = R.string.login_subtitle),
                        style = AppTheme.typography.subtitle
                    )

                    Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXl))

                    AuthTextField(
                        value = uiState.email,
                        onValueChange = viewModel::onEmailChange,
                        label = stringResource(id = R.string.login_email_label),
                        placeholder = stringResource(id = R.string.login_email_placeholder),
                        leadingIcon = Icons.Default.Email,
                        isError = uiState.isEmailError
                    )

                    Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingMd))

                    AuthTextField(
                        value = uiState.password,
                        onValueChange = viewModel::onPasswordChange,
                        label = stringResource(id = R.string.login_password_label),
                        placeholder = stringResource(id = R.string.login_password_placeholder),
                        leadingIcon = Icons.Default.Lock,
                        trailingIcon = if (uiState.passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        onTrailingIconClick = viewModel::onPasswordVisibilityToggle,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXl))

                    AuthButton(
                        text = stringResource(id = R.string.login_button),
                        onClick = {
                            viewModel.onLoginClick { }
                        },
                        enabled = uiState.email.isNotBlank() && uiState.password.length >= AuthConstants.MIN_PASSWORD_LENGTH,
                        isLoading = uiState.isLoading
                    )

                    AuthToolbar(
                        secondaryText = stringResource(id = R.string.login_register_link),
                        onSecondaryClick = { router.navigateToRegister() }
                    )
                }
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )

                if (uiState.isSyncing) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.7f))
                            .align(Alignment.Center),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                color = Color.White,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = stringResource(id = R.string.sync_load),
                                color = Color.White,
                                style = AppTheme.typography.subtitle
                            )
                        }
                    }
                }
            }
        }
    }
}