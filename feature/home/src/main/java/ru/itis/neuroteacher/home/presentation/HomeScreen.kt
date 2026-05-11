package ru.itis.neuroteacher.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import ru.itis.neuroteacher.home.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import ru.itis.neuroteacher.domain.model.RecentTestItem
import ru.itis.neuroteacher.home.navigation.HomeRouter
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun HomeScreen(router: HomeRouter, viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(bottom = AppTheme.dimensions.buttonHeight + AppTheme.dimensions.spacingLg)
            ) {
                item {
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
                }

                item { Spacer(Modifier.height(AppTheme.dimensions.spacingLg)) }

                item {
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
                                style = AppTheme.typography.cardTitle,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )

                            Spacer(Modifier.height(AppTheme.dimensions.spacingXs))

                            Text(
                                text = stringResource(id = R.string.home_camera_subtitle),
                                style = AppTheme.typography.cardSubtitle,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = AppTheme.dimensions.paddingHorizontal),
                                textAlign = TextAlign.Center

                            )
                        }
                    }
                }

                item { Spacer(Modifier.height(AppTheme.dimensions.spacingSm)) }

                item {
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
                                style = AppTheme.typography.cardTitle,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )

                            Spacer(Modifier.height(AppTheme.dimensions.spacingXs))

                            Text(
                                text = stringResource(id = R.string.home_text_subtitle),
                                style = AppTheme.typography.cardSubtitle,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = AppTheme.dimensions.paddingHorizontal),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                item { Spacer(Modifier.height(AppTheme.dimensions.spacingLg)) }

                item {
                    Row(
                        modifier = Modifier
                            .width(AppTheme.dimensions.containerWidth)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.home_recent_title),
                            style = AppTheme.typography.sectionTitle
                        )

                        Text(
                            text = stringResource(id = R.string.home_view_all),
                            fontSize = AppTheme.dimensions.viewAllButtonFontSize,
                            fontWeight = FontWeight.Medium,
                            color = AppTheme.colors.viewAllButtonColor,
                            modifier = Modifier.clickable { router.navigateToHistory() }
                        )
                    }
                }

                item { Spacer(Modifier.height(AppTheme.dimensions.spacingMd)) }

                if (uiState.recentTests.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .width(AppTheme.dimensions.containerWidth)
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
                } else {
                    uiState.recentTests.forEachIndexed { index, test ->
                        item(key = "${test.title}-${test.date}-$index") {
                            RecentTestCard(test = test)
                            if (index < uiState.recentTests.size - 1) {
                                Spacer(Modifier.height(AppTheme.dimensions.spacingMd))
                            }
                        }
                    }
                }

                item { Spacer(Modifier.height(AppTheme.dimensions.spacingLg)) }
            }
        }
    }
}

@Composable
private fun RecentTestCard(test: RecentTestItem) {
    Card(
        modifier = Modifier
            .width(AppTheme.dimensions.containerWidth)
            .height(AppTheme.dimensions.recentTestCardHeight)
            .shadow(
                elevation = AppTheme.dimensions.statsCardShadowElevation,
                ambientColor = AppTheme.colors.shadowColor,
                spotColor = AppTheme.colors.shadowColor,
            ),
        shape = RoundedCornerShape(AppTheme.dimensions.recentTestCardCornerRadius),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colors.cardBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.spacingMd)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(
                    text = test.title,
                    fontSize = AppTheme.dimensions.recentTestTitleFontSize,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.recentTestTitleColor,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXs))

                Text(
                    text = test.date,
                    fontSize = AppTheme.dimensions.recentTestDateFontSize,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.recentTestDateColor
                )
            }

            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(AppTheme.dimensions.recentTestStatusDotSize)
                        .background(
                            color = getScoreColor(test.scorePercentage),
                            shape = RoundedCornerShape(50)
                        )
                )
                Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingXxxs))
                Text(
                    text = "${test.scorePercentage}%",
                    fontSize = AppTheme.dimensions.recentTestScoreFontSize,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.recentTestScoreColor
                )
            }
        }
    }
}

@Composable
private fun getScoreColor(percentage: Int): Color {
    return if (percentage >= 70) {
        AppTheme.colors.statusSuccess
    } else {
        AppTheme.colors.statusError
    }
}