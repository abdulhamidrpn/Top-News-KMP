package com.rpn.newskmpapp.presentation.ui.article_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.rpn.newskmpapp.data.mapper.toFavouriteEntity
import com.rpn.newskmpapp.data.model.Article
import com.rpn.newskmpapp.domain.repository.FavouriteNewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch


class ArticleDetailViewModel(
    private val favouriteNewsRepository: FavouriteNewsRepository
) : ViewModel() {
    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    var isBookmarked by mutableStateOf(false)

    fun isArticleBookmark(currentArticle: Article) {
        viewModelScope.launch {
            println("currentArticle isBookmarked previous: $isBookmarked")
            isBookmarked = favouriteNewsRepository.isArticleFavorite(currentArticle.publishedAt)
            println("currentArticle isBookmarked after: $isBookmarked")
        }
    }

    fun bookmarkArticle(currentArticle: Article) {
        viewModelScope.launch {
            if (!isBookmarked) {
                favouriteNewsRepository.upsertArticle(currentArticle.toFavouriteEntity())
            } else {
                favouriteNewsRepository.deleteArticle(currentArticle.toFavouriteEntity())
            }
            isBookmarked = !isBookmarked
        }
    }

}