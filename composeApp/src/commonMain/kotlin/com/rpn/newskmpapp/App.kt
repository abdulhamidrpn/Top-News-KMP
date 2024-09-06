package com.rpn.newskmpapp

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rpn.newskmpapp.di.koinViewModel
import com.rpn.newskmpapp.presentation.navigation.graphs.RootNavGraph
import com.rpn.newskmpapp.presentation.theme.NewsAppTheme
import com.rpn.newskmpapp.presentation.ui.setting.SettingViewModel
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.not_connected
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        val settingViewModel = koinViewModel<SettingViewModel>()
        val currentTheme by settingViewModel.currentTheme.collectAsState()

        val snackbarHostState = remember { SnackbarHostState() }

        val isOffline by settingViewModel.isOffline.collectAsStateWithLifecycle()

        // If user is not connected to the internet show a snack bar to inform them.
        val notConnectedMessage = stringResource(Res.string.not_connected)

        NewsAppTheme(currentTheme) {

            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) }
            ) {

                LaunchedEffect(isOffline) {
                    if (isOffline) {
                        snackbarHostState.showSnackbar(
                            message = notConnectedMessage,
                            duration = Indefinite,
                        )
                    }
                }

                RootNavGraph(settingViewModel)
            }
        }
    }

}
