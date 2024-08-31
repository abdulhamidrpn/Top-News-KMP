package com.rpn.newskmpapp.domain.model

import com.rpn.newskmpapp.utils.getRandomId
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val author: String,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String?,
    val sourceName: String
){
    val id: String = getRandomId()
}
