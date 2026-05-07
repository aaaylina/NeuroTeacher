package ru.itis.neuroteacher.testcreation.presentation.camera

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.collectLatest
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.testcreation.navigation.TestCreationRouter
import ru.itis.neuroteacher.testcreation.utils.constants.TestGenerationConstants
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun PhotoPreviewScreen(
    router: TestCreationRouter,
    viewModel: PhotoPreviewViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val typography = AppTheme.typography
    val colors = AppTheme.colors
    val dimensions = AppTheme.dimensions
    val shapes = AppTheme.shapes

    LaunchedEffect(uiState.error) {
        uiState.error?.let { errorMessage ->
            snackbarHostState.showSnackbar(
                message = errorMessage,
                duration = SnackbarDuration.Long
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collectLatest { event ->
            when (event) {
                is PhotoPreviewNavigationEvent.NavigateToTest -> router.navigateToTest(event.testId)
                PhotoPreviewNavigationEvent.NavigateBackToCamera -> router.navigateUp()
            }
        }
    }

    val selectedGradient = Brush.linearGradient(
        colors = listOf(colors.photoDemoGradientStart, colors.photoDemoGradientEnd)
    )

    val unselectedGradient = Brush.linearGradient(
        colors = listOf(colors.photoDemoButtonBgUnselected, colors.photoDemoButtonBgUnselected)
    )

    Scaffold(
        containerColor = colors.photoDemoBgBlack,
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(top = dimensions.spacingLg, start = dimensions.spacingLg, end = dimensions.spacingLg)
                    .fillMaxWidth()
                    .height(dimensions.photoHeight)
                    .clip(shapes.photoImageShape)
                    .background(colors.photoDemoPhotoGray),
                contentAlignment = Alignment.Center
            ) {
                if (uiState.imageUri.isNotEmpty()) {
                    AsyncImage(
                        model = uiState.imageUri,
                        contentDescription = stringResource(R.string.photo_demo_captured_photo_desc),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = stringResource(R.string.photo_demo_title),
                            color = colors.photoDemoTextDark,
                            style = typography.cardTitle,
                            fontSize = dimensions.fontSizePhotoTitle,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = stringResource(R.string.photo_demo_subtitle),
                            color = colors.photoDemoTextSecondary,
                            fontSize = dimensions.fontSizePhotoSubtitle
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(dimensions.spacingLg))

            Card(
                modifier = Modifier
                    .padding(horizontal = dimensions.spacingLg)
                    .fillMaxWidth(),
                shape = shapes.photoCardShape,
                colors = CardDefaults.cardColors(containerColor = colors.photoDemoCardBg)
            ) {
                Column(modifier = Modifier.padding(dimensions.photoCardPadding)) {
                    Text(
                        text = stringResource(R.string.text_input_question_count_label),
                        color = Color.White,
                        fontSize = dimensions.fontSizeLabel,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(dimensions.spacingMd))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensions.photoButtonsSpacing)
                    ) {
                        TestGenerationConstants.QUESTION_COUNT_OPTIONS.forEach { count ->
                            val isSelected = uiState.selectedQuestionCount == count

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(dimensions.photoQuestionButtonHeight)
                                    .clip(shapes.photoQuestionButtonShape)
                                    .background(
                                        if (isSelected) selectedGradient else unselectedGradient
                                    )
                                    .clickable { viewModel.updateQuestionCount(count) },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = count.toString(),
                                    color = Color.White,
                                    fontSize = dimensions.fontSizeButtonText,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensions.spacingLg, vertical = dimensions.spacingXl),
                horizontalArrangement = Arrangement.spacedBy(dimensions.spacingMd)
            ) {
                Button(
                    onClick = { viewModel.onRetakeClick() },
                    modifier = Modifier
                        .weight(1f)
                        .height(dimensions.photoMainButtonHeight),
                    colors = ButtonDefaults.buttonColors(containerColor = colors.photoDemoTextDark),
                    shape = shapes.photoButtonShape,
                    enabled = !uiState.isGeneratingTest
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(R.string.photo_demo_retake_desc),
                        modifier = Modifier.size(dimensions.iconSizeSmall),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(dimensions.spacingSm))
                    Text(text = stringResource(R.string.photo_demo_retake), color = Color.White)
                }

                Box(
                    modifier = Modifier
                        .weight(1.1f)
                        .height(dimensions.photoMainButtonHeight)
                        .clip(shapes.photoButtonShape)
                        .background(selectedGradient)
                        .clickable(enabled = !uiState.isGeneratingTest) {
                            viewModel.generateTestFromText()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (uiState.isGeneratingTest) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(dimensions.iconSizeMedium),
                            strokeWidth = dimensions.strokeWidthSmall
                        )
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = stringResource(R.string.photo_demo_generate_desc),
                                tint = Color.White,
                                modifier = Modifier.size(dimensions.iconSizeSmall)
                            )
                            Spacer(modifier = Modifier.width(dimensions.spacingSm))
                            Text(
                                text = stringResource(R.string.photo_demo_continue),
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = dimensions.fontSizeButtonText
                            )
                        }
                    }
                }
            }
        }
    }
}