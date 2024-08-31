package com.rpn.newskmpapp.presentation.ui.setting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.rpn.newskmpapp.presentation.ui.setting.component.DeveloperInfoCard
import com.rpn.newskmpapp.presentation.ui.setting.component.BookmarkDialog
import com.rpn.newskmpapp.presentation.ui.setting.component.SettingItem
import com.rpn.newskmpapp.presentation.ui.setting.component.ThemeSelectionDialog
import com.rpn.newskmpapp.utils.Theme
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.delete_bookmark
import kmp_news_app.composeapp.generated.resources.go_back
import kmp_news_app.composeapp.generated.resources.ic_delete
import kmp_news_app.composeapp.generated.resources.ic_light_mode
import kmp_news_app.composeapp.generated.resources.logo
import kmp_news_app.composeapp.generated.resources.setting
import kmp_news_app.composeapp.generated.resources.theme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController, settingViewModel: SettingViewModel) {

    var showThemeSelectionDialog by remember { mutableStateOf(false) }
    val currentTheme by settingViewModel.currentTheme.collectAsState()
    var showDeleteBookmarkArticleDialog by remember { mutableStateOf(false) }

    when {
        showDeleteBookmarkArticleDialog -> {
            BookmarkDialog(
                onDeleteHistory = {
                    settingViewModel.deleteHistory()
                    showDeleteBookmarkArticleDialog = false
                },
                onDismissRequest = {
                    showDeleteBookmarkArticleDialog = false
                }
            )

        }

        showThemeSelectionDialog -> {
            ThemeSelectionDialog(
                onThemeChange = { theme ->
                    settingViewModel.changeThemeMode(theme.name)
                    showThemeSelectionDialog = false
                },
                onDismissRequest = { showThemeSelectionDialog = false },
                currentTheme =currentTheme ?: Theme.DARK_MODE.name
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.setting)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.go_back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                SettingItem(
                    onClick = {
                        showThemeSelectionDialog = true
                    },
                    painter = painterResource(Res.drawable.ic_light_mode),
                    itemName = stringResource(Res.string.theme)
                )
            }
            item {
                SettingItem(
                    onClick = {
                        showDeleteBookmarkArticleDialog = true
                    },
                    painter = painterResource(Res.drawable.ic_delete),
                    itemName = stringResource(Res.string.delete_bookmark),
                    itemColor = MaterialTheme.colorScheme.error
                )
            }
            item {
                DeveloperInfoCard(
                    name = "Mohammad Abdul Hamid",
                    role = "Android Developer",
                    image = painterResource(Res.drawable.logo),
                    profileUrl = "https://linktr.ee/abdulhamidrpn"
                )
            }
        }
    }
}