package com.mtnine.amuidea.data

import com.mtnine.amuidea.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    @POST("login")
    fun getLoginResponse(@Body user: User): Call<SimpleResponse>

    @POST("account")
    fun createAccount(@Body user: User) : Call<SimpleResponse>

    @POST("addword")
    fun addWord(@Body post: Post) : Call<WordResponse>

    @POST("getword")
    fun getWord(@Body post: Post) : Call<WordResponse>

    @POST("getidea")
    fun getIdea(@Body post: Post) : Call<PostResponse>

    @POST("addidea")
    fun addIdea(@Body post: Post) : Call<SimpleResponse>

    @POST("getstate")
    fun getState(@Body post: Post) : Call<SimpleResponse>
}