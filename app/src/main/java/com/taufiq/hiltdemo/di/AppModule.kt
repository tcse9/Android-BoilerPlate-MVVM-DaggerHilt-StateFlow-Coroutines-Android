package com.taufiq.hiltdemo.di

import android.util.Log
import com.taufiq.hiltdemo.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesUrl() = "https://jsonplaceholder.typicode.com/"

    @Provides
    fun logginIntercepter() = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { msg->
        Log.d("OKHTTP", msg)
    }).setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okhttpClient(interceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder().addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun providesApiService(url:String, client:OkHttpClient) : ApiService =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)

}