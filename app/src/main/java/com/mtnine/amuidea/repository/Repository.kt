package com.mtnine.amuidea.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.data.RetrofitClient
import com.mtnine.amuidea.model.PostResponse
import com.mtnine.amuidea.model.User
import com.mtnine.amuidea.model.UserResponse
import com.mtnine.amuidea.model.WordResponse
import com.mtnine.amuidea.vm.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {

    fun callLogin(id: String, pw: String): MutableLiveData<UserResponse> {
        val userLiveData: MutableLiveData<UserResponse> = MutableLiveData<UserResponse>()
        val call = RetrofitClient.apiInterface.getLoginResponse(User(id, pw, null))
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val data = response.body()!!
                val statusCode = data.statusCode
                val body = data.msg
                userLiveData.value = UserResponse(statusCode, body)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return userLiveData
    }

    fun callAccount(id: String, pw: String, nick: String): MutableLiveData<UserResponse> {
        val userLiveData: MutableLiveData<UserResponse> = MutableLiveData<UserResponse>()
        val call = RetrofitClient.apiInterface.createAccount(User(id, pw, nick))
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val data = response.body()!!
                val statusCode = data.statusCode
                val body = data.msg
                userLiveData.value = UserResponse(statusCode, body)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return userLiveData
    }

    fun callGetWord(): MutableLiveData<WordResponse> {
        val wordLiveData: MutableLiveData<WordResponse> = MutableLiveData<WordResponse>()
        val call = RetrofitClient.apiInterface.getWord()
        call.enqueue(object : Callback<WordResponse> {
            override fun onResponse(call: Call<WordResponse>, response: Response<WordResponse>) {
                val data = response.body()!!
                val statusCode = data.statusCode
                val body = data.msg
                wordLiveData.value = WordResponse(statusCode, body)
            }

            override fun onFailure(call: Call<WordResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return wordLiveData
    }

    fun callGetIdeas(id: String): MutableLiveData<PostResponse> {
        val postLiveData = MutableLiveData<PostResponse>()
        val call = RetrofitClient.apiInterface.getIdeas(id)
        call.enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                val data = response.body()!!
                postLiveData.value = PostResponse(data.statusCode, data.date, data.words, data.idea)
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return postLiveData
    }
}