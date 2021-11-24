package com.mtnine.amuidea.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        initApi = retrofit.create(InitApi::class.java)
    }

    companion object {
        private var instance: RetrofitClient? = null
        private var initApi: InitApi? = null

        fun getInstance(): RetrofitClient {
            if (instance == null) {
                instance = RetrofitClient()
            }
            return instance as RetrofitClient
        }

        fun getRetrofitInterface(): InitApi? {
            return initApi
        }

        const val baseUrl = "https://y2xjj9oina.execute-api.ap-northeast-2.amazonaws.com/amuidea/"
    }


}