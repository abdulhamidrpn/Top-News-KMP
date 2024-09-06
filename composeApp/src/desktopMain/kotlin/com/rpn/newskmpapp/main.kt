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

private class WindowSize(val width: Int, val height: Int)

private val tablet_landscape = WindowSize(1100, 825)
private val tablet_portrait = WindowSize(825, 1100)
private val mobile_landscape = WindowSize(575, 260)
private val mobile_portrait = WindowSize(260, 575)
private val laptop = WindowSize(1060, 650)
private val desktop = WindowSize(1280, 720)

/*
Create Windows Version by running the following command in Terminal:
./gradlew packageMsi
Generated Windows Application in the following Location:
...\composeApp\build\compose\binaries\main\msi
*/
fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "News",
        state = WindowState(
            width = desktop.width.dp,
            height = desktop.height.dp,
            position = WindowPosition(Alignment.Center)
        ),
        icon = org.jetbrains.compose.resources.painterResource(Res.drawable.app_logo)
    ) {
        window.minimumSize = window.size
        App()
    }

}