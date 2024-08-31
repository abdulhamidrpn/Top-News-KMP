package com.rpn.newskmpapp.data.mapper

import com.rpn.newskmpapp.data.local.entity.ArticleEntity
import com.rpn.newskmpapp.data.remote.dto.ArticleDto
import com.rpn.newskmpapp.data.remote.dto.SourceDto
import com.rpn.newskmpapp.data.remote.news_dto.Article2Dto
import com.rpn.newskmpapp.domain.model.Article


fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description,
        publishedAt = this.publishedAt ?: "",
        title = this.title ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage,
        source = SourceDto(this.sourceName)
    )
}


fun ArticleEntity.toDomainModel(): Article {
    return Article(
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description ?: "",
        publishedAt = this.publishedAt ?: "",
        title = this.title ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage,
        sourceName = this.source.name
    )
}

fun List<ArticleEntity>.toDomainModelEntityList(): List<Article> {
    return this.map { it.toDomainModel() }
}

fun ArticleDto.toDomainModel(): Article {
    return Article(
        author = this.author ?: "",
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt ?: "",
        title = this.title ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage,
        sourceName = this.source.name
    )
}

fun List<ArticleDto>.toDomainModelList(): List<Article> {
    return this.map { it.toDomainModel() }
}

fun Article2Dto.toDomainModel(): Article {
    return Article(
        author = this.creator?.firstOrNull() ?: "",
        content = this.content,
        description = this.description,
        publishedAt = this.pubDate ?: "",
        title = this.title ?: "",
        url = this.link ?: "",
        urlToImage = this.imageUrl,
        sourceName = this.sourceName ?: ""
    )
}

fun List<Article2Dto>.toDomainModel2List(): List<Article> {
    return this.map { it.toDomainModel() }
}
