package com.rpn.newskmpapp.domain.repository

import com.rpn.newskmpapp.data.model.Article
import com.rpn.newskmpapp.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getSearchResults(query: String): Flow<DataState<List<Article>>>
    suspend fun getHeadLines(): Flow<DataState<List<Article>>>
}