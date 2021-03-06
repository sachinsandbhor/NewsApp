package com.sachinsandbhor.domain.usecases

import com.sachinsandbhor.domain.common.BaseFlowableUseCase
import com.sachinsandbhor.domain.common.FlowableRxTransformer
import com.sachinsandbhor.domain.entities.NewsSourceEntity
import com.sachinsandbhor.domain.repositories.NewsRepository
import io.reactivex.Flowable

class GetRemoteNewsUsecase(private val transformer: FlowableRxTransformer<NewsSourceEntity>,
                           private val repository: NewsRepository) :
    BaseFlowableUseCase<NewsSourceEntity>(transformer) {

    override fun createFlowable(data: HashMap<String, Any>?): Flowable<NewsSourceEntity> {
        return repository.getRemoteNews()
    }

    fun getRemoteNews(): Flowable<NewsSourceEntity> {
        val data = HashMap<String, Any>()
        return single(data)
    }
}