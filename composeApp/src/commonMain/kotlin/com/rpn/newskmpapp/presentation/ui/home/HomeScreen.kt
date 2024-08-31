package com.rpn.newskmpapp.presentation.ui.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rpn.newskmpapp.presentation.ui.component.ShimmerEffect
import com.rpn.newskmpapp.presentation.ui.component.ArticleList
import com.rpn.newskmpapp.presentation.ui.component.EmptyContent
import com.rpn.newskmpapp.presentation.ui.component.ShimmerEffect
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.ic_network_error
import kmp_news_app.composeapp.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HeadLineScreen(
    navController: NavController,
    sharedTransactionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: HomeScreenState,
    onEvent: (HomeScreenEvents) -> Unit
) {
    state.articles.DisplayResult(
        onIdle = {

        },
        onLoading = {
            ShimmerEffect()
        },
        onSuccess = { articles ->
            if (articles.isEmpty()) EmptyContent(
                message = stringResource(Res.string.no_news),
                icon = Res.drawable.ic_browse,
                onRetryClick = {
                    onEvent(HomeScreenEvents.LoadHeadlines)
                }
            ) else
                ArticleList(
                    originalArticles = articles,
                    navController = navController,
                    sharedTransactionScope = sharedTransactionScope,
                    animatedVisibilityScope = animatedVisibilityScope
                )
        },
        onError = {
            EmptyContent(
                message = it,
                icon = Res.drawable.ic_network_error,
                onRetryClick = {
                    onEvent(HomeScreenEvents.LoadHeadlines)
                }
            )
        }
    )
}