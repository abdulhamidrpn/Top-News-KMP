package com.rpn.newskmpapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rpn.newskmpapp.data.local.entity.CachedArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CachedArticleDao {

    @Query("SELECT * FROM cachedArticleTable")
    fun getAllCachedArticles(): Flow<List<CachedArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<CachedArticleEntity>)

    @Query("DELETE FROM cachedArticleTable")
    suspend fun clearCachedArticles()
}
