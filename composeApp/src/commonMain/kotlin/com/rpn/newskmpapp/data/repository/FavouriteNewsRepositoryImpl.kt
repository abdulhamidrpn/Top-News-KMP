package com.rpn.newskmpapp.data.repository

import com.rpn.newskmpapp.data.local.dao.FavoriteArticleDao
import com.rpn.newskmpapp.data.local.entity.FavoriteArticleEntity
import com.rpn.newskmpapp.domain.repository.FavouriteNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flowOn

class FavouriteNewsRepositoryImpl(
    private val newsDao: FavoriteArticleDao
) : FavouriteNewsRepository {
    override suspend fun upsertArticle(article: FavoriteArticleEntity) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: FavoriteArticleEntity) {
        newsDao.delete(article)
    }

    override suspend fun deleteAllBookmark() {
        newsDao.deleteAllBookmark()
    }

    override fun getArticles() = newsDao.getArticles().flowOn(Dispatchers.IO)


    override suspend fun isArticleFavorite(articleId: String): Boolean {
        return newsDao.isArticleFavorite(articleId)
    }

    override suspend fun getArticle(articleId: String): FavoriteArticleEntity? {
        return newsDao.getArticle(articleId = articleId)
    }
}