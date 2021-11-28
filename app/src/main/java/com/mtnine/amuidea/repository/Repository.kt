package com.mtnine.amuidea.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.mtnine.amuidea.data.RetrofitClient
import com.mtnine.amuidea.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import util.StringUtil.LOGIN_ID
import util.StringUtil.LOGIN_STATE
import util.StringUtil.PREF

object Repository {
    fun getLoginState(context: Context): Boolean {
        val pref: SharedPreferences = context.getSharedPreferences(PREF, MODE_PRIVATE)
        return pref.getBoolean(LOGIN_STATE, false)
    }

    fun callCurrentState(id: String, date:String): MutableLiveData<SimpleResponse> {
        val simpleLiveData = MutableLiveData<SimpleResponse>()
        val call = RetrofitClient.apiInterface.getState(Post(id,date,null,null))
        call.enqueue(object: Callback<SimpleResponse> {
            override fun onResponse(call: Call<SimpleResponse>, response: Response<SimpleResponse>) {
                val data = response.body()!!
                val statusCode = data.statusCode
                val body = data.msg
                simpleLiveData.value = SimpleResponse(statusCode, body)
            }

            override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return simpleLiveData
    }

    fun getLoginId(context: Context): String {
        val pref: SharedPreferences = context.getSharedPreferences(PREF, MODE_PRIVATE)
        return pref.getString(LOGIN_ID, "")!!
    }

    fun putLoginState(context: Context, isChecked: Boolean) {
        val pref = context.getSharedPreferences(PREF, MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean(LOGIN_STATE, isChecked)
        editor.apply()
    }

    fun putLoginId(context: Context, loginId: String) {
        val pref = context.getSharedPreferences(PREF, MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(LOGIN_ID, loginId)
        editor.apply()
    }

    fun callLogin(id: String, pw: String): MutableLiveData<SimpleResponse> {
        val userLiveData: MutableLiveData<SimpleResponse> = MutableLiveData<SimpleResponse>()
        val call = RetrofitClient.apiInterface.getLoginResponse(User(id, pw, null))
        call.enqueue(object : Callback<SimpleResponse> {
            override fun onResponse(call: Call<SimpleResponse>, response: Response<SimpleResponse>) {
                val data = response.body()!!
                val statusCode = data.statusCode
                val body = data.msg
                userLiveData.value = SimpleResponse(statusCode, body)
            }

            override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return userLiveData
    }

    fun callAccount(id: String, pw: String, nick: String): MutableLiveData<SimpleResponse> {
        val userLiveData: MutableLiveData<SimpleResponse> = MutableLiveData<SimpleResponse>()
        val call = RetrofitClient.apiInterface.createAccount(User(id, pw, nick))
        call.enqueue(object : Callback<SimpleResponse> {
            override fun onResponse(call: Call<SimpleResponse>, response: Response<SimpleResponse>) {
                val data = response.body()!!
                val statusCode = data.statusCode
                val body = data.msg
                userLiveData.value = SimpleResponse(statusCode, body)
            }

            override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return userLiveData
    }

    fun callAddWord(id: String, date: String): MutableLiveData<WordResponse> {
        val wordLiveData: MutableLiveData<WordResponse> = MutableLiveData<WordResponse>()
        val call = RetrofitClient.apiInterface.addWord(Post(id,date,null,null))
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

    fun callGetWord(id: String, date: String): MutableLiveData<WordResponse> {
        val wordLiveData = MutableLiveData<WordResponse>()
        val call = RetrofitClient.apiInterface.getWord(Post(id,date,null,null))
        call.enqueue(object: Callback<WordResponse> {
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

    fun callGetIdeas(id: String, date:String): MutableLiveData<ArrayList<JsonObject>> {
        val postLiveData = MutableLiveData<ArrayList<JsonObject>>()
        val call = RetrofitClient.apiInterface.getIdea(Post(id, date, null, null))
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

    fun callAddIdea(post: Post): MutableLiveData<SimpleResponse> {
        val liveData = MutableLiveData<SimpleResponse>()
        val call = RetrofitClient.apiInterface.addIdea(post)
        call.enqueue(object: Callback<SimpleResponse> {
            override fun onResponse(
                call: Call<SimpleResponse>,
                response: Response<SimpleResponse>
            ) {
                val data = response.body()!!
                liveData.value = SimpleResponse(data.statusCode,data.msg)
            }

            override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return liveData
    }
}