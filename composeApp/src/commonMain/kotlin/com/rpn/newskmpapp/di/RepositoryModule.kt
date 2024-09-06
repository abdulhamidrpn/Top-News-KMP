package com.rpn.newskmpapp.di

import com.rpn.newskmpapp.data.local.NewsDatabase
import com.rpn.newskmpapp.data.repository.FavouriteNewsRepositoryImpl
import com.rpn.newskmpapp.data.repository.NewsRepositoryImpl
import com.rpn.newskmpapp.domain.repository.FavouriteNewsRepository
import com.rpn.newskmpapp.domain.repository.NewsRepository
import com.rpn.newskmpapp.domain.use_case.FormatDateTimeUseCase
import com.rpn.newskmpapp.domain.use_case.GetHeadLinesUseCase
import com.rpn.newskmpapp.domain.use_case.GetSearchDataUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module


val repositoryModule = module {

    single<FavouriteNewsRepository> {
        FavouriteNewsRepositoryImpl(get<NewsDatabase>().favouriteArticleDao())
    }
    single<NewsRepository> {
        NewsRepositoryImpl(
            get(named("ClientA")),
            get(named("ClientB")),
            get<NewsDatabase>().cachedArticleDao()
        )
    }
}

val domainModule = module {
    factory<FormatDateTimeUseCase> { FormatDateTimeUseCase() }
    factory<GetHeadLinesUseCase> { GetHeadLinesUseCase(get(), get()) }
    factory<GetSearchDataUseCase> { GetSearchDataUseCase(get(), get()) }
}
