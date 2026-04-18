package ru.itis.neuroteacher.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
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
    val textOnWhite: Color,

    val successBackground: Color,
    val errorBackground: Color,
    val successBorder: Color,
    val errorBorder: Color,
    val explanationBackground: Color,
    val explanationText: Color,
    val progressBarTrack: Color
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

@Immutable
data class AppDimensions(
    val questionButtonWidth: Dp,
    val questionButtonHeight: Dp,
    val topBarTitleSize: TextUnit,
    val iconSizeSmall: Dp,
    val iconSizeMedium: Dp,
    val radiusSmall: Dp,
    val radiusMedium: Dp,
    val radiusLarge: Dp,
    val spacingXs: Dp,
    val spacingSm: Dp,
    val spacingMd: Dp,
    val spacingLg: Dp,
    val spacingXl: Dp,
    val spacingXxxs: Dp,
    val textFieldMinHeight: Dp,
    val progressHeight: Dp,
    val buttonHeight: Dp,
    val fontSizeHelper: TextUnit,
    val fontSizeButton: TextUnit,
    val fontSizeCardTitle: TextUnit,
    val fontSizeTopBarTitle: TextUnit,
    val questionTextSize: TextUnit,
    val optionTextSize: TextUnit,
    val explanationTitleSize: TextUnit,
    val explanationTextSize: TextUnit,
    val topBarTitleTestSize: TextUnit
)

val LocalAppColors = staticCompositionLocalOf {
    AppColors(
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
        backgroundGradient = emptyList(),
        backgroundGradientMain = emptyList(),
        iconGradientStart = Color.Unspecified,
        iconGradientEnd = Color.Unspecified,
        shadowColor = Color.Unspecified,
        bottomBarBorder = Color.Unspecified,
        textOnWhite = Color.Unspecified,
        successBackground = Color.Unspecified,
        errorBackground = Color.Unspecified,
        successBorder = Color.Unspecified,
        errorBorder = Color.Unspecified,
        explanationBackground = Color.Unspecified,
        explanationText = Color.Unspecified,
        progressBarTrack = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        title = TextStyle.Default,
        subtitle = TextStyle.Default,
        button = TextStyle.Default,
        label = TextStyle.Default,
        placeholder = TextStyle.Default,
        error = TextStyle.Default,
        welcomeText = TextStyle.Default,
        cardTitle = TextStyle.Default,
        cardSubtitle = TextStyle.Default,
        sectionTitle = TextStyle.Default,
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

val LocalAppDimensions = staticCompositionLocalOf {
    AppDimensions(
        questionButtonWidth = 0.dp,
        questionButtonHeight = 0.dp,
        topBarTitleSize = TextUnit.Unspecified,
        iconSizeSmall = 0.dp,
        iconSizeMedium = 0.dp,
        radiusSmall = 0.dp,
        radiusMedium = 0.dp,
        radiusLarge = 0.dp,
        spacingXs = 0.dp,
        spacingSm = 0.dp,
        spacingMd = 0.dp,
        spacingLg = 0.dp,
        spacingXl = 0.dp,
        spacingXxxs = 0.dp,
        textFieldMinHeight = 0.dp,
        progressHeight = 0.dp,
        buttonHeight = 0.dp,
        fontSizeHelper = TextUnit.Unspecified,
        fontSizeButton = TextUnit.Unspecified,
        fontSizeCardTitle = TextUnit.Unspecified,
        fontSizeTopBarTitle = TextUnit.Unspecified,
        questionTextSize = TextUnit.Unspecified,
        optionTextSize = TextUnit.Unspecified,
        explanationTitleSize = TextUnit.Unspecified,
        explanationTextSize = TextUnit.Unspecified,
        topBarTitleTestSize = TextUnit.Unspecified
    )
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val colors = AppColors(
        primary = Primary,
        primaryVariant = PrimaryVariant,
        backgroundLight = BackgroundLight,
        cardBackground = CardBackground,
        textPrimary = TextPrimary,
        textSecondary = TextSecondary,
        textHint = TextHint,
        textLabel = TextLabel,
        borderDefault = BorderDefault,
        borderFocused = BorderFocused,
        borderError = BorderError,
        error = Error,
        disabled = Disabled,
        backgroundGradient = BackgroundGradient,
        backgroundGradientMain = BackgroundGradientMain,
        iconGradientStart = IconGradientStart,
        iconGradientEnd = IconGradientEnd,
        shadowColor = ShadowColor,
        bottomBarBorder = BottomBarBorder,
        textOnWhite = TextOnWhite,

        successBackground = SuccessBackground,
        errorBackground = ErrorBackground,
        successBorder = SuccessBorder,
        errorBorder = ErrorBorder,
        explanationBackground = ExplanationBackground,
        explanationText = ExplanationText,
        progressBarTrack = ProgressBarTrack
    )

    val typography = AppTypography(
        title = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Medium, color = colors.textPrimary),
        subtitle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = colors.textSecondary),
        button = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White),
        label = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = colors.textLabel),
        placeholder = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, color = colors.textHint),
        error = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, color = colors.error),
        welcomeText = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold, color = Color.White),
        cardTitle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = colors.textOnWhite),
        cardSubtitle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF4A5565)),
        sectionTitle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.White),
        emptyStateText = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium, color = colors.textOnWhite)
    )

    val shapes = AppShapes(
        inputCorner = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        buttonCorner = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        cardCorner = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
        iconRound = androidx.compose.foundation.shape.CircleShape
    )

    val dimensions = AppDimensions(
        questionButtonWidth = 64.dp,
        questionButtonHeight = 48.dp,
        topBarTitleSize = 20.sp,
        iconSizeSmall = 20.dp,
        iconSizeMedium = 24.dp,
        radiusSmall = 12.dp,
        radiusMedium = 16.dp,
        radiusLarge = 24.dp,
        spacingXs = 4.dp,
        spacingSm = 8.dp,
        spacingMd = 12.dp,
        spacingLg = 16.dp,
        spacingXl = 24.dp,
        spacingXxxs = 4.dp,
        textFieldMinHeight = 300.dp,
        progressHeight = 4.dp,
        buttonHeight = 50.dp,
        fontSizeHelper = 12.sp,
        fontSizeButton = 16.sp,
        fontSizeCardTitle = 16.sp,
        fontSizeTopBarTitle = 20.sp,
        questionTextSize = 18.sp,
        optionTextSize = 15.sp,
        explanationTitleSize = 14.sp,
        explanationTextSize = 13.sp,
        topBarTitleTestSize = 18.sp
    )

    androidx.compose.runtime.CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides typography,
        LocalAppShapes provides shapes,
        LocalAppDimensions provides dimensions
    ) {
        content()
    }
}

object AppTheme {
    val colors: AppColors
        @Composable get() = LocalAppColors.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val shapes: AppShapes
        @Composable get() = LocalAppShapes.current

    val dimensions: AppDimensions
        @Composable get() = LocalAppDimensions.current
}