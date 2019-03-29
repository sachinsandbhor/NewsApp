package com.sachinsandbhor.newsapp.di

import androidx.room.Room
import com.sachinsandbhor.data.api.RemoteNewsApi
import com.sachinsandbhor.data.db.NewsDatabase
import com.sachinsandbhor.data.mappers.NewsDataToEntityMapper
import com.sachinsandbhor.data.mappers.NewsEntityToDataMapper
import com.sachinsandbhor.data.repositories.NewsCacheImpl
import com.sachinsandbhor.data.repositories.NewsRemoteImpl
import com.sachinsandbhor.data.repositories.NewsRepositoryImpl
import com.sachinsandbhor.domain.repositories.NewsRepository
import com.sachinsandbhor.domain.usecases.GetNewsUsecase
import com.sachinsandbhor.newsapp.BuildConfig
import com.sachinsandbhor.newsapp.common.AsyncFlowableTransformer
import com.sachinsandbhor.newsapp.mappers.NewsEntityMapper
import com.sachinsandbhor.newsapp.views.newslist.NewsListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_NEWS_USECASE = "getNewsUseCase"
private const val REMOTE = "remote response"
private const val DATABASE = "database"

val networkModule = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BuildConfig.BASE_URL) }
    single(name = API) { (get(RETROFIT_INSTANCE) as Retrofit).create(RemoteNewsApi::class.java) }
}

val databaseModule = module {
    single(name = DATABASE) {
        Room.databaseBuilder(androidApplication(), NewsDatabase::class.java, "news_articles").build()
    }
}

val viewModelModule = module {
    viewModel { NewsListViewModel(getNewsCases = get(GET_NEWS_USECASE), mapper = NewsEntityMapper()) }
}

val usecasesModule = module {
    factory(name = "getNewsUseCase") {
        GetNewsUsecase(
            transformer = AsyncFlowableTransformer(),
            newsRepository = get()
        )
    }
}

val repositoriesModule = module {
    single(name = "remote") {NewsRemoteImpl(api = get(API))}
    single(name = "local") {
        NewsCacheImpl(database = get(DATABASE), dataToEntityMapper = NewsDataToEntityMapper(), entityToDataMapper = NewsEntityToDataMapper())
    }
    single { NewsRepositoryImpl(remote = get("remote"), cache = get("local")) as NewsRepository}
}