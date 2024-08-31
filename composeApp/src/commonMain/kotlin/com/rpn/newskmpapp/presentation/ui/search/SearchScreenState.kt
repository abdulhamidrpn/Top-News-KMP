package com.rpn.newskmpapp.presentation.ui.search

import com.rpn.newskmpapp.domain.model.Article
import com.rpn.newskmpapp.utils.Resource

data class SearchScreenState(
    val searchHistory: List<String> = emptyList(),
    val searchQuery: String = "",
    val articles: Resource<List<Article>> = Resource.Idle
)

