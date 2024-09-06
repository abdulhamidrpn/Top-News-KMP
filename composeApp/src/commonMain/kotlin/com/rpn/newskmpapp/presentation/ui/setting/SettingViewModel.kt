package com.rpn.newskmpapp.presentation.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpn.newskmpapp.data.datastore.AppPreferences
import com.rpn.newskmpapp.domain.repository.FavouriteNewsRepository
import com.rpn.newskmpapp.utils.konnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingViewModel(
    private val appPreferences: AppPreferences,
    private val favouriteNewsRepository: FavouriteNewsRepository
) : ViewModel() {

    private val _isOffline: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isOffline = _isOffline.asStateFlow()

    private val _currentTheme: MutableStateFlow<String?> = MutableStateFlow(null)
    val currentTheme = _currentTheme.asStateFlow()

    fun deleteHistory() = viewModelScope.launch(Dispatchers.IO) {
        favouriteNewsRepository.deleteAllBookmark()
    }

    init {
        currentThemeGet()
        getConnection()
    }

    private fun currentThemeGet() = runBlocking {
        _currentTheme.value = appPreferences.getTheme()
    }

    fun changeThemeMode(value: String) = viewModelScope.launch(Dispatchers.IO) {
        appPreferences.changeThemeMode(value)
        _currentTheme.value = value
    }

    private fun getConnection() {
        viewModelScope.launch {
            konnection.observeHasConnection().collect { isConnected ->
                _isOffline.emit(!isConnected)
            }
        }
    }
}