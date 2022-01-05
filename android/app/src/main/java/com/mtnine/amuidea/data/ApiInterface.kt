package com.mtnine.amuidea.data

import com.mtnine.amuidea.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("login")
    suspend fun getLoginResponse(@Body user: User): Response<SimpleResponse>

    @POST("account")
    suspend fun createAccount(@Body user: User): Response<SimpleResponse>

    @POST("addword")
    suspend fun addWord(@Body post: Post): Response<WordResponse>

    @POST("getword")
    suspend fun getWord(@Body post: Post): Response<WordResponse>

    @POST("getidea")
    suspend fun getIdea(@Body post: Post): Response<PostResponse>

    @POST("addidea")
    suspend fun addIdea(@Body post: Post): Response<SimpleResponse>

    @POST("getstate")
    suspend fun getState(@Body post: Post): Response<SimpleResponse>
}