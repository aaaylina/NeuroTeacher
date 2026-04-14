package ru.itis.neuroteacher.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Immutable
data class AppColors(
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
    val backgroundGradient: List<Color>,

    val backgroundGradientMain: List<Color>,
    val iconGradientStart: Color,
    val iconGradientEnd: Color,
    val shadowColor: Color,
    val bottomBarBorder: Color,
    val textOnWhite: Color
)

@Immutable
data class AppTypography(
    val title: TextStyle,
    val subtitle: TextStyle,
    val button: TextStyle,
    val label: TextStyle,
    val placeholder: TextStyle,
    val error: TextStyle,

    val welcomeText: TextStyle,
    val cardTitle: TextStyle,
    val cardSubtitle: TextStyle,
    val sectionTitle: TextStyle,
    val emptyStateText: TextStyle
)

@Immutable
data class AppShapes(
    val inputCorner: androidx.compose.foundation.shape.RoundedCornerShape,
    val buttonCorner: androidx.compose.foundation.shape.RoundedCornerShape,

    val cardCorner: androidx.compose.foundation.shape.RoundedCornerShape,
    val iconRound: androidx.compose.foundation.shape.RoundedCornerShape
)

val LocalAppColors = staticCompositionLocalOf {
    AppColors(
        primary = Color.Unspecified, primaryVariant = Color.Unspecified,
        backgroundLight = Color.Unspecified, cardBackground = Color.Unspecified,
        textPrimary = Color.Unspecified, textSecondary = Color.Unspecified,
        textHint = Color.Unspecified, textLabel = Color.Unspecified,
        borderDefault = Color.Unspecified, borderFocused = Color.Unspecified,
        borderError = Color.Unspecified, error = Color.Unspecified,
        disabled = Color.Unspecified, backgroundGradient = emptyList(),
        backgroundGradientMain = emptyList(),
        iconGradientStart = Color.Unspecified, iconGradientEnd = Color.Unspecified,
        shadowColor = Color.Unspecified, bottomBarBorder = Color.Unspecified,
        textOnWhite = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        title = TextStyle.Default, subtitle = TextStyle.Default,
        button = TextStyle.Default, label = TextStyle.Default,
        placeholder = TextStyle.Default, error = TextStyle.Default,
        welcomeText = TextStyle.Default, cardTitle = TextStyle.Default,
        cardSubtitle = TextStyle.Default, sectionTitle = TextStyle.Default,
        emptyStateText = TextStyle.Default
    )
}

val LocalAppShapes = staticCompositionLocalOf {
    AppShapes(
        inputCorner = androidx.compose.foundation.shape.RoundedCornerShape(0.dp),
        buttonCorner = androidx.compose.foundation.shape.RoundedCornerShape(0.dp),
        cardCorner = androidx.compose.foundation.shape.RoundedCornerShape(0.dp),
        iconRound = androidx.compose.foundation.shape.RoundedCornerShape(0.dp)
    )
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val colors = AppColors(
        primary = Color(0xFF4A6CF7),
        primaryVariant = Color(0xFF8B5CF6),
        backgroundLight = Color(0xFFEFF6FF),
        cardBackground = Color(0xFFFFFFFF),
        textPrimary = Color(0xFF000000),
        textSecondary = Color(0xFF6B7280),
        textHint = Color(0xFF9CA3AF),
        textLabel = Color(0xFF374151),
        borderDefault = Color(0xFFE5E7EB),
        borderFocused = Color(0xFF4A6CF7),
        borderError = Color(0xFFEF4444),
        error = Color(0xFFEF4444),
        disabled = Color(0xFFD1D5DB),
        backgroundGradient = listOf(Color(0xFF4A6CF7), Color(0xFF8B5CF6)),

        backgroundGradientMain = listOf(
            Color(0xFF155DFC),
            Color(0xFF9810FA),
            Color(0xFF8200DB)
        ),
        iconGradientStart = Color(0xFF2B7FFF),
        iconGradientEnd = Color(0xFF9810FA),
        shadowColor = Color(0x1A000000),
        bottomBarBorder = Color(0x14000000),
        textOnWhite = Color(0xFF101828)
    )

    val typography = AppTypography(
        title = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Medium, color = colors.textPrimary),
        subtitle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = colors.textSecondary),
        button = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White),
        label = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = colors.textLabel),
        placeholder = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = colors.textHint),
        error = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, color = colors.error),

        welcomeText = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        ),
        cardTitle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colors.textOnWhite
        ),
        cardSubtitle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF4A5565)
        ),
        sectionTitle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        ),
        emptyStateText = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = colors.textOnWhite
        )
    )

    val shapes = AppShapes(
        inputCorner = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        buttonCorner = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),

        cardCorner = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
        iconRound = androidx.compose.foundation.shape.CircleShape
    )

    androidx.compose.runtime.CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides typography,
        LocalAppShapes provides shapes
    ) {
        content()
    }
}

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current

    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current

    val shapes: AppShapes
        @Composable
        get() = LocalAppShapes.current
}