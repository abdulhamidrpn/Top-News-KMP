package com.rpn.newskmpapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceDto,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)