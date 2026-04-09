package ru.itis.neuroteacher.auth.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ru.itis.neuroteacher.auth.ui.theme.AuthTheme

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = PasswordVisualTransformation()
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = AuthTheme.typography.label,
            color = AuthTheme.colors.textLabel
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    style = AuthTheme.typography.placeholder,
                    color = AuthTheme.colors.textHint
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = AuthTheme.colors.textHint
                )
            },
            trailingIcon = trailingIcon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = if (onTrailingIconClick != null)
                            AuthTheme.colors.primary
                        else
                            AuthTheme.colors.textHint,
                        modifier = if (onTrailingIconClick != null)
                            Modifier.clickable { onTrailingIconClick() }
                        else Modifier
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            isError = isError,
            shape = AuthTheme.shapes.inputCorner,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AuthTheme.colors.borderFocused,
                unfocusedBorderColor = AuthTheme.colors.borderDefault,
                cursorColor = AuthTheme.colors.primary,
                errorBorderColor = AuthTheme.colors.borderError
            )
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                style = AuthTheme.typography.error,
                color = AuthTheme.colors.error
            )
        }
    }
}