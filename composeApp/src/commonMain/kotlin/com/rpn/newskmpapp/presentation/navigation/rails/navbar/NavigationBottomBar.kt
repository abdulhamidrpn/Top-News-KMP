package com.rpn.newskmpapp.presentation.navigation.rails.navbar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rpn.newskmpapp.presentation.navigation.rails.items.NavigationItem
import org.jetbrains.compose.resources.stringResource

@Composable
fun NavigationBottomBar(
    items: List<NavigationItem>,
    currentRoute: String? = null,
    onItemClick: (NavigationItem) -> Unit,
) {
    NavigationBar(modifier = Modifier.fillMaxSize()) {
        items.forEach { bottomNavigationItem ->
            val isSelected = bottomNavigationItem.route == currentRoute
            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemClick(bottomNavigationItem) },
                icon = {
                    NavigationIcon(
                        item = bottomNavigationItem, selected = isSelected
                    )
                },
                label = {
                    Text(
                        text = stringResource(bottomNavigationItem.label),
                        style =
                        if (isSelected) MaterialTheme.typography.labelLarge
                        else MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        softWrap = false,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                },
                alwaysShowLabel = true,
            )
        }
    }
}