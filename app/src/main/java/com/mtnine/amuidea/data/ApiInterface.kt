package com.mtnine.amuidea.data

import com.mtnine.amuidea.model.LoginResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @POST("login")
    fun getLoginResponse(
        @Query("id") id: String,
        @Query("pw") pw: String
    ): Call<LoginResponse>
}