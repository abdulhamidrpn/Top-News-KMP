package com.rpn.newskmpapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cachedArticleTable")
data class CachedArticleEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "articleId")
    val articleId: String,
    val author: String? = null,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String?,
    val sourceName: String
)
