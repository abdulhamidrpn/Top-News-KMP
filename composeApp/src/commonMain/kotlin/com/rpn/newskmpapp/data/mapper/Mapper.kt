package com.rpn.newskmpapp.data.mapper

import com.rpn.newskmpapp.data.local.entity.ArticleEntity
import com.rpn.newskmpapp.data.local.entity.CachedArticleEntity
import com.rpn.newskmpapp.data.local.entity.FavoriteArticleEntity
import com.rpn.newskmpapp.data.model.Article
import com.rpn.newskmpapp.data.remote.dto.ArticleDto
import com.rpn.newskmpapp.data.remote.dto.SourceDto
import com.rpn.newskmpapp.data.remote.news_dto.Article2Dto


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

fun List<Article>.toArticleEntityList(): List<ArticleEntity> {
    return this.map { it.toEntity() }
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
        sourceName = this.source.name ?:""
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
        sourceName = this.source.name ?: "",
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


fun CachedArticleEntity.toDomainModel(): Article {
    return Article(
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description ?: "",
        publishedAt = this.publishedAt ?: "",
        title = this.title ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage,
        sourceName = this.sourceName
    )
}

fun List<CachedArticleEntity>.toDomainCachedModelEntityList(): List<Article> {
    return this.map { it.toDomainModel() }
}

fun Article.toCacheEntity(): CachedArticleEntity {
    return CachedArticleEntity(
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description,
        publishedAt = this.publishedAt ?: "",
        title = this.title ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage,
        sourceName = this.sourceName,
        articleId = this.publishedAt
    )
}

fun List<Article>.toCacheArticleEntityList(): List<CachedArticleEntity> {
    return this.map { it.toCacheEntity() }
}

/*Favourite Article*/
fun Article.toFavouriteEntity(): FavoriteArticleEntity {
    return FavoriteArticleEntity(
        articleId = this.publishedAt,
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description,
        publishedAt = this.publishedAt ?: "",
        title = this.title ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage,
        sourceName = this.sourceName
    )
}

fun List<Article>.toFavouriteEntityList(): List<FavoriteArticleEntity> {
    return this.map { it.toFavouriteEntity() }
}


fun FavoriteArticleEntity.toDomainModel(): Article {
    return Article(
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description ?: "",
        publishedAt = this.publishedAt ?: "",
        title = this.title ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage,
        sourceName = this.sourceName
    )
}

fun List<FavoriteArticleEntity>.toDomainFavoriteModelEntityList(): List<Article> {
    return this.map { it.toDomainModel() }
}