package ru.itis.neuroteacher.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
    val chartSuccessBorder: Color,
    val chartErrorBorder: Color,
    val errorBorder: Color,
    val explanationBackground: Color,
    val explanationText: Color,
    val progressBarTrack: Color,
    val white: Color,
    val cardSubtitleColor: Color,
    val textOnCard: Color,
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
    val inputCorner: RoundedCornerShape,
    val buttonCorner: RoundedCornerShape,
    val cardCorner: RoundedCornerShape,
    val iconRound: RoundedCornerShape,
    val buttonCornerSmall: RoundedCornerShape,
)

@Immutable
data class AppDimensions(
    val spacingXxxs: Dp,
    val spacingXs: Dp,
    val spacingSm: Dp,
    val spacingMd: Dp,
    val spacingLg: Dp,
    val spacingXl: Dp,

    val buttonHeight: Dp,
    val progressHeight: Dp,
    val textFieldMinHeight: Dp,
    val questionButtonWidth: Dp,
    val questionButtonHeight: Dp,

    val iconSizeSmall: Dp,
    val iconSizeMedium: Dp,
    val iconSize: Dp,
    val iconEmptySize: Dp,
    val iconEmojiSize: TextUnit,
    val iconEmojiSizeLarge: TextUnit,

    val headerHeight: Dp,
    val containerWidth: Dp,
    val containerCameraHeight: Dp,
    val containerTextHeight: Dp,
    val emptyStateHeight: Dp,
    val shadowElevation: Dp,
    val cardPadding: Dp,
    val paddingHorizontal: Dp,
    val logoContainerSize: Dp,
    val logoSize: Dp,

    val borderThickness: Dp,

    val fontSizeHelper: TextUnit,
    val fontSizeButton: TextUnit,
    val fontSizeCardTitle: TextUnit,
    val fontSizeTopBarTitle: TextUnit,
    val fontSizeQuestionText: TextUnit,
    val fontSizeOptionText: TextUnit,
    val fontSizeExplanationTitle: TextUnit,
    val fontSizeExplanationText: TextUnit,
    val fontSizeTopBarTitleTest: TextUnit,

    val buttonCornerRadius: Dp,
    val buttonLargeHeight: Dp,
    val buttonShadowElevation: Dp,
    val iconSizeResult: Dp,
)

val LocalAppDimensions = staticCompositionLocalOf {
    AppDimensions(
        spacingXxxs = 4.dp, spacingXs = 8.dp, spacingSm = 12.dp,
        spacingMd = 16.dp, spacingLg = 24.dp, spacingXl = 32.dp,

        buttonHeight = 50.dp, progressHeight = 4.dp, textFieldMinHeight = 300.dp,
        questionButtonWidth = 64.dp, questionButtonHeight = 48.dp,

        iconSizeSmall = 20.dp, iconSizeMedium = 24.dp,
        iconSize = 80.dp, iconEmptySize = 96.dp,
        iconEmojiSize = 40.sp, iconEmojiSizeLarge = 48.sp,

        headerHeight = 96.dp, containerWidth = 345.dp,
        containerCameraHeight = 236.dp, containerTextHeight = 216.dp,
        emptyStateHeight = 200.dp, shadowElevation = 8.dp,
        cardPadding = 32.dp, paddingHorizontal = 24.dp,
        logoContainerSize = 80.dp, logoSize = 60.dp,

        borderThickness = 1.5.dp,

        fontSizeHelper = 12.sp, fontSizeButton = 16.sp,
        fontSizeCardTitle = 16.sp, fontSizeTopBarTitle = 20.sp,
        fontSizeQuestionText = 18.sp, fontSizeOptionText = 15.sp,
        fontSizeExplanationTitle = 14.sp, fontSizeExplanationText = 13.sp,
        fontSizeTopBarTitleTest = 18.sp,

        buttonCornerRadius = 14.dp,
        buttonLargeHeight = 80.dp,
        buttonShadowElevation = 4.dp,
        iconSizeResult = 20.dp,
    )
}

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
        chartSuccessBorder = Color.Unspecified,
        errorBorder = Color.Unspecified,
        chartErrorBorder = Color.Unspecified,
        explanationBackground = Color.Unspecified,
        explanationText = Color.Unspecified,
        progressBarTrack = Color.Unspecified,
        white = Color.Unspecified,
        cardSubtitleColor = Color.Unspecified,
        textOnCard = Color.Unspecified
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
        inputCorner = RoundedCornerShape(12.dp),
        buttonCorner = RoundedCornerShape(12.dp),
        cardCorner = RoundedCornerShape(24.dp),
        iconRound = CircleShape,
        buttonCornerSmall = RoundedCornerShape(14.dp),
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
        chartSuccessBorder = ChartSuccessBorder,
        chartErrorBorder = ChartErrorBorder,
        errorBorder = ErrorBorder,
        explanationBackground = ExplanationBackground,
        explanationText = ExplanationText,
        progressBarTrack = ProgressBarTrack,
        white = Color.White,
        cardSubtitleColor = Color(0xFF4A5565),
        textOnCard = Color(0xFF101828)
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
        iconRound = CircleShape,
        buttonCornerSmall = RoundedCornerShape(14.dp),
    )

    val dimensions = AppDimensions(
        spacingXxxs = 4.dp, spacingXs = 8.dp, spacingSm = 12.dp,
        spacingMd = 16.dp, spacingLg = 24.dp, spacingXl = 32.dp,

        buttonHeight = 50.dp, progressHeight = 4.dp, textFieldMinHeight = 300.dp,
        questionButtonWidth = 64.dp, questionButtonHeight = 48.dp,

        iconSizeSmall = 20.dp, iconSizeMedium = 24.dp,
        iconSize = 80.dp, iconEmptySize = 96.dp,
        iconEmojiSize = 40.sp, iconEmojiSizeLarge = 48.sp,

        headerHeight = 96.dp, containerWidth = 345.dp,
        containerCameraHeight = 236.dp, containerTextHeight = 216.dp,
        emptyStateHeight = 200.dp, shadowElevation = 8.dp,
        cardPadding = 32.dp, paddingHorizontal = 24.dp,
        logoContainerSize = 80.dp, logoSize = 60.dp,

        borderThickness = 1.5.dp,

        fontSizeHelper = 12.sp, fontSizeButton = 16.sp,
        fontSizeCardTitle = 16.sp, fontSizeTopBarTitle = 20.sp,
        fontSizeQuestionText = 18.sp, fontSizeOptionText = 15.sp,
        fontSizeExplanationTitle = 14.sp, fontSizeExplanationText = 13.sp,
        fontSizeTopBarTitleTest = 18.sp,

        buttonCornerRadius = 14.dp,
        buttonLargeHeight = 80.dp,
        buttonShadowElevation = 4.dp,
        iconSizeResult = 20.dp,
    )

    CompositionLocalProvider(
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