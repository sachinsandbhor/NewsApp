package com.sachinsandbhor.data.repositories

import com.sachinsandbhor.domain.entities.NewsSourceEntity
import com.sachinsandbhor.domain.repositories.NewsRepository
import io.reactivex.Flowable

class NewsRepositoryImpl(private val cache: NewsCacheImpl,
                         private val remote: NewsRemoteImpl): NewsRepository {

    override fun getNews(): Flowable<NewsSourceEntity> {
        val updateNewsFlowable = remote.getNews()
        return cache.getNews()
            .mergeWith(updateNewsFlowable.doOnNext{
                remoteNews -> cache.saveArticles(remoteNews)
            })
    }

    override fun getLocalNews(): Flowable<NewsSourceEntity> {
        return cache.getNews()
    }

    override fun getRemoteNews(): Flowable<NewsSourceEntity> {
        return remote.getNews()
    }
}