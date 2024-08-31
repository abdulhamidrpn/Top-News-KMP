package com.rpn.newskmpapp.presentation.navigation.graphs

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rpn.newskmpapp.di.koinViewModel
import com.rpn.newskmpapp.presentation.navigation.Graph
import com.rpn.newskmpapp.presentation.navigation.MainScreenRoutes
import com.rpn.newskmpapp.presentation.ui.bookmark.BookMarkViewModel
import com.rpn.newskmpapp.presentation.ui.bookmark.BookmarkScreen
import com.rpn.newskmpapp.presentation.ui.home.HeadLineScreen
import com.rpn.newskmpapp.presentation.ui.home.HomeViewModel
import com.rpn.newskmpapp.presentation.ui.search.SearchScreen
import com.rpn.newskmpapp.presentation.ui.search.SearchViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    homeNavController: NavHostController,
    paddingValues: PaddingValues,
    sharedTransactionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {


    NavHost(
        navController = homeNavController,
        route = Graph.mainScreenGraph,
        startDestination = MainScreenRoutes.HeadLine.route,
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.background)
    ) {

        composable(route = MainScreenRoutes.HeadLine.route) {
            val headlineViewModel = koinViewModel<HomeViewModel>()
            val state by headlineViewModel.state.collectAsState()
            HeadLineScreen(
                navController = rootNavController,
                sharedTransactionScope = sharedTransactionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                state = state,
                onEvent = headlineViewModel::onEvent
            )
        }

        composable(route = MainScreenRoutes.Search.route) {
            val headlineViewModel = koinViewModel<SearchViewModel>()
            val state by headlineViewModel.state.collectAsState()
            SearchScreen(
                navController = rootNavController,
                sharedTransactionScope = sharedTransactionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                state = state,
                onEvent = headlineViewModel::onEvent
            )
        }

        composable(route = MainScreenRoutes.Bookmark.route) {
            val bookMarkViewModel = koinViewModel<BookMarkViewModel>()
            val state by bookMarkViewModel.state.collectAsState()
            BookmarkScreen(
                navController = rootNavController,
                sharedTransactionScope = sharedTransactionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                state = state,
                onEvent = bookMarkViewModel::onEvent
            )
        }
    }
}