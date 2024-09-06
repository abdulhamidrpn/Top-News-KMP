package com.rpn.newskmpapp.domain.repository

import com.rpn.newskmpapp.data.local.entity.FavoriteArticleEntity
import kotlinx.coroutines.flow.Flow

interface FavouriteNewsRepository {

    fun getArticles(): Flow<List<FavoriteArticleEntity>>
    suspend fun upsertArticle(article: FavoriteArticleEntity)
    suspend fun deleteArticle(article: FavoriteArticleEntity)
    suspend fun isArticleFavorite(articleId: String): Boolean
    suspend fun getArticle(articleId: String): FavoriteArticleEntity?
    suspend fun deleteAllBookmark()
}