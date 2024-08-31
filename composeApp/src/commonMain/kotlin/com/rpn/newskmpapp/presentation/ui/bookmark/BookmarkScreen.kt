package com.rpn.newskmpapp.presentation.ui.bookmark

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rpn.newskmpapp.presentation.ui.component.ArticleList
import com.rpn.newskmpapp.presentation.ui.component.EmptyContent
import com.rpn.newskmpapp.presentation.ui.component.ShimmerEffect
import com.rpn.newskmpapp.presentation.ui.home.HomeScreenEvents
import com.rpn.newskmpapp.presentation.ui.home.HomeScreenState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BookmarkScreen(
    navController: NavController,
    sharedTransactionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: BookMarkScreenState,
    onEvent: (BookMarkScreenEvents) -> Unit
) {

    state.articles.DisplayResult(
        onIdle = {
            EmptyContent("No articles saved yet")
        },
        onLoading = {
            ShimmerEffect()
        },
        onSuccess = { articles ->
            if (articles.isEmpty()) EmptyContent("No articles found") else
                ArticleList(
                    originalArticles = articles,
                    sharedTransactionScope = sharedTransactionScope,
                    animatedVisibilityScope = animatedVisibilityScope,
                    navController = navController
                )
        },
        onError = {
            EmptyContent("Something went wrong")
        }
    )
}