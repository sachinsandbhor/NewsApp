package com.sachinsandbhor.domain.usecases

import com.sachinsandbhor.domain.common.BaseFlowableUseCase
import com.sachinsandbhor.domain.common.FlowableRxTransformer
import com.sachinsandbhor.domain.entities.NewsSourceEntity
import com.sachinsandbhor.domain.repositories.NewsRepository
import io.reactivex.Flowable

class GetNewsUsecase(private val transformer: FlowableRxTransformer<NewsSourceEntity>,
                     private val newsRepository: NewsRepository) : BaseFlowableUseCase<NewsSourceEntity>(transformer) {

    override fun createFlowable(data: HashMap<String, Any>?): Flowable<NewsSourceEntity> {
        return newsRepository.getNews()
    }

    fun getNews(): Flowable<NewsSourceEntity> {
        val data = HashMap<String, Any>()
        return single(data)
    }
}