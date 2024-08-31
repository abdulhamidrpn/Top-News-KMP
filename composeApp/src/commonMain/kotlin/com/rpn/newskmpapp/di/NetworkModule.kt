package com.rpn.newskmpapp.di

import com.rpn.newskmpapp.data.utils.Constants
import com.rpn.newskmpapp.data.utils.getHttpClient
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single(named("ClientA")) { getHttpClient(Constants.BASE_URL) }
    single(named("ClientB")) { getHttpClient(Constants.BASE_URL_2) }
}