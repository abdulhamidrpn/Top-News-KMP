package com.rpn.newskmpapp.presentation.ui.search

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChipDefaults.assistChipColors
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rpn.newskmpapp.presentation.theme.getDimens
import com.rpn.newskmpapp.presentation.ui.component.ArticleList
import com.rpn.newskmpapp.presentation.ui.component.EmptyContent
import com.rpn.newskmpapp.presentation.ui.component.ShimmerEffect
import com.rpn.newskmpapp.presentation.ui.search.component.SearchAppView
import com.rpn.newskmpapp.utils.popularSearchKeywords
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.ic_search
import kmp_news_app.composeapp.generated.resources.no_news
import kmp_news_app.composeapp.generated.resources.type_to_search
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    sharedTransactionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: SearchScreenState,
    onEvent: (SearchScreenEvents) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var isSuggestionChipsVisible by remember { mutableStateOf(false) }
    var isArticleEmpty by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = Unit) {
        if (isArticleEmpty) {
            delay(500)
            focusRequester.requestFocus()
        }
    }
    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {

        SearchAppView(
            modifier = Modifier
                .padding(
                    horizontal = getDimens().paddingSmall,
                    vertical = getDimens().paddingSmall
                )
                .focusRequester(focusRequester)
                .onFocusChanged { isSuggestionChipsVisible = it.isFocused },
            query = state.searchQuery,
            searchHistory = state.searchHistory,
            onQueryChange = { onEvent(SearchScreenEvents.OnSearchQueryChange(it)) },
            isFocused = isSuggestionChipsVisible,
            onSearchClick = {
                onEvent(SearchScreenEvents.OnSearchClicked(it))
                keyboardController?.hide()
                focusManager.clearFocus()
            },
            onRemoveHistory = { onEvent(SearchScreenEvents.OnRemoveHistory) },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (state.searchQuery.isNotEmpty()) {
                            onEvent(SearchScreenEvents.OnSearchQueryChange(""))
                            isSuggestionChipsVisible = false
                        } else navController.navigateUp()
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                }
            },
        )

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = 72.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val selectionColor: @Composable (Boolean) -> Color = { selection ->
                if (selection) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
            }

            val leadingIcon: @Composable (() -> Unit) = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
            LazyRow(
                contentPadding = PaddingValues(horizontal = getDimens().paddingLarge),
                horizontalArrangement = Arrangement.spacedBy(getDimens().paddingSmall)
            ) {
                items(popularSearchKeywords) { keyword ->
                    ElevatedAssistChip(
                        onClick = {
                            onEvent(SearchScreenEvents.OnSearchQueryChange(keyword))
                            onEvent(SearchScreenEvents.OnSearchClicked(keyword))

                            keyboardController?.hide()
                            focusManager.clearFocus()
                        },
                        label = { Text(keyword) },
                        colors = assistChipColors(containerColor = selectionColor(keyword == state.searchQuery)),
                        shape = RoundedCornerShape(8.dp),
                        leadingIcon = leadingIcon.takeIf { keyword == state.searchQuery }
                    )
                }
            }

            HorizontalDivider()

            state.articles.DisplayResult(
                onIdle = {
                    EmptyContent(
                        message = stringResource(Res.string.type_to_search),
                        icon = Res.drawable.ic_search,
                        onRetryClick = null
                    )
                },
                onLoading = {
                    ShimmerEffect()
                },
                onSuccess = { articles ->
                    if (articles.isEmpty())
                        EmptyContent(
                            message = stringResource(Res.string.no_news),
                            icon = Res.drawable.ic_browse,
                            onRetryClick = {
                                onEvent(SearchScreenEvents.OnSearchClicked(state.searchQuery))
                            }
                        ) else {
                        isArticleEmpty = false
                        ArticleList(
                            originalArticles = articles,
                            sharedTransactionScope = sharedTransactionScope,
                            animatedVisibilityScope = animatedVisibilityScope,
                            navController = navController
                        )
                    }
                },
                onError = {
                    EmptyContent(
                        message = it,
                        icon = Res.drawable.ic_network_error,
                        onRetryClick = {
                            onEvent(SearchScreenEvents.OnSearchClicked(state.searchQuery))
                        }
                    )
                }
            )
        }
    }


}
