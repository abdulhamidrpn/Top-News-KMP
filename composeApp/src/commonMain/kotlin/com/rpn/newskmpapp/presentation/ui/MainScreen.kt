package com.rpn.newskmpapp.presentation.ui

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rpn.newskmpapp.presentation.icon.AppIcons
import com.rpn.newskmpapp.presentation.navigation.Routes
import com.rpn.newskmpapp.presentation.navigation.graphs.MainNavGraph
import com.rpn.newskmpapp.presentation.navigation.rails.items.navigationItems
import com.rpn.newskmpapp.presentation.navigation.rails.navbar.NavigationBottomBar
import com.rpn.newskmpapp.presentation.navigation.rails.navbar.NavigationSideBar
import com.rpn.newskmpapp.presentation.theme.isNotCompactWindowSize
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.setting
import org.jetbrains.compose.resources.stringResource

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class,
    ExperimentalSharedTransitionApi::class
)
@Composable
fun MainScreen(
    rootNavController: NavHostController,
    mainNavController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()

    var previousRoute by rememberSaveable {
        mutableStateOf(navBackStackEntry?.destination?.route)
    }
    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf { navBackStackEntry?.destination?.route ?: "" }
    }

    val title by remember(currentRoute) {
        derivedStateOf {
            navigationItems.find { it.route == currentRoute }?.label
                ?: navigationItems.first().label
        }
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    DisposableEffect(Unit) {

        onDispose {
            previousRoute = currentRoute
        }
    }
    LaunchedEffect(Unit) {
        if (previousRoute != null) {
            mainNavController.navigate(previousRoute!!) {
                mainNavController.graph.startDestinationRoute?.let { startDestinationRoute ->
                    popUpTo(startDestinationRoute) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }


    val useNavRail = isNotCompactWindowSize()
    if (useNavRail) {
        Row {
            NavigationSideBar(
                items = navigationItems,
                currentRoute = currentRoute,
                onItemClick = { currentBottomNavigationItem ->
                    mainNavController.navigate(currentBottomNavigationItem.route) {
                        mainNavController.graph.startDestinationRoute?.let { startDestinationRoute ->
                            popUpTo(startDestinationRoute) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )

            Scaffold(modifier = Modifier.weight(1f)) {
                Column {

                    TopAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        scrollBehavior = scrollBehavior,
                        title = {
                            Text(
                                stringResource(title),
                                style = MaterialTheme.typography.headlineSmall,
                                maxLines = 1,
                                softWrap = false,
                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Bold,
                            )
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    rootNavController.navigate(Routes.Setting.route)
                                },
                            ) {
                                Icon(
                                    imageVector = AppIcons.Settings,
                                    contentDescription = stringResource(Res.string.setting)
                                )
                            }


                        }
                    )

                    MainNavGraph(
                        rootNavController = rootNavController,
                        homeNavController = mainNavController,
                        paddingValues = PaddingValues(),
                        sharedTransactionScope = sharedTransitionScope,
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }

            }

        }
    } else {
        Scaffold(
            modifier = Modifier,
            topBar = {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    scrollBehavior = scrollBehavior,
                    title = {
                        Text(
                            stringResource(title),
                            style = MaterialTheme.typography.headlineSmall,
                            maxLines = 1,
                            softWrap = false,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                rootNavController.navigate(Routes.Setting.route)
                            },
                        ) {
                            Icon(
                                imageVector = AppIcons.Settings,
                                contentDescription = stringResource(Res.string.setting)
                            )
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar {
                    NavigationBottomBar(
                        items = navigationItems,
                        currentRoute = currentRoute,
                        onItemClick = { currentBottomNavigationItem ->
                            mainNavController.navigate(currentBottomNavigationItem.route) {
                                mainNavController.graph.startDestinationRoute?.let { startDestinationRoute ->
                                    popUpTo(startDestinationRoute) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        ) { paddingValues ->
            MainNavGraph(
                rootNavController = rootNavController,
                homeNavController = mainNavController,
                paddingValues = paddingValues,
                sharedTransactionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope
            )
        }
    }
}