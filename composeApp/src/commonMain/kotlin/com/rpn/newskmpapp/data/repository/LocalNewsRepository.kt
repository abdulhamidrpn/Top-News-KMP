package com.rpn.newskmpapp.data.repository

import com.rpn.newskmpapp.data.local.NewsDao
import com.rpn.newskmpapp.data.local.entity.ArticleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn

class LocalNewsRepository(
    private val newsDao: NewsDao
) {
    suspend fun upsertArticle(article: ArticleEntity) {
        newsDao.upsert(article)
    }

    suspend fun deleteArticle(article: ArticleEntity) {
        newsDao.delete(article)
    }
    suspend fun deleteAllBookmark() {
        newsDao.deleteAllBookmark()
    }

    fun getArticles() = newsDao.getArticles().flowOn(Dispatchers.IO)

    suspend fun getArticle(articleId: String): ArticleEntity? {
        return newsDao.getArticle(articleId = articleId)
    }
}