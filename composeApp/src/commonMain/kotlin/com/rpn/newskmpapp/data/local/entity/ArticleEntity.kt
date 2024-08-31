package com.rpn.newskmpapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rpn.newskmpapp.data.remote.dto.SourceDto
import kotlinx.serialization.SerialName

@Entity(tableName = "articleTable")
data class ArticleEntity(
    @SerialName("author")
    val author: String? = null,
    @SerialName("content")
    val content: String?,
    @SerialName("description")
    val description: String?,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "articleId")
    @SerialName("publishedAt")
    val publishedAt: String,
    @SerialName("source")
    val source: SourceDto,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String,
    @SerialName("urlToImage")
    val urlToImage: String?
)