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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.dimensionResource
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
                        .height(dimensionResource(id = R.dimen.header_height))
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.padding_horizontal),
                            vertical = dimensionResource(id = R.dimen.padding_horizontal)
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = stringResource(id = R.string.home_welcome),
                        style = typography.welcomeText
                    )
                }

                Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_lg)))


                Box(
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.container_width))
                        .height(dimensionResource(id = R.dimen.container_camera_height))
                        .shadow(
                            elevation = dimensionResource(id = R.dimen.shadow_elevation),
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
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_card))
                    ) {
                        Box(
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.icon_size))
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
                                fontSize = dimensionResource(id = R.dimen.icon_emoji_size).value.sp
                            )
                        }

                        Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_md)))

                        Text(
                            text = stringResource(id = R.string.home_camera_title),
                            style = typography.cardTitle
                        )

                        Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_xs)))

                        Text(
                            text = stringResource(id = R.string.home_camera_subtitle),
                            style = typography.cardSubtitle,
                            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal))
                        )
                    }
                }

                Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_sm)))

                Box(
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.container_width))
                        .height(dimensionResource(id = R.dimen.container_text_height))
                        .shadow(
                            elevation = dimensionResource(id = R.dimen.shadow_elevation),
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
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_card))
                    ) {
                        Box(
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.icon_size))
                                .clip(shapes.iconRound)
                                .background(colors.backgroundLight),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "📝", // тут аналогично
                                fontSize = dimensionResource(id = R.dimen.icon_emoji_size).value.sp
                            )
                        }

                        Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_md)))

                        Text(
                            text = stringResource(id = R.string.home_text_title),
                            style = typography.cardTitle
                        )

                        Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_xs)))

                        Text(
                            text = stringResource(id = R.string.home_text_subtitle),
                            style = typography.cardSubtitle,
                            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal))
                        )
                    }
                }

                Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_lg)))

                Column(
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.container_width))
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_horizontal))
                ) {
                    Text(
                        text = stringResource(id = R.string.home_recent_title),
                        style = typography.sectionTitle
                    )

                    Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_md)))


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.empty_state_height))
                            .clip(shapes.cardCorner)
                            .background(colors.cardBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(dimensionResource(id = R.dimen.icon_empty_size))
                                    .clip(shapes.iconRound)
                                    .background(colors.backgroundLight),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "📚", // тут аналогично
                                    fontSize = dimensionResource(id = R.dimen.icon_emoji_size_large).value.sp
                                )
                            }

                            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_md)))

                            Text(
                                text = stringResource(id = R.string.home_empty_state),
                                style = typography.emptyStateText
                            )
                        }
                    }

                }

                Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_lg)))
            }
        }
    }
}