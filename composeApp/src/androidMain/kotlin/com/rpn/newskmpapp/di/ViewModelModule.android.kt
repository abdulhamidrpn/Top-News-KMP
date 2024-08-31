package com.rpn.newskmpapp.di

import com.rpn.newskmpapp.presentation.ui.article_detail.ArticleDetailViewModel
import com.rpn.newskmpapp.presentation.ui.bookmark.BookMarkViewModel
import com.rpn.newskmpapp.presentation.ui.home.HomeViewModel
import com.rpn.newskmpapp.presentation.ui.search.SearchViewModel
import com.rpn.newskmpapp.presentation.ui.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val viewmodelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookMarkViewModel)
    viewModelOf(::ArticleDetailViewModel)
    viewModelOf(::SettingViewModel)
}
