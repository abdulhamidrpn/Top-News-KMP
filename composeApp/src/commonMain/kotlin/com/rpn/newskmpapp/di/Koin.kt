package com.rpn.newskmpapp.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            networkModule,
            databaseModule,
            repositoryModule,
            domainModule,
            viewmodelModule,
        )
    }