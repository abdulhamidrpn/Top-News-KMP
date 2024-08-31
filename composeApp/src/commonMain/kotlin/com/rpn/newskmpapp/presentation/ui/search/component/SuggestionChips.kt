package com.rpn.newskmpapp.presentation.ui.search.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun SearchSuggestionChips(
    list: List<String>,
    selectedKeyword: String,
    onSearchSelected: (String) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.padding(8.dp),
        state = rememberLazyListState()
    ) {
        items(items = list) { keyword ->
            SearchSuggestionChip(
                name = keyword,
                isSelected = selectedKeyword == keyword,
            ) { onSearchSelected(keyword) }
        }
    }
}


@Composable
private fun SearchSuggestionChip(
    name: String,
    isSelected: Boolean,
    imageVector: ImageVector? = Icons.Default.Search,
    onItemSelected: (String) -> Unit
) {
    val selectionColor =
        if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
    val selectionTextColor =
        if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
    val borderStroke = if (isSelected) BorderStroke(
        width = 1.dp, color = MaterialTheme.colorScheme.onTertiaryContainer
    ) else BorderStroke(
        width = 0.dp, color = Color.Transparent,
    )

    Surface(
        shape = MaterialTheme.shapes.small,
        border = borderStroke,
        color = selectionColor,
        onClick = { onItemSelected(name) }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            imageVector?.let {
                Icon(imageVector = imageVector, contentDescription = name)
            }
            Text(
                text = name,
                color = selectionTextColor,
            )
        }
    }
}