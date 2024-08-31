package com.rpn.newskmpapp.presentation.navigation.graphs

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rpn.newskmpapp.di.koinViewModel
import com.rpn.newskmpapp.domain.model.Article
import com.rpn.newskmpapp.presentation.navigation.Graph
import com.rpn.newskmpapp.presentation.navigation.Routes
import com.rpn.newskmpapp.presentation.ui.MainScreen
import com.rpn.newskmpapp.presentation.ui.article_detail.ArticleDetailScreen
import com.rpn.newskmpapp.presentation.ui.article_detail.ArticleDetailViewModel
import com.rpn.newskmpapp.presentation.ui.setting.SettingScreen
import com.rpn.newskmpapp.presentation.ui.setting.SettingViewModel
import kotlinx.serialization.json.Json

fun noAnimation(): EnterTransition = fadeIn(initialAlpha = 1f)

fun noAnimationExit(): ExitTransition = fadeOut(targetAlpha = 1f)
/*enterTransition =  {
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween(150)
    )
},
exitTransition = {
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween(150)
    )
},
popEnterTransition = {
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween(150)
    )
},
popExitTransition = {
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween(150)
    )
}*/

/*,enterTransition = { noAnimation() },
exitTransition = { noAnimationExit() },
popEnterTransition = { noAnimation() },
popExitTransition = { noAnimationExit() }*/

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RootNavGraph(settingViewModel: SettingViewModel) {
    val rootNavController = rememberNavController()
    val homeNavController = rememberNavController()
    SharedTransitionLayout {
        val sharedTransitionScope = this
        NavHost(
            navController = rootNavController,
            route = Graph.rootScreenGraph,
            startDestination = Graph.mainScreenGraph,
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            composable(route = Graph.mainScreenGraph) {
                MainScreen(
                    animatedVisibilityScope = this,
                    sharedTransactionScope = sharedTransitionScope,
                    rootNavController = rootNavController,
                    mainNavController = homeNavController
                )
            }
            composable(route = Routes.Setting.route) {
                SettingScreen(
                    navController = rootNavController,
                    settingViewModel = settingViewModel
                )
            }
            composable(route = Routes.ArticleDetail.route) {

                val articleDetailViewModel = koinViewModel<ArticleDetailViewModel>()
                rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")
                    ?.let { article ->
                        val currentArticle: Article = Json.decodeFromString(article)
                        ArticleDetailScreen(
                            navController = rootNavController,
                            sharedTransitionScope = sharedTransitionScope,
                            animatedVisibilityScope = this,
                            currentArticle = currentArticle,
                            articleDetailViewModel = articleDetailViewModel
                        )
                    }
            }
            /*composable<MyRoutes> { backStackEntry ->
                val currentArticle = backStackEntry.toRoute<MyRoutes.ArticleDetail>().article
                val articleDetailViewModel = koinViewModel<ArticleDetailViewModel>()

                ArticleDetailScreen(
                    navController = rootNavController,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = this,
                    currentArticle = currentArticle,
                    articleDetailViewModel = articleDetailViewModel
                )
            }*/
        }
    }
}