package com.rpn.newskmpapp.data.repository

import com.rpn.newskmpapp.data.mapper.toDomainModel2List
import com.rpn.newskmpapp.data.mapper.toDomainModelList
import com.rpn.newskmpapp.data.remote.dto.NewsResponseDto
import com.rpn.newskmpapp.data.remote.news_dto.NewsResponse2Dto
import com.rpn.newskmpapp.data.utils.Constants.API_KEY
import com.rpn.newskmpapp.data.utils.Constants.API_KEY_2
import com.rpn.newskmpapp.domain.model.Article
import com.rpn.newskmpapp.domain.repository.NewsRepository
import com.rpn.newskmpapp.domain.utils.DataState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val clientA: HttpClient,
    private val clientB: HttpClient
) : NewsRepository {

    override suspend fun getSearchResults(query: String): Flow<DataState<List<Article>>> = flow {
        try {
            emit(DataState.Loading)
            val responseA = try {
                clientA.get("everything") {
                    parameter("q", query)
                    parameter("apiKey", API_KEY)
                }.body<NewsResponseDto>()
            } catch (e: Exception) {
                NewsResponseDto()
            }

            val responseB = try {
                clientB.get("latest") {
                    parameter("country", "bd")
                    parameter("q", query)
                    parameter("apiKey", API_KEY_2)
                }.body<NewsResponse2Dto>()
            } catch (e: Exception) {
                NewsResponse2Dto()
            }

            val articlesA = responseA.articles.toDomainModelList()
            val articlesB = responseB.results.toDomainModel2List()
            val combinedArticles = articlesB + articlesA

            if (combinedArticles.isEmpty()) {
                emit(DataState.Error("No results found"))
                return@flow
            }
            emit(DataState.Success(combinedArticles))
        } catch (e: Exception) {
            emit(DataState.Error(e.message ?: "Unknown error"))
        }
    }

    override suspend fun getHeadLines(): Flow<DataState<List<Article>>> = flow {
        try {
            emit(DataState.Loading)

            val responseA = clientA.get("top-headlines") {
                parameter("country", "us")
                parameter("apiKey", API_KEY)
            }.body<NewsResponseDto>()

            val responseB = clientB.get("latest") {
                parameter("country", "bd")
                parameter("apiKey", API_KEY_2)
            }.body<NewsResponse2Dto>()

            val articlesA = responseA.articles.toDomainModelList()
            val articlesB = responseB.results.toDomainModel2List()
            val combinedArticles = articlesB + articlesA

            emit(DataState.Success(combinedArticles))
        } catch (e: Exception) {
            emit(DataState.Error(e.message ?: "Unknown error"))
        }
    }
}