package com.rpn.newskmpapp.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import dev.tmapps.konnection.Konnection
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.dark_mode
import kmp_news_app.composeapp.generated.resources.light_mode
import kmp_news_app.composeapp.generated.resources.system_default
import org.jetbrains.compose.resources.StringResource

val konnection = Konnection.instance

enum class Theme(val title: StringResource) {
    SYSTEM_DEFAULT(Res.string.system_default),
    LIGHT_MODE(Res.string.light_mode),
    DARK_MODE(Res.string.dark_mode)
}

enum class Type {
    Mobile, Desktop
}

enum class WindowSizeClass { Compact, Medium, Expanded }

val FadeIn = fadeIn(animationSpec = tween(220, delayMillis = 90)) +
        scaleIn(
            initialScale = 0.92f,
            animationSpec = tween(220, delayMillis = 90)
        )

val FadeOut = fadeOut(animationSpec = tween(90))

val popularSearchKeywords = listOf(
    "Breaking News",
    "Politics",
    "Share Market",
    "Sports",
    "Entertainment",
    "Technology",
    "Business",
    "Health",
    "World News",
    "Local News",
    "Science",
    "Weather",
    "Opinion",
    "Finance",
    "Culture",
    "Travel",
    "Education",
    "Environment",
    "Crime",
    "Elections",
    "Job Market"
)

