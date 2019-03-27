package com.sachinsandbhor.newsapp.entities

data class News(
    var articles: List<Article> = emptyList(),
    var status: String? = null
)