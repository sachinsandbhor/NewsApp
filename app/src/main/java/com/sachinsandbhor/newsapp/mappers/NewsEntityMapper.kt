package com.sachinsandbhor.newsapp.mappers

import com.sachinsandbhor.domain.common.Mapper
import com.sachinsandbhor.domain.entities.ArticleEntity
import com.sachinsandbhor.newsapp.entities.Article as PresentationArticle
import com.sachinsandbhor.domain.entities.NewsSourceEntity
import com.sachinsandbhor.newsapp.entities.News

class NewsEntityMapper: Mapper<NewsSourceEntity, News>() {
    override fun mapFrom(from: NewsSourceEntity): News =
        News(
            articles = mapListArticlesToPresentation(from.articles),
            status = from?.status
        )

    private fun mapListArticlesToPresentation(articles: List<ArticleEntity>): List<PresentationArticle> =
            articles.map { mapArticlesToPresentation(it) }

    private fun mapArticlesToPresentation(response: ArticleEntity): PresentationArticle = PresentationArticle(
        author = response.author,
        content = response.content,
        description = response.description,
        publishedAt = response.publishedAt,
        title = response.title,
        url = response.url,
        urlToImage = response.urlToImage
    )

}