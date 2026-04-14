package ru.itis.neuroteacher.testcreation.presentation.test.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.itis.neuroteacher.testcreation.domain.model.Question
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
fun QuestionCard(
    question: Question,
    selectedOptionIndex: Int?,
    onOptionSelected: (Int) -> Unit,
    isEnabled: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = question.text,
                style = AppTheme.typography.cardTitle.copy(fontSize = 18.sp),
                color = Color.Black,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            question.options.forEachIndexed { index, option ->
                val isSelected = selectedOptionIndex == index
                val isCorrect = index == question.correctIndex
                val showResult = isSelected && isEnabled.not()

                val backgroundColor = when {
                    showResult && isCorrect -> Color(0xFFD4EDD4)
                    showResult && !isCorrect -> Color(0xFFF8D7DA)
                    isSelected -> Color(0xFFF0F2F5)
                    else -> Color.White
                }

                val borderColor = when {
                    showResult && isCorrect -> Color(0xFF28A745)
                    showResult && !isCorrect -> Color(0xFFDC3545)
                    isSelected -> AppTheme.colors.primary
                    else -> Color(0xFFDEE2E6)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(backgroundColor)
                        .border(
                            width = 1.5.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable(enabled = isEnabled) { onOptionSelected(index) }
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = option,
                            style = AppTheme.typography.cardSubtitle.copy(fontSize = 15.sp),
                            color = if (showResult && !isCorrect) Color.Gray else Color.Black,
                            modifier = Modifier.weight(1f)
                        )

                        if (showResult && isCorrect) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                contentDescription = "Correct",
                                tint = Color(0xFF28A745),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}