package ru.itis.neuroteacher.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import ru.itis.neuroteacher.home.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ru.itis.neuroteacher.ui.theme.AppTheme


@Composable
fun HomeScreen(
    onCameraClick: () -> Unit,
    onTextClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onProfileClick: () -> Unit,
) {
    val colors = AppTheme.colors
    val typography = AppTheme.typography
    val shapes = AppTheme.shapes

    val mainGradient = Brush.linearGradient(
        colors = colors.backgroundGradientMain,
        start = androidx.compose.ui.geometry.Offset(0f, 0f),
        end = androidx.compose.ui.geometry.Offset(1000f, 1000f)
    )
    Scaffold(
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(mainGradient)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(AppTheme.dimensions.headerHeight)
                        .padding(
                            horizontal = AppTheme.dimensions.paddingHorizontal,
                            vertical = AppTheme.dimensions.paddingHorizontal
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = stringResource(id = R.string.home_welcome),
                        style = typography.welcomeText
                    )
                }

                Spacer(Modifier.height(AppTheme.dimensions.spacingLg))

                Box(
                    modifier = Modifier
                        .width(AppTheme.dimensions.containerWidth)
                        .height(AppTheme.dimensions.containerCameraHeight)
                        .shadow(
                            elevation = AppTheme.dimensions.shadowElevation,
                            shape = shapes.cardCorner,
                            ambientColor = colors.shadowColor,
                            spotColor = colors.shadowColor
                        )
                        .clip(shapes.cardCorner)
                        .background(colors.cardBackground)
                        .clickable(onClick = onCameraClick),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(AppTheme.dimensions.iconSize)
                                .clip(shapes.iconRound)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(colors.iconGradientStart, colors.iconGradientEnd)
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "📷", //пока так сделала хардкодом эмодзи, потом заменю их на иконки
                                fontSize = AppTheme.dimensions.iconEmojiSize
                            )
                        }

                        Spacer(Modifier.height(AppTheme.dimensions.spacingMd))

                        Text(
                            text = stringResource(id = R.string.home_camera_title),
                            style = typography.cardTitle
                        )

                        Spacer(Modifier.height(AppTheme.dimensions.spacingXs))

                        Text(
                            text = stringResource(id = R.string.home_camera_subtitle),
                            style = typography.cardSubtitle,
                            modifier = Modifier.padding(horizontal = AppTheme.dimensions.paddingHorizontal)
                        )
                    }
                }

                Spacer(Modifier.height(AppTheme.dimensions.spacingSm))

                Box(
                    modifier = Modifier
                        .width(AppTheme.dimensions.containerWidth)
                        .height(AppTheme.dimensions.containerTextHeight)
                        .shadow(
                            elevation = AppTheme.dimensions.shadowElevation,
                            shape = shapes.cardCorner,
                            ambientColor = colors.shadowColor,
                            spotColor = colors.shadowColor
                        )
                        .clip(shapes.cardCorner)
                        .background(colors.cardBackground)
                        .clickable(onClick = onTextClick),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(AppTheme.dimensions.iconSize)
                                .clip(shapes.iconRound)
                                .background(colors.backgroundLight),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "📝", // тут аналогично
                                fontSize = AppTheme.dimensions.iconEmojiSize
                            )
                        }

                        Spacer(Modifier.height(AppTheme.dimensions.spacingMd))

                        Text(
                            text = stringResource(id = R.string.home_text_title),
                            style = typography.cardTitle
                        )

                        Spacer(Modifier.height(AppTheme.dimensions.spacingXs))

                        Text(
                            text = stringResource(id = R.string.home_text_subtitle),
                            style = typography.cardSubtitle,
                            modifier = Modifier.padding(horizontal = AppTheme.dimensions.paddingHorizontal)
                        )
                    }
                }

                Spacer(Modifier.height(AppTheme.dimensions.spacingLg))

                Column(
                    modifier = Modifier
                        .width(AppTheme.dimensions.containerWidth)
                        .padding(horizontal = AppTheme.dimensions.paddingHorizontal)
                ) {
                    Text(
                        text = stringResource(id = R.string.home_recent_title),
                        style = typography.sectionTitle
                    )

                    Spacer(Modifier.height(AppTheme.dimensions.spacingMd))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(AppTheme.dimensions.emptyStateHeight)
                            .clip(shapes.cardCorner)
                            .background(colors.cardBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(AppTheme.dimensions.iconEmptySize)
                                    .clip(shapes.iconRound)
                                    .background(colors.backgroundLight),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "📚", // тут аналогично
                                    fontSize = AppTheme.dimensions.iconEmojiSizeLarge
                                )
                            }

                            Spacer(Modifier.height(AppTheme.dimensions.spacingMd))

                            Text(
                                text = stringResource(id = R.string.home_empty_state),
                                style = typography.emptyStateText
                            )
                        }
                    }

                }

                Spacer(Modifier.height(AppTheme.dimensions.spacingLg))
            }
        }
    }
}