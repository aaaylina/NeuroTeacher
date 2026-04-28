package ru.itis.neuroteacher.testcreation.presentation.textinput

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.testcreation.navigation.TestCreationRouter
import ru.itis.neuroteacher.testcreation.presentation.textinput.components.QuestionCountButton
import ru.itis.neuroteacher.testcreation.presentation.textinput.components.TextInputTopBar
import ru.itis.neuroteacher.testcreation.utils.constants.TestGenerationConstants
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
internal fun TextInputScreen(
    router: TestCreationRouter,
    testCache: ru.itis.neuroteacher.testcreation.data.TestCache,
    viewModel: TextInputViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val navEvent by viewModel.navigationEvents.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var text by remember { mutableStateOf("") }
    var selectedQuestions by remember { mutableStateOf(TestGenerationConstants.QUESTION_COUNT_OPTIONS.first()) }

    val isTextValid = text.length in TestGenerationConstants.MIN_TEXT_LENGTH..TestGenerationConstants.MAX_TEXT_LENGTH
    val isGenerateEnabled = !uiState.isLoading && isTextValid

    val errorGenerationText = stringResource(R.string.common_error_generation)

    LaunchedEffect(navEvent) {
        when (val event = navEvent) {
            is TextInputNavigationEvent.NavigateToTest -> {
                val testId = testCache.save(event.test)
                router.navigateToTest(testId)
                viewModel.onEventConsumed()
            }
            is TextInputNavigationEvent.ShowError -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = uiState.error ?: errorGenerationText,
                        withDismissAction = true
                    )
                }
                viewModel.onEventConsumed()
            }
            null -> {}
        }
    }

    Scaffold(
        topBar = { TextInputTopBar(onNavigateBack = { router.navigateUp() }) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = AppTheme.colors.backgroundLight
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(AppTheme.dimensions.spacingLg),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AppTheme.colors.cardBackground
                ),
                shape = AppTheme.shapes.cardCorner
            ) {
                Column(
                    modifier = Modifier.padding(AppTheme.dimensions.spacingLg)
                ) {
                    TextField(
                        value = text,
                        onValueChange = {
                            if (it.length <= TestGenerationConstants.MAX_TEXT_LENGTH) text = it
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.text_input_placeholder),
                                color = AppTheme.colors.textHint
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = AppTheme.dimensions.textFieldMinHeight),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = androidx.compose.ui.graphics.Color.Transparent,
                            unfocusedContainerColor = androidx.compose.ui.graphics.Color.Transparent,
                            disabledContainerColor = androidx.compose.ui.graphics.Color.Transparent,
                            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        maxLines = Int.MAX_VALUE,
                        enabled = !uiState.isLoading
                    )

                    Text(
                        text = stringResource(R.string.text_input_min_length_hint, TestGenerationConstants.MIN_TEXT_LENGTH),
                        style = AppTheme.typography.placeholder.copy(
                            fontSize = AppTheme.dimensions.fontSizeHelper
                        ),
                        color = AppTheme.colors.textHint
                    )
                }
            }

            Spacer(Modifier.height(AppTheme.dimensions.spacingLg))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AppTheme.colors.cardBackground
                ),
                shape = AppTheme.shapes.cardCorner
            ) {
                Column(
                    modifier = Modifier.padding(AppTheme.dimensions.spacingLg)
                ) {
                    Text(
                        text = stringResource(R.string.text_input_question_count_label),
                        style = AppTheme.typography.cardTitle,
                        color = AppTheme.colors.textPrimary
                    )

                    Spacer(Modifier.height(AppTheme.dimensions.spacingMd))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.spacingSm)
                    ) {
                        TestGenerationConstants.QUESTION_COUNT_OPTIONS.forEach { count ->
                            QuestionCountButton(
                                count = count,
                                isSelected = selectedQuestions == count,
                                onClick = { selectedQuestions = count }
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(AppTheme.dimensions.spacingLg))

            Text(
                text = stringResource(R.string.text_input_char_counter, text.length, TestGenerationConstants.MAX_TEXT_LENGTH),
                style = AppTheme.typography.placeholder.copy(
                    fontSize = AppTheme.dimensions.fontSizeHelper
                ),
                color = if (text.length < TestGenerationConstants.MIN_TEXT_LENGTH) {
                    AppTheme.colors.textHint
                } else {
                    AppTheme.colors.primary
                }
            )

            Spacer(Modifier.height(AppTheme.dimensions.spacingLg))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.spacingMd),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { /* TODO: вставка из буфера */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = AppTheme.colors.cardBackground,
                        contentColor = AppTheme.colors.textPrimary
                    ),
                    border = BorderStroke(
                        1.dp,
                        AppTheme.colors.borderDefault
                    ),
                    shape = AppTheme.shapes.buttonCorner,
                    enabled = !uiState.isLoading
                ) {
                    Icon(
                        imageVector = Icons.Filled.ContentPaste,
                        contentDescription = stringResource(R.string.cd_paste),
                        modifier = Modifier.size(AppTheme.dimensions.iconSizeSmall)
                    )
                    Spacer(Modifier.width(AppTheme.dimensions.spacingSm))
                    Text(
                        stringResource(R.string.common_paste),
                        style = AppTheme.typography.button.copy(color = AppTheme.colors.textPrimary)
                    )
                }

                Button(
                    onClick = {
                        if (isTextValid) {
                            viewModel.generateTest(text, selectedQuestions)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    enabled = isGenerateEnabled,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isGenerateEnabled) AppTheme.colors.primary else AppTheme.colors.disabled,
                        disabledContainerColor = AppTheme.colors.disabled
                    ),
                    shape = AppTheme.shapes.buttonCorner
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(AppTheme.dimensions.iconSizeSmall),
                            color = AppTheme.colors.textPrimary.copy(alpha = 1f),
                            strokeWidth = 2.dp
                        )
                        Spacer(Modifier.width(AppTheme.dimensions.spacingSm))
                        Text(stringResource(R.string.common_generating), style = AppTheme.typography.button)
                    } else {
                        Icon(
                            imageVector = Icons.Filled.AutoAwesome,
                            contentDescription = stringResource(R.string.cd_generate),
                            modifier = Modifier.size(AppTheme.dimensions.iconSizeSmall)
                        )
                        Spacer(Modifier.width(AppTheme.dimensions.spacingSm))
                        Text(stringResource(R.string.common_generate), style = AppTheme.typography.button)
                    }
                }
            }
        }
    }
}