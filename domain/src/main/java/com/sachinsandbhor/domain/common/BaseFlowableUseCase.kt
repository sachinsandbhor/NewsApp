package com.sachinsandbhor.domain.common

import io.reactivex.Flowable

abstract class BaseFlowableUseCase<T>(private val transformer: FlowableRxTransformer<T>) {

    abstract fun createFlowable(data: HashMap<String, Any>? = null): Flowable<T>

    fun single(withData: HashMap<String, Any>? = null): Flowable<T> {
        return createFlowable(withData).compose(transformer)
    }
}