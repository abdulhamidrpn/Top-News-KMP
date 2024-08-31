package com.rpn.newskmpapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.rpn.newskmpapp.presentation.theme.imageSize
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun ArticleImage(url: String, modifier: Modifier) {
    AsyncImage(
        modifier = modifier
            .size(imageSize)
            .background(Color.Gray),
        model = url,
        error = painterResource(Res.drawable.logo),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}