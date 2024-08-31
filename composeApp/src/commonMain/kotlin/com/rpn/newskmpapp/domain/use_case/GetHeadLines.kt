package com.rpn.newskmpapp.domain.use_case


import com.rpn.newskmpapp.domain.model.Article
import com.rpn.newskmpapp.domain.repository.NewsRepository
import com.rpn.newskmpapp.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

class GetHeadLines(
    private val repository: NewsRepository
) {

    suspend operator fun invoke(): Flow<DataState<List<Article>>> {
        return repository.getHeadLines()
    }
}