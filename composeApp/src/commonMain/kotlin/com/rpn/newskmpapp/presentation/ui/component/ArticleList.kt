package com.rpn.newskmpapp.presentation.ui.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rpn.newskmpapp.domain.model.Article
import com.rpn.newskmpapp.presentation.navigation.Routes
import com.rpn.newskmpapp.utils.Type
import com.rpn.newskmpapp.utils.getType
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun BoxScope.StaggeredGridWithCustomScrollbar(staggeredGridState: LazyStaggeredGridState) {
    var dragOffset by remember { mutableStateOf(0f) }
    var scrollbarPosition by remember { mutableFloatStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(staggeredGridState) {
        snapshotFlow { staggeredGridState.firstVisibleItemIndex to staggeredGridState.firstVisibleItemScrollOffset }
            .collect { (index, offset) ->
                val totalItems = staggeredGridState.layoutInfo.totalItemsCount
                val viewportHeight = staggeredGridState.layoutInfo.viewportSize.height
                val visibleItems = staggeredGridState.layoutInfo.visibleItemsInfo.size

                if (totalItems > 0 && visibleItems > 0) {
                    val scrollProgress = (index + offset / viewportHeight.toFloat()) / totalItems
                    scrollbarPosition = scrollProgress.coerceIn(0f, 1f)
                }
            }
    }
    // Custom Vertical Scrollbar
    Box(
        modifier = Modifier
            .align(Alignment.CenterEnd)
            .width(8.dp)
            .fillMaxHeight()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    val totalItems = staggeredGridState.layoutInfo.totalItemsCount.toFloat()
                    val viewportHeight = staggeredGridState.layoutInfo.viewportSize.height.toFloat()
                    val visibleItems = staggeredGridState.layoutInfo.visibleItemsInfo.size

                    if (totalItems > 0 && visibleItems > 0) {
                        val maxScrollOffset =
                            staggeredGridState.layoutInfo.viewportEndOffset.toFloat()
                        val scrollPosition =
                            ((scrollbarPosition * totalItems)) + ((dragAmount / viewportHeight) * totalItems).y.toFloat()

                        coroutineScope.launch {
                            staggeredGridState.scrollToItem(scrollPosition.toInt())
                        }
                    }
                }
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val scrollBarHeight = size.height / 5f
            drawRect(
                color = Color.Gray,
                topLeft = Offset(0f, scrollbarPosition * (size.height - scrollBarHeight)),
                size = size.copy(height = scrollBarHeight)
            )
        }
    }
}

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
    val listState = rememberLazyStaggeredGridState()
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {

        val state = rememberLazyStaggeredGridState()

        LazyVerticalStaggeredGrid(
            modifier = modifier.fillMaxSize(),
            columns = StaggeredGridCells.Adaptive(360.dp),
            contentPadding = PaddingValues(16.dp),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            state = listState,
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
                        navController.navigate(Routes.ArticleDetail.route)
                    }
                )
            }
        }


        // Scroll indicator if the screen is not compact
        if (getType() == Type.Desktop) {
            StaggeredGridWithCustomScrollbar(staggeredGridState = listState)
        }

        // FAB to scroll to the top
        if (listState.firstVisibleItemIndex > 0) {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                content = { Icon(Icons.Default.KeyboardArrowUp, "Scroll to top.") }
            )
        }
    }
}
