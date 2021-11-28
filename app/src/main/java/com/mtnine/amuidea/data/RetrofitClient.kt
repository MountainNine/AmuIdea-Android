package com.mtnine.amuidea.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val baseUrl = "https://a5mppwt298.execute-api.ap-northeast-2.amazonaws.com/amuidea/"

    val retrofitClient: Retrofit.Builder by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient
            .build()
            .create(ApiInterface::class.java)
    }
}