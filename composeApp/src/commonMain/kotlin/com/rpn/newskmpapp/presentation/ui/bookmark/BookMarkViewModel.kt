package com.rpn.newskmpapp.presentation.ui.bookmark

import androidx.lifecycle.ViewModel
import com.rpn.newskmpapp.data.mapper.toDomainFavoriteModelEntityList
import com.rpn.newskmpapp.domain.repository.FavouriteNewsRepository
import com.rpn.newskmpapp.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookMarkViewModel(
    private val favouriteNewsRepository: FavouriteNewsRepository
) : ViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    private val _state = MutableStateFlow<BookMarkScreenState>(BookMarkScreenState())
    val state: StateFlow<BookMarkScreenState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = BookMarkScreenState(),
    )

    init {
        getBookMarks()
    }

    private fun getBookMarks() {

        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(articles = Resource.Loading) }
            try {
                favouriteNewsRepository.getArticles().collect { result ->
                    _state.update {
                        it.copy(articles = Resource.Success(result.toDomainFavoriteModelEntityList()))
                    }
                }
            } catch (e: Exception) {
                _state.update { it.copy(articles = Resource.Error(e.message.toString())) }
            }
        }
    }


    fun onEvent(event: BookMarkScreenEvents) {
        when (event) {
            BookMarkScreenEvents.LoadAlreadyRead -> TODO()
            BookMarkScreenEvents.LoadCurrentlyReading -> TODO()
            BookMarkScreenEvents.LoadHeadlines -> TODO()
        }
    }
}