package com.mtnine.amuidea.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface InitApi {
    @POST("amuidea")
    fun getApiResponse(@Body apiRequest : ApiRequest) : Call<ApiResponse>
}