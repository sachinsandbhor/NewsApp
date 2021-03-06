package com.sachinsandbhor.newsapp.di

import com.sachinsandbhor.newsapp.BuildConfig
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.URL
import java.util.concurrent.TimeUnit

fun createNetworkClient(baseUrl: String) = retrofitClient(httpClient())!!

fun httpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val hostname = URL(BuildConfig.BASE_URL).host
    val certificatePinner = CertificatePinner.Builder()
        .add(hostname, "sha256/yt4CKQurqpgR8jLYJMvItV4WO3XjCWG/O6FhBxByjk8=")
        .add(hostname, "sha256/WGJkyYjx1QMdMe0UqlyOKXtydPDVrk7sl2fV+nNm1r4=")
        .build()
    val clientBuilder = OkHttpClient.Builder()
    clientBuilder.certificatePinner(certificatePinner)
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
    clientBuilder.addInterceptor(BasicAuthInterceptor())
    clientBuilder.readTimeout(120, TimeUnit.SECONDS)
    clientBuilder.writeTimeout(120, TimeUnit.SECONDS)
    return clientBuilder.build()
}

private fun retrofitClient(httpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()


class BasicAuthInterceptor: Interceptor{
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newsUrl = request.url().newBuilder().addQueryParameter("apiKey", BuildConfig.API_KEY).build()
        val newRequest = request.newBuilder().url(newsUrl).build()
        return chain.proceed(newRequest)
    }

}