package com.sachinsandbhor.data.mappers

import com.sachinsandbhor.data.entities.ArticleData
import com.sachinsandbhor.data.entities.NewsData
import com.sachinsandbhor.domain.entities.ArticleEntity
import com.sachinsandbhor.domain.entities.NewsSourceEntity

class NewsEntityToDataMapper {

    fun mapToEntity(data: NewsSourceEntity?): NewsData? = NewsData(
        status = data?.status,
        articles = mapListArticlesToEntity(data?.articles)
    )

    fun mapListArticlesToEntity(articles: List<ArticleEntity>?)
            : List<ArticleData> = articles?.map { mapArticleToEntity(it) } ?: emptyList()

    fun mapArticleToEntity(response: ArticleEntity): ArticleData = ArticleData(
        author = response.author,
        content = response.content,
        description = response.description,
        publishedAt = response.publishedAt,
        title = response.title,
        url = response.url,
        urlToImage = response.urlToImage
    )
}