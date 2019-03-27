package com.sachinsandbhor.data.repositories

import com.sachinsandbhor.data.db.ArticlesDao
import com.sachinsandbhor.data.db.NewsDatabase
import com.sachinsandbhor.data.mappers.NewsDataToEntityMapper
import com.sachinsandbhor.data.mappers.NewsEntityToDataMapper
import com.sachinsandbhor.domain.datastores.DataStore
import com.sachinsandbhor.domain.entities.NewsSourceEntity
import io.reactivex.Flowable

class NewsCacheImpl(private val entityToDataMapper: NewsEntityToDataMapper,
                    private val dataToEntityMapper: NewsDataToEntityMapper,
                    private val database: NewsDatabase): DataStore {

    private val dao: ArticlesDao = database.getArticlesDao()

    override fun getNews(): Flowable<NewsSourceEntity> {
        return dao.getAllArticles().map { it-> dataToEntityMapper.mapToEntity(it) }
    }

    fun saveArticles(remoteNews: NewsSourceEntity?) {
        dao.clear()
        dao.saveAllArticles(remoteNews?.articles!!.map { articles -> entityToDataMapper.mapArticleToEntity(articles) })
    }
}