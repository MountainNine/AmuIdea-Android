package com.mtnine.amuidea.data

import com.mtnine.amuidea.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("login")
    fun getLoginResponse(@Body user: User): Call<SimpleResponse>

    @POST("account")
    fun createAccount(@Body user: User) : Call<SimpleResponse>

    @POST("getword")
    fun getWord() : Call<WordResponse>

    @POST("getideas")
    fun getIdeas(id: String) : Call<PostResponse>

    @POST("addidea")
    fun addIdea(id: String, @Body post: Post) : Call<SimpleResponse>
}