package com.rpn.newskmpapp.presentation.ui.article_detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.rpn.newskmpapp.data.model.Article
import com.rpn.newskmpapp.presentation.icon.AppIcons
import com.rpn.newskmpapp.presentation.theme.detailImageSize
import com.rpn.newskmpapp.presentation.theme.getDimens
import com.rpn.newskmpapp.utils.shareLink
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.go_back
import kmp_news_app.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3WindowSizeClassApi::class,
    ExperimentalSharedTransitionApi::class
)
@Composable
fun ArticleDetailScreen(
    navController: NavController,
    currentArticle: Article,
    articleDetailViewModel: ArticleDetailViewModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {

    LaunchedEffect(Unit) {
        articleDetailViewModel.isArticleBookmark(currentArticle)
    }

    val uriHandler = LocalUriHandler.current
    val windowSizeClass = calculateWindowSizeClass()
    val responsiveWeight = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 1f
        WindowWidthSizeClass.Medium -> 0.8f
        WindowWidthSizeClass.Expanded -> 0.5f
        else -> 1f
    }
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                modifier = Modifier.background(Color.Transparent),
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            AppIcons.ArrowBack,
                            contentDescription = stringResource(Res.string.go_back)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            shareLink(currentArticle.url)
                        },
                    ) {
                        Icon(
                            imageVector = AppIcons.Share,
                            contentDescription = "Share"
                        )
                    }
                    IconButton(
                        onClick = {
                            uriHandler.openUri(currentArticle.url)
                        },
                    ) {
                        Icon(
                            painter = painterResource(AppIcons.Browse),
                            contentDescription = "Browse"
                        )
                    }

                    IconButton(onClick = {
                        articleDetailViewModel.bookmarkArticle(currentArticle)
                    }) {
                        Icon(
                            painter = painterResource(
                                if (articleDetailViewModel.isBookmarked) AppIcons.Bookmark
                                else AppIcons.BookmarkBorder
                            ),
                            contentDescription = null,
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        with(sharedTransitionScope) {
            sharedTransitionScope.isTransitionActive
            SelectionContainer {
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .fillMaxWidth(responsiveWeight)
                            .fillMaxHeight()
                            .verticalScroll(scrollState)
                            .padding(8.dp)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .sharedBounds(
                                    rememberSharedContentState(key = "item-image-${currentArticle.id}"),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                )
                                .aspectRatio(16f / 9f)
                                .height(detailImageSize)
                                .background(Color.Gray),
                            model = currentArticle.urlToImage,
                            error = painterResource(Res.drawable.logo),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = currentArticle.title,
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.sharedBounds(
                                    rememberSharedContentState(
                                        key = "item-title-${currentArticle.id}"
                                    ),
                                    animatedVisibilityScope,
                                )
                            )



                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                HorizontalDivider(
                                    modifier = Modifier.width(70.dp)
                                        .clip(MaterialTheme.shapes.medium),
                                    thickness = getDimens().thickness
                                )

                                Text(
                                    text = currentArticle.formatPublishedAt,
                                    style = MaterialTheme.typography.labelSmall,
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ElevatedAssistChip(
                                    onClick = { },
                                    label = {
                                        Text(
                                            text = currentArticle.sourceName,
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                    },
                                    shape = RoundedCornerShape(16.dp),
                                )

                                Text(
                                    text = currentArticle.author,
                                    style = MaterialTheme.typography.labelLarge,
                                )
                            }
                            HorizontalDivider()

                            Text(
                                text = currentArticle.description ?: "",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.sharedElement(
                                    rememberSharedContentState(
                                        key = "item-description-${currentArticle.id}"
                                    ),
                                    animatedVisibilityScope,
                                )
                            )

                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "Read the full story",
                                    style = MaterialTheme.typography.labelSmall,
                                )

                                TextButton(
                                    onClick = { uriHandler.openUri(currentArticle.url) },
                                    shape = MaterialTheme.shapes.small,
                                ) {
                                    Icon(
                                        imageVector = AppIcons.ExitToApp,
                                        contentDescription = null,
                                        modifier = Modifier.padding(end = 16.dp)
                                    )

                                    Text(
                                        text = currentArticle.url,
                                        style = MaterialTheme.typography.bodySmall,
                                    )
                                }
                            }


                        }

                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

        }
    }
}

