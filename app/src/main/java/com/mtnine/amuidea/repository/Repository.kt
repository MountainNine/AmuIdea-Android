package com.mtnine.amuidea.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.data.RetrofitClient
import com.mtnine.amuidea.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import util.StringUtil.PREF

object Repository {
    fun getLoginState(context: Context): Boolean {
        val pref: SharedPreferences = context.getSharedPreferences(PREF, MODE_PRIVATE)
        return pref.getBoolean("login_state", false)
    }

    fun getCurrentState(context: Context): Int {
        val pref: SharedPreferences = context.getSharedPreferences(PREF, MODE_PRIVATE)
        return pref.getInt("current_state", 0)
    }

    fun getLoginId(context: Context): String? {
        val pref: SharedPreferences = context.getSharedPreferences(PREF, MODE_PRIVATE)
        return pref.getString("login_id", "")
    }

    fun putLoginState(context: Context, isChecked: Boolean) {
        val pref = context.getSharedPreferences(PREF, MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("login_state", false)
        editor.apply()
    }

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

    fun callGetIdeas(id: String): MutableLiveData<ArrayList<Post>> {
        val postLiveData = MutableLiveData<ArrayList<Post>>()
        val call = RetrofitClient.apiInterface.getIdeas(id)
        call.enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                val data = response.body()!!
                postLiveData.value = data.msg
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return postLiveData
    }
}