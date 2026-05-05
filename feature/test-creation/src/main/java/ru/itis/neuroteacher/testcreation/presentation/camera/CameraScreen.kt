package ru.itis.neuroteacher.testcreation.presentation.camera

import android.Manifest
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.testcreation.data.TestCache
import ru.itis.neuroteacher.testcreation.navigation.TestCreationRouter
import ru.itis.neuroteacher.ui.theme.AppTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun CameraScreen(
    router: TestCreationRouter,
    testCache: TestCache,
    viewModel: CameraViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val colors = AppTheme.colors
    val dimensions = AppTheme.dimensions
    val typography = AppTheme.typography
    val shapes = AppTheme.shapes

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            context.contentResolver.openInputStream(it).use { inputStream ->
                val bitmap = BitmapFactory.decodeStream(inputStream)
                viewModel.processSelectedImage(bitmap)
            }
        }
    }

    LaunchedEffect(Unit) {
        cameraPermissionState.launchPermissionRequest()
    }

    LaunchedEffect(cameraPermissionState.status.isGranted) {
        if (cameraPermissionState.status.isGranted) {
            viewModel.startCamera(lifecycleOwner)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navigationEvents.collect { event ->
            when (event) {
                is CameraNavigationEvent.NavigateToPhotoDemo -> {
                    router.navigateToPhotoDemo(event.imageUri, event.recognizedText)
                    viewModel.onEventConsumed()
                }
                CameraNavigationEvent.ShowError -> {
                    viewModel.onEventConsumed()
                }
                null -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.cardBackground)
    ) {
        when {
            !cameraPermissionState.status.isGranted -> {
                Text(
                    text = stringResource(R.string.camera_permission_denied),
                    modifier = Modifier.align(Alignment.Center),
                    style = typography.subtitle.copy(color = colors.textHint)
                )
            }
            uiState.isLoading -> {
                androidx.compose.material3.CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = colors.primary
                )
            }
            uiState.error != null -> {
                Text(
                    text = stringResource(R.string.camera_error, uiState.error ?: stringResource(R.string.camera_unknown_error)),
                    modifier = Modifier.align(Alignment.Center),
                    style = typography.error
                )
            }
            else -> {
                AndroidView(
                    factory = { ctx ->
                        PreviewView(ctx).apply {
                            viewModel.setupPreview(this, lifecycleOwner)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth()
                        .height(dimensions.headerHeight)
                        .padding(start = dimensions.cameraTopPaddingStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { router.navigateUp() },
                        modifier = Modifier
                            .size(dimensions.cameraBackButtonSize)
                            .clip(shapes.iconRound)
                            .background(colors.cameraBackButtonBackground)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = stringResource(R.string.camera_back_description),
                            tint = colors.white,
                            modifier = Modifier.size(dimensions.cameraBackIconSize)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(bottom = dimensions.spacingLg)
                        .offset(x = dimensions.cameraOffsetX),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { galleryLauncher.launch("image/*") },
                        modifier = Modifier
                            .size(dimensions.cameraButtonSize)
                            .clip(CircleShape)
                            .background(colors.cameraButtonBackground)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_gallery),
                            contentDescription = stringResource(R.string.camera_gallery_description),
                            tint = colors.white,
                            modifier = Modifier.size(dimensions.cameraIconSize)
                        )
                    }

                    Spacer(modifier = Modifier.width(dimensions.cameraButtonsGap))

                    IconButton(
                        onClick = { viewModel.capturePhoto() },
                        modifier = Modifier
                            .size(dimensions.cameraButtonSize + dimensions.cameraButtonSizeDelta)
                            .clip(CircleShape)
                            .shadow(
                                elevation = dimensions.shadowElevation,
                                shape = CircleShape,
                                clip = false
                            )
                            .background(colors.white.copy(alpha = 0.9f))
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_camera),
                            contentDescription = stringResource(R.string.camera_capture_description),
                            tint = colors.cameraButtonBackground,
                            modifier = Modifier.size(dimensions.cameraIconSize + dimensions.cameraIconSizeDelta)
                        )
                    }
                }
            }
        }
    }
}