package com.rpn.newskmpapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseDto(
    val articles: List<ArticleDto> = emptyList(),
    val status: String = "",
    val totalResults: Int = 0
)