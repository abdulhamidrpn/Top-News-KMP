package com.rpn.newskmpapp.presentation.ui.search

sealed class    SearchScreenEvents {
    data class OnSearchClicked(val query: String) : SearchScreenEvents()
    data class OnSearchQueryChange(val query: String) : SearchScreenEvents()
    data object OnBackClicked : SearchScreenEvents()
    data object OnRemoveHistory : SearchScreenEvents()
}

