package ru.itis.neuroteacher.ui.theme.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.itis.neuroteacher.ui.R
import ru.itis.neuroteacher.ui.theme.AppTheme

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
    Surface(
        color = AppTheme.colors.cardBackground,
        tonalElevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(AppTheme.dimensions.bottomBarHeight)
            .shadow(
                elevation = 2.dp,
                ambientColor = AppTheme.colors.shadowColor,
                spotColor = AppTheme.colors.shadowColor
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem.values().forEach { item ->
                val isSelected = currentRoute.endsWith(item.route.substringAfterLast('.'))
                val tintColor = if (isSelected) AppTheme.colors.bottomBarSelectedColor else AppTheme.colors.bottomBarUnselectedColor

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(vertical = AppTheme.dimensions.bottomBarVerticalPadding)
                        .clickable { onNavigate(item.route) }
                ) {
                    Box(
                        modifier = Modifier.size(AppTheme.dimensions.bottomBarIconContainerSize),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = stringResource(item.labelResId),
                            tint = tintColor,
                            modifier = Modifier.size(AppTheme.dimensions.bottomBarIconSize)
                        )
                    }

                    Spacer(modifier = Modifier.height(AppTheme.dimensions.bottomBarIconTextGap))

                    Text(
                        text = stringResource(item.labelResId),
                        fontSize = AppTheme.dimensions.bottomBarTextSize,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 16.sp,
                        letterSpacing = 0.sp,
                        color = tintColor,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}