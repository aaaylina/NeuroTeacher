package ru.itis.neuroteacher.ui.theme.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itis.neuroteacher.ui.R

enum class BottomNavItem(
    val route: String,
    val icon: Int,
    val labelResId: Int
) {
    HOME(
        route = "ru.itis.neuroteacher.home.navigation.model.HomeRoute",
        icon = R.drawable.ic_home,
        labelResId = R.string.bottom_home
    ),
    HISTORY(
        route = "ru.itis.neuroteacher.feature.history.navigation.HistoryRoute",
        icon = R.drawable.ic_history,
        labelResId = R.string.bottom_history
    ),
    PROFILE(
        route = "ru.itis.neuroteacher.feature.profile.navigation.ProfileRoute",
        icon = R.drawable.ic_profile,
        labelResId = R.string.bottom_profile
    )
}

@Composable
fun AppBottomBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        BottomNavItem.values().forEach { item ->
            val selected = currentRoute.endsWith(item.route.substringAfterLast('.'))

            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(item.labelResId),
                        tint = if (selected)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.labelResId),
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = if (selected)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            )
        }
    }
}