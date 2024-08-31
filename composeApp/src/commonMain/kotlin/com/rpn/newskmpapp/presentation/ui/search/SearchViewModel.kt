package com.rpn.newskmpapp.presentation.ui.search

import androidx.lifecycle.ViewModel
import com.rpn.newskmpapp.data.utils.AppPreferences
import com.rpn.newskmpapp.domain.use_case.GetSearchData
import com.rpn.newskmpapp.domain.utils.DataState
import com.rpn.newskmpapp.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val getSearchData: GetSearchData,
    private val appPreferences: AppPreferences
) : ViewModel() {
    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    private val _state = MutableStateFlow<SearchScreenState>(SearchScreenState())
    val state: StateFlow<SearchScreenState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SearchScreenState(),
    )

    init {
        getSearchHistory()
    }

    private fun getSearchHistory() {
        viewModelScope.launch {
            appPreferences.getSearchHistoryFlow().collectLatest { history ->
                _state.update { it.copy(searchHistory = history) }
            }
        }
    }

    private fun addSearchQuery(query: String) {
        viewModelScope.launch {
            appPreferences.addSearchQuery(query)
        }
    }

    private fun clearSearchHistory() {
        viewModelScope.launch {
            appPreferences.clearSearchHistory()
        }
    }


    fun onEvent(event: SearchScreenEvents) {
        when (event) {
            is SearchScreenEvents.OnBackClicked -> TODO()
            is SearchScreenEvents.OnRemoveHistory -> {
                clearSearchHistory()
            }

            is SearchScreenEvents.OnSearchClicked -> {
                addSearchQuery(event.query)
                viewModelScope.launch {
                    getSearchArticles(event.query)
                }
            }

            is SearchScreenEvents.OnSearchQueryChange -> {
                _state.update { it.copy(searchQuery = event.query) }
            }
        }
    }

    private suspend fun getSearchArticles(query: String) {
        getSearchData(query = query)
            .onEach { data ->
                when (data) {
                    DataState.Loading -> {
                        _state.update { it.copy(articles = Resource.Loading) }
                    }

                    is DataState.Success -> {
                        withContext(Dispatchers.Main) {
                            _state.update { it.copy(articles = Resource.Success(data.data)) }
                        }
                    }

                    is DataState.Error -> {
                        _state.update { it.copy(articles = Resource.Error(data.error)) }
                    }
                }
            }.launchIn(viewModelScope)
    }
}