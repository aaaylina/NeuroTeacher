package ru.itis.neuroteacher.auth.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import ru.itis.neuroteacher.auth.R

@Composable
fun AuthToolbar(
    primaryText: String,
    secondaryText: String,
    onSecondaryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.spacing_md)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = primaryText,
            fontSize = 14.sp,
            color = colorResource(id = R.color.text_secondary)
        )
        Text(
            text = " $secondaryText",
            fontSize = 14.sp,
            color = colorResource(id = R.color.primary),
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier.clickable { onSecondaryClick() }
        )
    }
}