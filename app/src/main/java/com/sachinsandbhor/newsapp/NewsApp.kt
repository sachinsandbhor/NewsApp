package com.sachinsandbhor.newsapp

import android.app.Application
import com.sachinsandbhor.newsapp.di.*
import org.koin.android.ext.android.startKoin

class NewsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(this,
            listOf(networkModule,
                viewModelModule,
                repositoriesModule,
                usecasesModule,
                databaseModule)

        )
    }
}