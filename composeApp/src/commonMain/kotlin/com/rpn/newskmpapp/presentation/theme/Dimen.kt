package com.rpn.newskmpapp.presentation.theme

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val xxSmallPadding = 4.dp
val xSmallPadding = 8.dp
val smallPadding = 12.dp

val mediumPadding = 16.dp

val largePadding = 20.dp
val xLargePadding = 24.dp
val xxLargePadding = 32.dp
val xxxLargePadding = 48.dp


val imageSize = 120.dp
val detailImageSize = 250.dp

sealed class Dimens {
    abstract val paddingSmall: Dp
    abstract val paddingMedium: Dp
    abstract val paddingLarge: Dp
    abstract val thickness: Dp
    abstract val imageHeight: Dp
    abstract val imageWidth: Dp
    abstract val textSizeSmall: TextUnit
    abstract val textSizeMedium: TextUnit
    abstract val textSizeLarge: TextUnit

    data object Compact : Dimens() {
        override val paddingSmall = 4.dp
        override val paddingMedium = 8.dp
        override val paddingLarge = 12.dp
        override val thickness = 2.dp
        override val imageHeight: Dp =160.dp
        override val imageWidth: Dp =260.dp
        override val textSizeSmall = 12.sp
        override val textSizeMedium = 14.sp
        override val textSizeLarge = 18.sp
    }

    data object Medium : Dimens() {
        override val paddingSmall = 8.dp
        override val paddingMedium = 16.dp
        override val paddingLarge = 24.dp
        override val thickness = 3.dp
        override val imageHeight: Dp =200.dp
        override val imageWidth: Dp =200.dp
        override val textSizeSmall = 13.sp
        override val textSizeMedium = 16.sp
        override val textSizeLarge = 22.sp
    }

    data object Expanded : Dimens() {
        override val paddingSmall = 12.dp
        override val paddingMedium = 24.dp
        override val paddingLarge = 32.dp
        override val thickness = 5.dp
        override val imageHeight: Dp =300.dp
        override val imageWidth: Dp =400.dp
        override val textSizeSmall = 14.sp
        override val textSizeMedium = 18.sp
        override val textSizeLarge = 28.sp
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun getDimens(): Dimens {
    val windowSizeClass = calculateWindowSizeClass()
    return when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> Dimens.Compact
        WindowWidthSizeClass.Medium -> Dimens.Medium
        WindowWidthSizeClass.Expanded -> Dimens.Expanded
        else -> Dimens.Compact
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun isNotCompactWindowSize(): Boolean {
    val windowSizeClass = calculateWindowSizeClass()
    return windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
}


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun getWindowSize() : String  {
    // Select a navigation element based on window size.
    val windowSizeClass = calculateWindowSizeClass()

    val width =  windowSizeClass.widthSizeClass.toString()
    val height = windowSizeClass.heightSizeClass.toString()
    return when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> "Compact $width x $height"
        WindowWidthSizeClass.Medium -> "Medium $width x $height"
        WindowWidthSizeClass.Expanded -> "Expanded $width x $height"
        else -> "Else $width x $height"
    }
}
