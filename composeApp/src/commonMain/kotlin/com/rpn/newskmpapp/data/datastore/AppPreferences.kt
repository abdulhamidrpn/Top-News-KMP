package com.rpn.newskmpapp.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.rpn.newskmpapp.utils.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AppPreferences(
    private val dataStore: DataStore<Preferences>
) {
    private val themeKey = stringPreferencesKey("theme")

    private val searchHistoryKey = stringPreferencesKey("search_history")

    // Function to get the search history as a Flow
    fun getSearchHistoryFlow(): Flow<List<String>> {
        return dataStore.data.map { preferences ->
            preferences[searchHistoryKey]?.split(",")?.take(16) ?: emptyList()
        }
    }

    // Function to add a new search query to the history
    suspend fun addSearchQuery(query: String) {
        dataStore.edit { preferences ->
            val currentList =
                preferences[searchHistoryKey]?.split(",")?.toMutableList() ?: mutableListOf()
            if (query !in currentList) {
                if (currentList.size >= 16) {
                    currentList.removeAt(16) // Remove the oldest item if the list exceeds 16 items
                }
                currentList.add(0, query)
                preferences[searchHistoryKey] = currentList.joinToString(",")
            }
        }
    }

    // Function to clear the search history
    suspend fun clearSearchHistory() {
        dataStore.edit { preferences ->
            preferences.remove(searchHistoryKey)
        }
    }


    suspend fun getTheme() = dataStore.data.map { preferences ->
        preferences[themeKey] ?: Theme.DARK_MODE.name
    }.first()

    suspend fun changeThemeMode(value: String) = dataStore.edit { preferences ->
        preferences[themeKey] = value
    }
}