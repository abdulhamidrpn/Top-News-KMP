package com.rpn.newskmpapp.presentation.ui.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rpn.newskmpapp.data.model.Article

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArticleItem(
    sharedTransactionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .shadow(
                elevation = 12.dp,
                clip = true,
                ambientColor = Color(0xffCE5A01),
                spotColor = Color(0xffCE5A01)
            )
            .clickable { onClick() }
    ) {
        with(sharedTransactionScope) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(8.dp)
            ) {

                ArticleImage(
                    url = article.urlToImage ?: "",
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "item-image-${article.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    )
                )

                Column(
                    modifier = Modifier.weight(1f).padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    Text(
                        modifier = Modifier.sharedElement(
                            state = rememberSharedContentState(
                                key = "item-title-${article.id}"
                            ),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                        text = article.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (!article.description.isNullOrEmpty()) {
                        Text(
                            modifier = Modifier.padding(top = 8.dp).sharedElement(
                                state = rememberSharedContentState(
                                    key = "item-description-${article.id}"
                                ),
                                animatedVisibilityScope = animatedVisibilityScope,
                            ),
                            text = article.description,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }


                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (article.sourceName.isNotEmpty()) {
                            ElevatedAssistChip(
                                onClick = {

                                },
                                label = {
                                    Text(
                                        text = article.sourceName,
                                        maxLines = 2,
                                        style = MaterialTheme.typography.labelMedium,
                                        modifier = Modifier.padding(vertical = 2.dp)
                                    )
                                },
                                shape = RoundedCornerShape(16.dp)

                            )
                        }

                        Text(
                            text = article.author,
                            maxLines = 1,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }

                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = article.formatPublishedAt,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FeaturedArticle(
    article: Article,
    sharedTransactionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .shadow(
                elevation = 26.dp,
                clip = true,
                ambientColor = Color(0xffCE5A01),
                spotColor = Color(0xffCE5A01)
            )
            .clickable { onClick() }
    ) {
        with(sharedTransactionScope) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = "item-container-${article.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ArticleImage(
                    url = article.urlToImage ?: "",
                    modifier = Modifier.fillMaxWidth().sharedElement(
                        state = rememberSharedContentState(key = "item-image-${article.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    )
                )

                Text(
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(
                            key = "item-title-${article.id}"
                        ),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                    text = article.title,
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                if (!article.description.isNullOrEmpty()) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp).sharedElement(
                            state = rememberSharedContentState(
                                key = "item-description-${article.id}"
                            ),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                        text = article.description,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (article.sourceName.isNotEmpty()) {
                        ElevatedAssistChip(
                            onClick = {

                            },
                            label = {
                                Text(
                                    text = article.sourceName,
                                    maxLines = 2,
                                    style = MaterialTheme.typography.labelMedium,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                            },
                            shape = RoundedCornerShape(16.dp),
                        )
                    }

                    Text(
                        text = article.author,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }

                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = article.formatPublishedAt,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

            }
        }

    }

}
