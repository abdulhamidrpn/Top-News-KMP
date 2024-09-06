package com.rpn.newskmpapp.presentation.ui.home

import com.rpn.newskmpapp.data.model.Article
import com.rpn.newskmpapp.utils.Resource

data class HomeScreenState(
    val articles: Resource<List<Article>> = Resource.Idle
)

