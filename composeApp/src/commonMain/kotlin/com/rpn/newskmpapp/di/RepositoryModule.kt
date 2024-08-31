package com.rpn.newskmpapp.di

import com.rpn.newskmpapp.data.local.NewsDatabase
import com.rpn.newskmpapp.data.repository.LocalNewsRepository
import com.rpn.newskmpapp.data.repository.NewsRepositoryImpl
import com.rpn.newskmpapp.domain.repository.NewsRepository
import com.rpn.newskmpapp.domain.use_case.GetHeadLines
import com.rpn.newskmpapp.domain.use_case.GetSearchData
import org.koin.core.qualifier.named
import org.koin.dsl.module


val repositoryModule = module {

    single {
        LocalNewsRepository(get<NewsDatabase>().newsDao())
    }
    single<NewsRepository> { NewsRepositoryImpl(get(named("ClientA")), get(named("ClientB"))) }
}

val domainModule = module {
    factory<GetHeadLines> { GetHeadLines(get()) }
    factory<GetSearchData> { GetSearchData(get()) }
}
