package com.rpn.newskmpapp.presentation.navigation


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


// TODO: Type Safe Navigation
/*
@Serializable
sealed class MyRoutes {

    @Serializable
    data class ArticleDetail(val articleStr: String) : MyRoutes()

}
*/
