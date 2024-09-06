package com.rpn.newskmpapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.rpn.newskmpapp.data.local.entity.FavoriteArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteArticleDao {

    @Query("SELECT COUNT(*) FROM favoriteArticleTable WHERE articleId = :articleId")
    suspend fun isArticleFavorite(articleId: String): Boolean

    @Upsert
    suspend fun upsert(article: FavoriteArticleEntity)

    @Delete
    suspend fun delete(article: FavoriteArticleEntity)

    @Query("DELETE FROM favoriteArticleTable")
    suspend fun deleteAllBookmark()

    @Query("SELECT * FROM favoriteArticleTable")
    fun getArticles(): Flow<List<FavoriteArticleEntity>>

    @Query("SELECT * FROM favoriteArticleTable WHERE articleId=:articleId")
    suspend fun getArticle(articleId: String): FavoriteArticleEntity?

}
