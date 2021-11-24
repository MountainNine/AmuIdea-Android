package com.mtnine.amuidea.data

import com.mtnine.amuidea.model.LoginResponse
import com.mtnine.amuidea.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("login")
    fun getLoginResponse(@Body user: User): Call<LoginResponse>
}