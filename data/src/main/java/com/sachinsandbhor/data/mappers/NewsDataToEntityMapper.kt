package com.sachinsandbhor.data.mappers

import com.sachinsandbhor.data.entities.ArticleData
import com.sachinsandbhor.data.entities.NewsData
import com.sachinsandbhor.domain.entities.ArticleEntity
import com.sachinsandbhor.domain.entities.NewsSourceEntity

class NewsDataToEntityMapper {

    fun mapArticleToEntity(response: ArticleData) = ArticleEntity(
        author = response.author,
        content = response.content,
        description = response.description,
        publishedAt = response.publishedAt,
        title = response.title,
        url = response.url,
        urlToImage = response.urlToImage
    )

    fun mapToEntity(data: NewsData): NewsSourceEntity = NewsSourceEntity(
        status = data.status,
        articles = mapListArticlesToEntity(data.articles)
    )

    fun mapToEntity(data: List<ArticleData>): NewsSourceEntity = NewsSourceEntity(
        articles = mapListArticlesToEntity(data)
    )

    fun mapListArticlesToEntity(articles: List<ArticleData>): List<ArticleEntity> =
        articles.map { mapArticleToEntity(it) } ?: emptyList()

}
