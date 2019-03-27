package com.sachinsandbhor.domain.repositories

import com.sachinsandbhor.domain.entities.NewsSourceEntity
import io.reactivex.Flowable

interface NewsRepository {

    fun getNews(): Flowable<NewsSourceEntity>
    fun getLocalNews(): Flowable<NewsSourceEntity>
    fun getRemoteNews(): Flowable<NewsSourceEntity>

}