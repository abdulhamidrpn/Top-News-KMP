package com.rpn.newskmpapp

import androidx.compose.ui.window.ComposeUIViewController


fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}