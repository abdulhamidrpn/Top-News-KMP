package com.rpn.newskmpapp.presentation.ui.bookmark

import com.rpn.newskmpapp.data.model.Article
import com.rpn.newskmpapp.utils.Resource

data class BookMarkScreenState(
    val articles: Resource<List<Article>> = Resource.Idle
)

