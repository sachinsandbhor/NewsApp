package com.sachinsandbhor.newsapp.newslist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sachinsandbhor.domain.common.Mapper
import com.sachinsandbhor.domain.entities.NewsSourceEntity
import com.sachinsandbhor.domain.usecases.GetNewsUsecase
import com.sachinsandbhor.newsapp.common.BaseViewModel
import com.sachinsandbhor.newsapp.entities.*

class NewsListViewModel(
    private val getNewsCases: GetNewsUsecase,
    private val mapper: Mapper<NewsSourceEntity, News>
) : BaseViewModel() {
    companion object {
        val TAG: String = NewsListViewModel::class.java.simpleName
    }

    var newsMutableList = MutableLiveData<Data<News>>()

    fun fetchNews() {
        val disposable = getNewsCases.getNews()
            .flatMap { mapper.flowable(it) }
            .subscribe({ response ->
                Log.e(TAG, "on next called")
                newsMutableList.value = Data(responseType = Status.SUCCESS, data = response)
            }, { error ->
                Log.d(TAG, "on error called")
                newsMutableList.value = Data(responseType = Status.ERROR, error = Error(error.message))
            }, {
                Log.d(TAG, "on complete")
            })

        addDisposable(disposable)
    }

    fun getNewsLiveData() = newsMutableList
}