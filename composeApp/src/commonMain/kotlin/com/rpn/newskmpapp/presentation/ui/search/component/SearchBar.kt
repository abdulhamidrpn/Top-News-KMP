package com.rpn.newskmpapp.presentation.ui.search.component


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_history
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppView(
    modifier: Modifier = Modifier,
    hint: String = "Search...",
    query: String,
    isFocused: Boolean,
    searchHistory: List<String> = emptyList(),
    onQueryChange: (String) -> Unit,
    onSearchClick: (String) -> Unit = {},
    onRemoveHistory: () -> Unit = {},
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {}
) {
    var active by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(isFocused) {
        active = isFocused
    }

    Box(Modifier
        .semantics { isTraversalGroup = true }
        .zIndex(1f)
        .fillMaxWidth()) {

        if (searchHistory.isEmpty()) {
            // Search bar
            DockedSearchBar(
                modifier = modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp),
                query = query,
                onQueryChange = { onQueryChange(it) },
                onSearch = {
                    onSearchClick(query)
                    active = false
                },
                placeholder = { Text(hint) },
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                active = false,
                onActiveChange = {},
                content = {}
            )
        }
        else {
            DockedSearchBar(
                modifier = modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp),
                placeholder = { Text(hint) },
                query = query,
                onQueryChange = { onQueryChange(it) },
                onSearch = {
                    onSearchClick(query)
                    active = false
                },
                active = active,
                onActiveChange = {  active = it },
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
            ) {
                if (searchHistory.isNotEmpty()) {
                    Text(
                        text = "Recent Searches",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        items(searchHistory) { history ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(MaterialTheme.shapes.medium)
                                    .clickable {
                                        onQueryChange(history)
                                        onSearchClick(history)
                                        active = false
                                    }
                                    .padding(vertical = 8.dp, horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_history),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(
                                    text = history,
                                    style = MaterialTheme.typography.titleMedium,
                                )
                            }
                        }
                    }
                    Text(
                        text = "Clear history",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .align(Alignment.End)
                            .clip(MaterialTheme.shapes.medium)
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                            .clickable {
                                onRemoveHistory()
                            },
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}