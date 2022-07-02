package org.techtown.recyclerview

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

object ApiManager {
    private lateinit var instance : ApiService

    fun getIntance() : ApiService{
        if(!::instance.isInitialized){
            instance = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(
                    OkHttpClient.Builder()
                            // gradle 작업 필요
                        .addInterceptor(HttpLoggingInterceptor().apply{
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
        return instance
    }
}