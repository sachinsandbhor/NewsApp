package com.sachinsandbhor.domain.datastores

import com.sachinsandbhor.domain.entities.NewsSourceEntity
import io.reactivex.Flowable

interface DataStore {
    fun getNews(): Flowable<NewsSourceEntity>
}