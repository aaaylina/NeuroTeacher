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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.itis.neuroteacher.home.navigation.HomeRouter
import ru.itis.neuroteacher.ui.theme.AppTheme



@Composable
fun HomeScreen(
    router: HomeRouter
) {
    Scaffold(
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = AppTheme.colors.backgroundGradientMain,
                        start = androidx.compose.ui.geometry.Offset(0f, 0f),
                        end = androidx.compose.ui.geometry.Offset(1000f, 1000f)
                    )
                )
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
                        style = AppTheme.typography.welcomeText
                    )
                }

                Spacer(Modifier.height(AppTheme.dimensions.spacingLg))

                Box(
                    modifier = Modifier
                        .width(AppTheme.dimensions.containerWidth)
                        .height(AppTheme.dimensions.containerCameraHeight)
                        .shadow(
                            elevation = AppTheme.dimensions.shadowElevation,
                            shape = AppTheme.shapes.cardCorner,
                            ambientColor = AppTheme.colors.shadowColor,
                            spotColor = AppTheme.colors.shadowColor
                        )
                        .clip(AppTheme.shapes.cardCorner)
                        .background(AppTheme.colors.cardBackground)
                        .clickable { router.navigateToCamera() },
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
                                .clip(AppTheme.shapes.iconRound)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(AppTheme.colors.iconGradientStart, AppTheme.colors.iconGradientEnd)
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_camera_icon),
                                contentDescription = stringResource(id = R.string.cd_camera_icon),
                                tint = AppTheme.colors.white,
                                modifier = Modifier.size(AppTheme.dimensions.iconSizeDefault)
                            )
                        }

                        Spacer(Modifier.height(AppTheme.dimensions.spacingMd))

                        Text(
                            text = stringResource(id = R.string.home_camera_title),
                            style = AppTheme.typography.cardTitle
                        )

                        Spacer(Modifier.height(AppTheme.dimensions.spacingXs))

                        Text(
                            text = stringResource(id = R.string.home_camera_subtitle),
                            style = AppTheme.typography.cardSubtitle,
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
                            shape = AppTheme.shapes.cardCorner,
                            ambientColor = AppTheme.colors.shadowColor,
                            spotColor = AppTheme.colors.shadowColor
                        )
                        .clip(AppTheme.shapes.cardCorner)
                        .background(AppTheme.colors.cardBackground)
                        .clickable { router.navigateToText() },
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
                                .clip(AppTheme.shapes.iconRound)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            AppTheme.colors.iconGradientSecondaryStart,
                                            AppTheme.colors.iconGradientSecondaryEnd
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_document_icon),
                                contentDescription = stringResource(id = R.string.cd_text_icon),
                                tint = AppTheme.colors.white,
                                modifier = Modifier.size(AppTheme.dimensions.iconSizeDefault)
                            )
                        }

                        Spacer(Modifier.height(AppTheme.dimensions.spacingMd))

                        Text(
                            text = stringResource(id = R.string.home_text_title),
                            style = AppTheme.typography.cardTitle
                        )

                        Spacer(Modifier.height(AppTheme.dimensions.spacingXs))

                        Text(
                            text = stringResource(id = R.string.home_text_subtitle),
                            style = AppTheme.typography.cardSubtitle,
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
                        style = AppTheme.typography.sectionTitle
                    )

                    Spacer(Modifier.height(AppTheme.dimensions.spacingMd))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(AppTheme.dimensions.emptyStateHeight)
                            .clip(AppTheme.shapes.cardCorner)
                            .background(AppTheme.colors.cardBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(AppTheme.dimensions.iconEmptySize)
                                    .clip(AppTheme.shapes.iconRound)
                                    .background(AppTheme.colors.backgroundLight),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_book_icon),
                                    contentDescription = stringResource(id = R.string.cd_book_icon),
                                    tint = AppTheme.colors.textSecondary,
                                    modifier = Modifier.size(AppTheme.dimensions.iconSizeLarge)
                                )
                            }

                            Spacer(Modifier.height(AppTheme.dimensions.spacingMd))

                            Text(
                                text = stringResource(id = R.string.home_empty_state),
                                style = AppTheme.typography.emptyStateText
                            )
                        }
                    }

                }

                Spacer(Modifier.height(AppTheme.dimensions.spacingLg))
            }
        }
    }
}