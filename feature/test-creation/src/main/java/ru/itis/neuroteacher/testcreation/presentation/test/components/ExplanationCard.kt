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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import ru.itis.neuroteacher.testcreation.R
import ru.itis.neuroteacher.ui.theme.AppTheme

@Composable
internal fun ExplanationCard(explanation: String?) {
    if (explanation.isNullOrEmpty()) return

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.spacing_lg),
                vertical = dimensionResource(R.dimen.spacing_sm)
            ),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.explanationBackground
        ),
        shape = AppTheme.shapes.cardCorner
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.spacing_lg)),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = stringResource(R.string.cd_info),
                tint = AppTheme.colors.primary,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.icon_size_small))
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacing_md)))

            Column {
                Text(
                    text = stringResource(R.string.explanation_label),
                    style = AppTheme.typography.cardTitle.copy(
                        fontSize = dimensionResource(R.dimen.font_size_explanation_title).value.sp
                    ),
                    color = AppTheme.colors.textPrimary
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_xxxs)))

                Text(
                    text = explanation,
                    style = AppTheme.typography.cardSubtitle.copy(
                        fontSize = dimensionResource(R.dimen.font_size_explanation_text).value.sp
                    ),
                    color = AppTheme.colors.explanationText
                )
            }
        }
    }
}