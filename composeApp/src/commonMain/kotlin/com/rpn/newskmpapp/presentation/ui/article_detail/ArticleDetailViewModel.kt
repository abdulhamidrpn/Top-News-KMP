package com.rpn.newskmpapp.presentation.ui.article_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rpn.newskmpapp.data.mapper.toEntity
import com.rpn.newskmpapp.data.repository.LocalNewsRepository
import com.rpn.newskmpapp.domain.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch


class ArticleDetailViewModel(
    private val localNewsRepository: LocalNewsRepository
) : ViewModel() {

    var isBookmarked by mutableStateOf(false)

    fun isArticleBookmark(currentArticle: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            currentArticle.publishedAt.let {
                localNewsRepository.getArticle(it)?.let {
                    isBookmarked = true
                }
            }
        }
    }

    fun bookmarkArticle(currentArticle: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isBookmarked) {
                localNewsRepository.upsertArticle(currentArticle.toEntity())
            } else {
                localNewsRepository.deleteArticle(currentArticle.toEntity())
            }
            isBookmarked = !isBookmarked
        }
    }

}