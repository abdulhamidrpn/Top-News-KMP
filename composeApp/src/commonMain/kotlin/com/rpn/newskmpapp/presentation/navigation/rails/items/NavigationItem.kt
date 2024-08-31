package com.rpn.newskmpapp.presentation.navigation.rails.items

import com.rpn.newskmpapp.presentation.navigation.MainScreenRoutes
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.bookmark
import kmp_news_app.composeapp.generated.resources.headlines
import kmp_news_app.composeapp.generated.resources.ic_bookmark_outlined
import kmp_news_app.composeapp.generated.resources.ic_headline
import kmp_news_app.composeapp.generated.resources.ic_search
import kmp_news_app.composeapp.generated.resources.search
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class NavigationItem(
    val label: StringResource,
    val route: String,
    val unselectedIcon: DrawableResource,
    val selectedIcon: DrawableResource,
    val hasNews: Boolean = false,
    val badgeCount: Int? = null,
)


val navigationItems = listOf(
    NavigationItem(
        label = Res.string.headlines,
        route = MainScreenRoutes.HeadLine.route,
        selectedIcon = Res.drawable.ic_headline,
        unselectedIcon = Res.drawable.ic_headline,
    ),
    NavigationItem(
        label = Res.string.search,
        route = MainScreenRoutes.Search.route,
        selectedIcon = Res.drawable.ic_search,
        unselectedIcon = Res.drawable.ic_search
    ),
    NavigationItem(
        label = Res.string.bookmark,
        route = MainScreenRoutes.Bookmark.route,
        selectedIcon = Res.drawable.ic_bookmark_outlined,
        unselectedIcon = Res.drawable.ic_bookmark_outlined
    )
)