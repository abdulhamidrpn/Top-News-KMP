package com.rpn.newskmpapp.di

import com.rpn.newskmpapp.presentation.ui.article_detail.ArticleDetailViewModel
import com.rpn.newskmpapp.presentation.ui.bookmark.BookMarkViewModel
import com.rpn.newskmpapp.presentation.ui.home.HomeViewModel
import com.rpn.newskmpapp.presentation.ui.search.SearchViewModel
import com.rpn.newskmpapp.presentation.ui.setting.SettingViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val viewmodelModule = module {
    singleOf(::HomeViewModel)
    singleOf(::SearchViewModel)
    singleOf(::BookMarkViewModel)
    singleOf(::ArticleDetailViewModel)
    singleOf(::SettingViewModel)
}
