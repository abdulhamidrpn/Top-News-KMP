package com.rpn.newskmpapp

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.rpn.newskmpapp.di.initKoin
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.app_logo

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "News",
        state = WindowState(
            width = 1280.dp,
            height = 720.dp,
            position = WindowPosition(Alignment.Center)
        ),
        icon = org.jetbrains.compose.resources.painterResource(Res.drawable.app_logo)
    ) {
        window.minimumSize = window.size
        App()
    }
}
