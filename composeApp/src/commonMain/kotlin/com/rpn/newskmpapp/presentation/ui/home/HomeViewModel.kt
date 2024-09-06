package com.rpn.newskmpapp.presentation.ui.home

import androidx.lifecycle.ViewModel
import com.rpn.newskmpapp.domain.use_case.GetHeadLinesUseCase
import com.rpn.newskmpapp.domain.utils.DataState
import com.rpn.newskmpapp.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getHeadLinesUseCase: GetHeadLinesUseCase
) : ViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    private val _state = MutableStateFlow<HomeScreenState>(HomeScreenState(Resource.Loading))
    val state: StateFlow<HomeScreenState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HomeScreenState(),
    )

    init {
        onEvent(HomeScreenEvents.LoadHeadlines)
    }

    fun onEvent(event: HomeScreenEvents) {
        when (event) {
            is HomeScreenEvents.LoadCurrentlyReading -> {}

            is HomeScreenEvents.LoadAlreadyRead -> {}

            is HomeScreenEvents.LoadHeadlines -> {
                viewModelScope.launch {
                    getHeadLineData()
                }
            }
        }
    }

    private suspend fun getHeadLineData() {
        getHeadLinesUseCase()
            .onEach { data ->
                when (data) {
                    DataState.Loading -> {
                        _state.update { it.copy(articles = Resource.Loading) }
                    }

                    is DataState.Success -> {
                        withContext(Dispatchers.Main) {
                            println("GetHeadlines: ${data.data.size} -> ${data.data}")
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