package com.sachinsandbhor.data.entities

import com.google.gson.annotations.SerializedName

data class NewsData(
    @SerializedName("articles")var articles: List<ArticleData> = emptyList(),
    @SerializedName("status") var status: String? = null
)