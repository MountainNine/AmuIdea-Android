package com.mtnine.amuidea.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface InitApi {
    @POST("login")
    fun getLoginResponse(@Body loginRequest : LoginRequest) : Call<LoginResponse>
}