package com.rpn.newskmpapp.presentation.ui.article_detail

sealed class ArticleDetailScreenEvents {
    data object OnBookmarkClick : ArticleDetailScreenEvents()
    data object OnShareClick : ArticleDetailScreenEvents()
    data object OnBrowseClick : ArticleDetailScreenEvents()
}

