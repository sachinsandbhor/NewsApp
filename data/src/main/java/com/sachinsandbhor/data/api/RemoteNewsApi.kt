package com.sachinsandbhor.data.api

import com.sachinsandbhor.data.entities.NewsData
import io.reactivex.Flowable
import retrofit2.http.GET

interface RemoteNewsApi {

    @GET("top-headlines?country=in")
    fun getNews(): Flowable<NewsData>
}