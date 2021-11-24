package com.mtnine.amuidea.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.data.RetrofitClient
import com.mtnine.amuidea.model.LoginResponse
import com.mtnine.amuidea.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {
    val userLogin: MutableLiveData<LoginResponse> = MutableLiveData<LoginResponse>()

    fun callLogin(id: String, pw: String) : MutableLiveData<LoginResponse> {
        val call = RetrofitClient.apiInterface.getLoginResponse(User(id, pw))
        call.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val data = response.body()!!
                val statusCode = data.statusCode
                val body = data.token
                userLogin.value = LoginResponse(statusCode, body)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return userLogin
    }
}