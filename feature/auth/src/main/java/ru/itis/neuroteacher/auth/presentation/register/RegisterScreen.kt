package ru.itis.neuroteacher.auth.presentation.register

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import ru.itis.neuroteacher.auth.R
import ru.itis.neuroteacher.auth.presentation.components.AuthButton
import ru.itis.neuroteacher.auth.presentation.components.AuthTextField
import ru.itis.neuroteacher.auth.presentation.components.AuthToolbar
import ru.itis.neuroteacher.auth.ui.theme.AppTypography

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val isFormValid = email.isNotBlank() &&
            password.length >= 6 &&
            password == confirmPassword

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.primary),
                        colorResource(id = R.color.primary_variant)
                    )
                )
            )
            .padding(dimensionResource(id = R.dimen.spacing_lg)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)))
                .background(colorResource(id = R.color.card_background))
                .padding(dimensionResource(id = R.dimen.card_padding))
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.logo_container_size))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.logo_corner_radius)))
                    .background(colorResource(id = R.color.background_light)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.logotype2),
                    contentDescription = stringResource(id = R.string.logo_description),
                    tint = androidx.compose.ui.graphics.Color.Unspecified,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.logo_size))
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_lg)))

            Text(
                text = stringResource(id = R.string.register_title),
                style = AppTypography.title,
                color = colorResource(id = R.color.text_primary)
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_sm)))

            Text(
                text = stringResource(id = R.string.register_subtitle),
                style = AppTypography.subtitle,
                color = colorResource(id = R.color.text_secondary)
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_xl)))

            AuthTextField(
                value = email,
                onValueChange = { email = it },
                label = stringResource(id = R.string.register_email_label),
                placeholder = stringResource(id = R.string.register_email_placeholder),
                leadingIcon = Icons.Default.Email
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_md)))

            AuthTextField(
                value = password,
                onValueChange = { password = it },
                label = stringResource(id = R.string.register_password_label),
                placeholder = stringResource(id = R.string.register_password_placeholder),
                leadingIcon = Icons.Default.Lock,
                trailingIcon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                onTrailingIconClick = { passwordVisible = !passwordVisible },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_md)))

            AuthTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = stringResource(id = R.string.register_confirm_password_label),
                placeholder = stringResource(id = R.string.register_confirm_password_placeholder),
                leadingIcon = Icons.Default.Lock,
                trailingIcon = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                onTrailingIconClick = { confirmPasswordVisible = !confirmPasswordVisible },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_xl)))

            AuthButton(
                text = stringResource(id = R.string.register_button),
                onClick = {
                    isLoading = true
                    onRegisterSuccess()
                },
                enabled = isFormValid,
                isLoading = isLoading
            )

            AuthToolbar(
                primaryText = stringResource(id = R.string.register_have_account),
                secondaryText = stringResource(id = R.string.register_login_link),
                onSecondaryClick = onNavigateToLogin
            )
        }
    }
}