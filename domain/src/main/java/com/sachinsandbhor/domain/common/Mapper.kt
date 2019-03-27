package com.sachinsandbhor.domain.common

import io.reactivex.Flowable

abstract class Mapper<in T,E> {

    abstract fun mapFrom(from: T): E

    fun flowable(from: T) = Flowable.fromCallable{mapFrom(from)}

    fun flowable(from: List<T>) = Flowable.fromCallable { from.map { mapFrom(it) } }
}