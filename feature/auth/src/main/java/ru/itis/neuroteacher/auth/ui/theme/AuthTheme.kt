package ru.itis.neuroteacher.auth.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.itis.neuroteacher.auth.R

@Immutable
data class AuthColors(
    val primary: Color,
    val primaryVariant: Color,
    val backgroundLight: Color,
    val cardBackground: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textHint: Color,
    val textLabel: Color,
    val borderDefault: Color,
    val borderFocused: Color,
    val borderError: Color,
    val error: Color,
    val disabled: Color,
    val backgroundGradient: List<Color>
)

@Immutable
data class AuthTypography(
    val title: TextStyle,
    val subtitle: TextStyle,
    val button: TextStyle,
    val label: TextStyle,
    val placeholder: TextStyle,
    val error: TextStyle
)

@Immutable
data class AuthShapes(
    val cardCorner: androidx.compose.foundation.shape.RoundedCornerShape,
    val inputCorner: androidx.compose.foundation.shape.RoundedCornerShape,
    val buttonCorner: androidx.compose.foundation.shape.RoundedCornerShape
)

val LocalAuthColors = androidx.compose.runtime.staticCompositionLocalOf {
    AuthColors(
        primary = Color.Unspecified,
        primaryVariant = Color.Unspecified,
        backgroundLight = Color.Unspecified,
        cardBackground = Color.Unspecified,
        textPrimary = Color.Unspecified,
        textSecondary = Color.Unspecified,
        textHint = Color.Unspecified,
        textLabel = Color.Unspecified,
        borderDefault = Color.Unspecified,
        borderFocused = Color.Unspecified,
        borderError = Color.Unspecified,
        error = Color.Unspecified,
        disabled = Color.Unspecified,
        backgroundGradient = emptyList()
    )
}

val LocalAuthTypography = androidx.compose.runtime.staticCompositionLocalOf {
    AuthTypography(
        title = TextStyle.Default,
        subtitle = TextStyle.Default,
        button = TextStyle.Default,
        label = TextStyle.Default,
        placeholder = TextStyle.Default,
        error = TextStyle.Default
    )
}

val LocalAuthShapes = androidx.compose.runtime.staticCompositionLocalOf {
    AuthShapes(
        cardCorner = androidx.compose.foundation.shape.RoundedCornerShape(0.dp),
        inputCorner = androidx.compose.foundation.shape.RoundedCornerShape(0.dp),
        buttonCorner = androidx.compose.foundation.shape.RoundedCornerShape(0.dp)
    )
}

@Composable
fun AuthTheme(
    content: @Composable () -> Unit
) {
    val colors = AuthColors(
        primary = colorResource(id = R.color.primary),
        primaryVariant = colorResource(id = R.color.primary_variant),
        backgroundLight = colorResource(id = R.color.background_light),
        cardBackground = colorResource(id = R.color.card_background),
        textPrimary = colorResource(id = R.color.text_primary),
        textSecondary = colorResource(id = R.color.text_secondary),
        textHint = colorResource(id = R.color.text_hint),
        textLabel = colorResource(id = R.color.text_label),
        borderDefault = colorResource(id = R.color.border_default),
        borderFocused = colorResource(id = R.color.border_focused),
        borderError = colorResource(id = R.color.border_error),
        error = colorResource(id = R.color.error),
        disabled = colorResource(id = R.color.disabled),
        backgroundGradient = listOf(
            colorResource(id = R.color.primary),
            colorResource(id = R.color.primary_variant)
        )
    )

    val typography = AuthTypography(
        title = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Medium, color = colors.textPrimary),
        subtitle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = colors.textSecondary),
        button = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White),
        label = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = colors.textLabel),
        placeholder = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = colors.textHint),
        error = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, color = colors.error)
    )

    val shapes = AuthShapes(
        cardCorner = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
        inputCorner = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        buttonCorner = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
    )

    androidx.compose.runtime.CompositionLocalProvider(
        LocalAuthColors provides colors,
        LocalAuthTypography provides typography,
        LocalAuthShapes provides shapes
    ) {
        content()
    }
}

object AuthTheme {
    val colors: AuthColors
        @Composable
        get() = LocalAuthColors.current

    val typography: AuthTypography
        @Composable
        get() = LocalAuthTypography.current

    val shapes: AuthShapes
        @Composable
        get() = LocalAuthShapes.current
}