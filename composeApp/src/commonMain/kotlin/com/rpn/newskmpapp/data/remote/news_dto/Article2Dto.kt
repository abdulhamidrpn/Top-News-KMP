package com.rpn.newskmpapp.data.remote.news_dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article2Dto(
    @SerialName("ai_org")
    val aiOrg: String?,
    @SerialName("ai_region")
    val aiRegion: String?,
    @SerialName("ai_tag")
    val aiTag: String?,
    @SerialName("article_id")
    val articleId: String?,
    @SerialName("category")
    val category: List<String>,
    @SerialName("content")
    val content: String?,
    @SerialName("country")
    val country: List<String>?,
    @SerialName("creator")
    val creator: List<String>?,
    @SerialName("description")
    val description: String?,
    @SerialName("duplicate")
    val duplicate: Boolean,
    @SerialName("image_url")
    val imageUrl: String?,
    @SerialName("keywords")
    val keywords: List<String>?,
    @SerialName("language")
    val language: String?,
    @SerialName("link")
    val link: String?,
    @SerialName("pubDate")
    val pubDate: String?,
    @SerialName("sentiment")
    val sentiment: String?,
    @SerialName("sentiment_stats")
    val sentimentStats: String?,
    @SerialName("source_icon")
    val sourceIcon: String?,
    @SerialName("source_id")
    val sourceId: String?,
    @SerialName("source_name")
    val sourceName: String?,
    @SerialName("source_priority")
    val sourcePriority: Int,
    @SerialName("source_url")
    val sourceUrl: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("video_url")
    val videoUrl: String?
)