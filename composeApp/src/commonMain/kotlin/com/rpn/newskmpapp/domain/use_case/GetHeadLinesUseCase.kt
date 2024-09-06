package com.rpn.newskmpapp.domain.use_case


import com.rpn.newskmpapp.data.model.Article
import com.rpn.newskmpapp.domain.repository.NewsRepository
import com.rpn.newskmpapp.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHeadLinesUseCase(
    private val repository: NewsRepository,
    private val formatDateTimeUseCase: FormatDateTimeUseCase
) {

    suspend operator fun invoke(): Flow<DataState<List<Article>>> {

        return repository.getHeadLines().map { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    val articlesWithFormattedDates = dataState.data.map { article ->
                        article.copy(
                            formatPublishedAt = "Published: " + formatDateTimeUseCase.execute(
                                article.publishedAt
                            )
                        )
                    }
                    DataState.Success(articlesWithFormattedDates)
                }

                is DataState.Error -> dataState
                is DataState.Loading -> dataState
            }
        }
    }
}
