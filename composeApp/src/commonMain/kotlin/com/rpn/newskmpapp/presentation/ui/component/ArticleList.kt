package com.rpn.newskmpapp.presentation.ui.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rpn.newskmpapp.data.model.Article
import com.rpn.newskmpapp.presentation.icon.AppIcons
import com.rpn.newskmpapp.presentation.navigation.Routes
import com.rpn.newskmpapp.presentation.ui.component.scrollbar.DraggableScrollbar
import com.rpn.newskmpapp.presentation.ui.component.scrollbar.rememberDraggableScroller
import com.rpn.newskmpapp.presentation.ui.component.scrollbar.scrollbarState
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@OptIn(
    ExperimentalMaterial3WindowSizeClassApi::class,
    ExperimentalSharedTransitionApi::class
)
@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    navController: NavController,
    originalArticles: List<Article>,
    sharedTransactionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val windowSizeClass = calculateWindowSizeClass()

    val gridCells = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 1
        WindowWidthSizeClass.Medium -> 2
        WindowWidthSizeClass.Expanded -> 3
        else -> 4
    }
    val articles = remember(originalArticles) { originalArticles.toList() }
    val itemsAvailable = articles.size
    val state = rememberLazyStaggeredGridState()
    val scrollbarState = state.scrollbarState(
        itemsAvailable = itemsAvailable,
    )

    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        LazyVerticalStaggeredGrid(
            modifier = modifier.fillMaxSize(),
            columns = StaggeredGridCells.Adaptive(360.dp),
            contentPadding = PaddingValues(16.dp),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            state = state,
        ) {

            item(span = StaggeredGridItemSpan.SingleLane, key = 1) {
                articles.firstOrNull()?.let { article ->
                    FeaturedArticle(
                        sharedTransactionScope = sharedTransactionScope,
                        animatedVisibilityScope = animatedVisibilityScope,
                        article = article,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            val articleStr = Json.encodeToString(article)

                            navController.currentBackStackEntry?.savedStateHandle?.apply {
                                set("article", articleStr)
                            }
                            navController.navigate(Routes.ArticleDetail.route)

                        }
                    )
                }
            }

            items(articles.drop(1)) { article ->

                ArticleItem(
                    sharedTransactionScope = sharedTransactionScope,
                    animatedVisibilityScope = animatedVisibilityScope,
                    article = article,
                    onClick = {
                        val articleStr = Json.encodeToString(article)
                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                            set("article", articleStr)
                        }

                        println("currentArticle on Click ${articleStr.toString()}")
                        navController.navigate(Routes.ArticleDetail.route)

                        // TODO: Type Safe Navigation
                        //navController.navigate(MyRoutes.ArticleDetail(articleStr))
                    }
                )
            }
        }


        state.DraggableScrollbar(
            modifier = Modifier
                .fillMaxHeight()
                .windowInsetsPadding(WindowInsets.systemBars)
                .padding(horizontal = 2.dp)
                .align(Alignment.CenterEnd),
            state = scrollbarState,
            orientation = Orientation.Vertical,
            onThumbMoved = state.rememberDraggableScroller(
                itemsAvailable = itemsAvailable,
            ),
        )

        // FAB to scroll to the top
        if (state.firstVisibleItemIndex > 0) {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        state.animateScrollToItem(0)
                    }
                },
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                content = { Icon(AppIcons.ArrowUp, "Scroll to top.") }
            )
        }
    }
}
