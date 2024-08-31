package com.rpn.newskmpapp.data.remote.news_dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse2Dto(
    @SerialName("nextPage")
    val nextPage: String = "",
    @SerialName("results")
    val results: List<Article2Dto> = emptyList(),
    @SerialName("status")
    val status: String = "",
    @SerialName("totalResults")
    val totalResults: Int = 0
)