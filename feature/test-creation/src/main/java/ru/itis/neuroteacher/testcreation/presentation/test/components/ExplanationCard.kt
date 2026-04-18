package ru.itis.neuroteacher.testcreation.presentation.test.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.itis.neuroteacher.ui.theme.AppTheme
import ru.itis.neuroteacher.testcreation.R

@Composable
internal fun ExplanationCard(explanation: String?) {
    if (explanation.isNullOrEmpty()) return

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = AppTheme.dimensions.spacingLg,
                vertical = AppTheme.dimensions.spacingSm
            ),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.explanationBackground
        ),
        shape = AppTheme.shapes.cardCorner
    ) {
        Row(
            modifier = Modifier.padding(AppTheme.dimensions.spacingLg),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = stringResource(R.string.cd_info),
                tint = AppTheme.colors.primary,
                modifier = Modifier
                    .size(AppTheme.dimensions.iconSizeSmall)
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(AppTheme.dimensions.spacingMd))

            Column {
                Text(
                    text = stringResource(R.string.explanation_label),
                    style = AppTheme.typography.cardTitle.copy(
                        fontSize = AppTheme.dimensions.explanationTitleSize
                    ),
                    color = AppTheme.colors.textPrimary
                )

                Spacer(modifier = Modifier.height(AppTheme.dimensions.spacingXxxs))

                Text(
                    text = explanation,
                    style = AppTheme.typography.cardSubtitle.copy(
                        fontSize = AppTheme.dimensions.explanationTextSize
                    ),
                    color = AppTheme.colors.explanationText
                )
            }
        }
    }
}