package com.rpn.newskmpapp.presentation.navigation


import com.rpn.newskmpapp.domain.model.Article
import kotlinx.serialization.Serializable

object Graph {
    const val rootScreenGraph = "root_screen_graph"
    const val mainScreenGraph = "main_screen_graph"
}

sealed class MainScreenRoutes(val route: String) {
    data object HeadLine : MainScreenRoutes("headline")
    data object Search : MainScreenRoutes("search")
    data object Bookmark : MainScreenRoutes("bookmark")
}

sealed class Routes(val route: String) {
    data object Setting : Routes("setting")
    data object ArticleDetail : Routes("articleDetail")
}
