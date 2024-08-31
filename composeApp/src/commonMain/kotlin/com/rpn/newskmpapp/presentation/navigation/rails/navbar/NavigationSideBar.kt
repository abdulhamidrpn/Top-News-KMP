package com.rpn.newskmpapp.presentation.navigation.rails.navbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.rpn.newskmpapp.presentation.navigation.rails.items.NavigationItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NavigationSideBar(
    items: List<NavigationItem>,
    currentRoute: String? = null,
    onItemClick: (NavigationItem) -> Unit,
) {
    var isTitleVisible by remember { mutableStateOf(true) }
    NavigationRail(
        modifier = Modifier.fillMaxHeight().alpha(0.95F),
        containerColor = MaterialTheme.colorScheme.surface,
        header = {
            IconButton(onClick = {
                isTitleVisible = !isTitleVisible
            }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }

        }
    ) {
        items.forEach { bottomNavigationItem ->

            val isSelected = bottomNavigationItem.route == currentRoute
            NavigationRailItem(
                modifier = Modifier.padding(vertical = 12.dp),
                icon = {
                    NavigationIcon(
                        item = bottomNavigationItem, selected = isSelected
                    )
                },
                label = {
                    AnimatedVisibility(isTitleVisible) {
                        Text(
                            text = stringResource(bottomNavigationItem.label),
                            style =
                            if (isSelected) MaterialTheme.typography.labelLarge
                            else MaterialTheme.typography.labelMedium,
                            maxLines = 1,
                            softWrap = false,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                        )
                    }
                },
                selected = isSelected,
                onClick = { onItemClick(bottomNavigationItem) }
            )
        }
    }
}


@Composable
fun NavigationIcon(
    item: NavigationItem, selected: Boolean,
) {
    BadgedBox(badge = {
        if (item.badgeCount != null) {
            Badge {
                Text(text = item.badgeCount.toString())
            }
        } else if (item.hasNews) {
            Badge()
        }
    }) {
        Icon(
            painter = painterResource(
                if (selected) item.selectedIcon
                else item.unselectedIcon
            ),
            contentDescription = stringResource(item.label),
            tint = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onBackground
            }
        )
    }
}