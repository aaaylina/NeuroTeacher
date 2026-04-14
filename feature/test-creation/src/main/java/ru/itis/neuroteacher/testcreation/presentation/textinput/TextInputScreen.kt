package ru.itis.neuroteacher.testcreation.presentation.textinput

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import ru.itis.neuroteacher.testcreation.presentation.textinput.components.QuestionCountButton
import ru.itis.neuroteacher.testcreation.presentation.textinput.components.TextInputTopBar
import ru.itis.neuroteacher.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputScreen(
    onNavigateBack: () -> Unit,
    onNavigateToTest: (String, String) -> Unit,
    viewModel: TextInputViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val navEvent by viewModel.navigationEvents.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var text by remember { mutableStateOf("") }
    var selectedQuestions by remember { mutableStateOf(5) }

    val questionOptions = listOf(5, 10, 15, 20)
    val maxCharacters = 5000
    val isGenerateEnabled = text.length >= 50 && !uiState.isLoading

    LaunchedEffect(navEvent) {
        when (val event = navEvent) {
            is TextInputNavigationEvent.NavigateToTest -> {
                onNavigateToTest(event.title, event.questionsJson)
                viewModel.onEventConsumed()
            }
            is TextInputNavigationEvent.ShowError -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = uiState.error ?: "Ошибка генерации",
                        withDismissAction = true
                    )
                }
                viewModel.onEventConsumed()
            }
            null -> {}
        }
    }

    Scaffold(
        topBar = {
            TextInputTopBar(onNavigateBack = onNavigateBack)
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color(0xFFF5F5F7)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    TextField(
                        value = text,
                        onValueChange = { if (it.length <= maxCharacters) text = it },
                        placeholder = {
                            Text(
                                text = "Вставьте текст лекции, конспекта или учебника...",
                                color = Color.Gray
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 300.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        maxLines = Int.MAX_VALUE,
                        enabled = !uiState.isLoading
                    )

                    Text(
                        text = "Минимальная рекомендуемая длина — 50 символов",
                        style = AppTheme.typography.placeholder.copy(fontSize = 12.sp),
                        color = Color.Gray
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Количество вопросов",
                        style = AppTheme.typography.cardTitle.copy(fontSize = 14.sp),
                        color = Color.Black
                    )

                    Spacer(Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        questionOptions.forEach { count ->
                            QuestionCountButton(
                                count = count,
                                isSelected = selectedQuestions == count,
                                onClick = { selectedQuestions = count }
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "${text.length} / $maxCharacters символов",
                style = AppTheme.typography.placeholder.copy(fontSize = 12.sp),
                color = if (text.length < 50) Color.Gray else AppTheme.colors.primary
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { /* TODO: вставка из буфера */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(12.dp),
                    enabled = !uiState.isLoading
                ) {
                    Icon(
                        imageVector = Icons.Filled.ContentPaste,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Вставить")
                }

                Button(
                    onClick = {
                        viewModel.generateTest(text, selectedQuestions)
                    },
                    modifier = Modifier.weight(1f),
                    enabled = isGenerateEnabled,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isGenerateEnabled) AppTheme.colors.primary else Color.LightGray,
                        disabledContainerColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Генерация...")
                    } else {
                        Icon(
                            imageVector = Icons.Filled.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Сгенерировать")
                    }
                }
            }
        }
    }
}