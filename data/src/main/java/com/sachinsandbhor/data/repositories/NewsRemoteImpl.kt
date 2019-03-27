package com.sachinsandbhor.data.repositories

import com.sachinsandbhor.data.api.RemoteNewsApi
import com.sachinsandbhor.data.mappers.NewsDataToEntityMapper
import com.sachinsandbhor.domain.datastores.DataStore
import com.sachinsandbhor.domain.entities.NewsSourceEntity
import io.reactivex.Flowable

class NewsRemoteImpl(private val api: RemoteNewsApi): DataStore {

    private val newsMapper = NewsDataToEntityMapper()

    override fun getNews(): Flowable<NewsSourceEntity> {
        return api.getNews().map { newsMapper.mapToEntity(it) }
    }
}