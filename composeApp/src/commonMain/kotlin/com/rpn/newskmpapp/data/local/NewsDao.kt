package com.rpn.newskmpapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rpn.newskmpapp.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: ArticleEntity)

    @Delete
    suspend fun delete(article: ArticleEntity)

    @Query("DELETE FROM articleTable")
    suspend fun deleteAllBookmark()

    @Query("SELECT * FROM articleTable")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articleTable WHERE articleId=:articleId")
    suspend fun getArticle(articleId: String): ArticleEntity?

}