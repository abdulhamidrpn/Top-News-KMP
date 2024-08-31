package com.rpn.newskmpapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.rpn.newskmpapp.di.koinViewModel
import com.rpn.newskmpapp.presentation.navigation.graphs.RootNavGraph
import com.rpn.newskmpapp.presentation.theme.NewsAppTheme
import com.rpn.newskmpapp.presentation.ui.setting.SettingViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        val settingViewModel = koinViewModel<SettingViewModel>()
        val currentTheme by settingViewModel.currentTheme.collectAsState()
        NewsAppTheme(currentTheme) {
            RootNavGraph(settingViewModel)
        }
    }

}
