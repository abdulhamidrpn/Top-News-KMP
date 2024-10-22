package com.rpn.newskmpapp.domain.use_case


import com.rpn.newskmpapp.data.model.Article
import com.rpn.newskmpapp.data.utils.Constants.prohibitedKeywords
import com.rpn.newskmpapp.domain.repository.NewsRepository
import com.rpn.newskmpapp.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetSearchDataUseCase(
    private val repository: NewsRepository,
    private val formatDateTimeUseCase: FormatDateTimeUseCase
) {

    suspend operator fun invoke(query: String): Flow<DataState<List<Article>>> {
        // Validate the query string
        return if (isQueryValid(query)) {
            repository.getSearchResults(query).map { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        val articlesWithFormattedDates = dataState.data.map { article ->
                            article.copy(
                                formatPublishedAt = "Published: "+ formatDateTimeUseCase.execute(article.publishedAt)
                            )
                        }
                        DataState.Success(articlesWithFormattedDates)
                    }

                    is DataState.Error -> dataState
                    is DataState.Loading -> dataState
                }
            }
        } else {
            // Return an error state if the query contains prohibited words
            flow {
                emit(DataState.Error("The query contains prohibited words."))
            }
        }
    }

    // Function to check if the query is valid
    private fun isQueryValid(query: String): Boolean {
        return prohibitedKeywords.none { word -> query.contains(word, ignoreCase = true) }
    }
}