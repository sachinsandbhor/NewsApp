package com.sachinsandbhor.domain.entities

data class NewsSourceEntity(
    var articles: List<ArticleEntity> = emptyList(),
    var status: String? = null,
    var totalResults: Int = 0
)