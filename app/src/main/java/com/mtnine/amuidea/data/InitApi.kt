package com.mtnine.amuidea.data

import com.mtnine.amuidea.model.LoginRequest
import com.mtnine.amuidea.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface InitApi {
    @POST("login")
    fun getLoginResponse(@Body loginRequest : LoginRequest) : Call<LoginResponse>
}