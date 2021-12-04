package com.mtnine.amuidea.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.JsonObject
import com.mtnine.amuidea.base.MutableSingleLiveData
import com.mtnine.amuidea.data.RetrofitClient
import com.mtnine.amuidea.model.*
import com.mtnine.amuidea.util.StringUtil.LOGIN_ID
import com.mtnine.amuidea.util.StringUtil.LOGIN_STATE
import com.mtnine.amuidea.util.StringUtil.PREF
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {
    fun getLoginState(context: Context): Boolean {
        val pref: SharedPreferences = context.getSharedPreferences(PREF, MODE_PRIVATE)
        return pref.getBoolean(LOGIN_STATE, false)
    }

    fun callCurrentState(id: String, date: String): MutableSingleLiveData<SimpleResponse> {
        val simpleLiveData = MutableSingleLiveData<SimpleResponse>()
        val call = RetrofitClient.apiInterface.getState(Post(id, date, null, null))
        call.enqueue(object : Callback<SimpleResponse> {
            override fun onResponse(
                call: Call<SimpleResponse>,
                response: Response<SimpleResponse>
            ) {
                val data = response.body()
                if (data != null) {
                    val statusCode = data.statusCode
                    val body = data.msg
                    simpleLiveData.setValue(SimpleResponse(statusCode, body))
                }
            }

            override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return simpleLiveData
    }

    fun getLoginId(context: Context): String {
        val pref: SharedPreferences = context.getSharedPreferences(PREF, MODE_PRIVATE)
        return pref.getString(LOGIN_ID, "").toString()
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

    fun callLogin(id: String, pw: String): MutableSingleLiveData<SimpleResponse> {
        val userLiveData: MutableSingleLiveData<SimpleResponse> = MutableSingleLiveData<SimpleResponse>()
        val call = RetrofitClient.apiInterface.getLoginResponse(User(id, pw, null))
        call.enqueue(object : Callback<SimpleResponse> {
            override fun onResponse(
                call: Call<SimpleResponse>,
                response: Response<SimpleResponse>
            ) {
                val data = response.body()
                if (data != null) {
                    val statusCode = data.statusCode
                    val body = data.msg
                    userLiveData.setValue(SimpleResponse(statusCode, body))
                }
            }

            override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return userLiveData
    }

    fun callAccount(id: String, pw: String, nick: String): MutableSingleLiveData<SimpleResponse> {
        val userLiveData: MutableSingleLiveData<SimpleResponse> = MutableSingleLiveData<SimpleResponse>()
        val call = RetrofitClient.apiInterface.createAccount(User(id, pw, nick))
        call.enqueue(object : Callback<SimpleResponse> {
            override fun onResponse(
                call: Call<SimpleResponse>,
                response: Response<SimpleResponse>
            ) {
                val data = response.body()
                if (data != null) {
                    val statusCode = data.statusCode
                    val body = data.msg
                    userLiveData.setValue(SimpleResponse(statusCode, body))
                }
            }

            override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return userLiveData
    }

    fun callAddWord(id: String, date: String): MutableSingleLiveData<WordResponse> {
        val wordLiveData: MutableSingleLiveData<WordResponse> = MutableSingleLiveData<WordResponse>()
        val call = RetrofitClient.apiInterface.addWord(Post(id, date, null, null))
        call.enqueue(object : Callback<WordResponse> {
            override fun onResponse(call: Call<WordResponse>, response: Response<WordResponse>) {
                val data = response.body()
                if (data != null) {
                    val statusCode = data.statusCode
                    val body = data.msg
                    wordLiveData.setValue(WordResponse(statusCode, body))
                }
            }

            override fun onFailure(call: Call<WordResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return wordLiveData
    }

    fun callGetWord(id: String, date: String): MutableSingleLiveData<WordResponse> {
        val wordLiveData = MutableSingleLiveData<WordResponse>()
        val call = RetrofitClient.apiInterface.getWord(Post(id, date, null, null))
        call.enqueue(object : Callback<WordResponse> {
            override fun onResponse(call: Call<WordResponse>, response: Response<WordResponse>) {
                val data = response.body()
                if (data != null) {
                    val statusCode = data.statusCode
                    val body = data.msg
                    wordLiveData.setValue(WordResponse(statusCode, body))
                }
            }

            override fun onFailure(call: Call<WordResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return wordLiveData
    }

    fun callGetIdeas(id: String, date: String): MutableSingleLiveData<ArrayList<JsonObject>> {
        val postLiveData = MutableSingleLiveData<ArrayList<JsonObject>>()
        val call = RetrofitClient.apiInterface.getIdea(Post(id, date, null, null))
        call.enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                val data = response.body()
                if (data != null) {
                    postLiveData.setValue(data.msg)
                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return postLiveData
    }

    fun callAddIdea(post: Post): MutableSingleLiveData<SimpleResponse> {
        val liveData = MutableSingleLiveData<SimpleResponse>()
        val call = RetrofitClient.apiInterface.addIdea(post)
        call.enqueue(object : Callback<SimpleResponse> {
            override fun onResponse(
                call: Call<SimpleResponse>,
                response: Response<SimpleResponse>
            ) {
                val data = response.body()
                if(data != null) {
                    liveData.setValue(SimpleResponse(data.statusCode, data.msg))
                }
            }

            override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                Log.d("D", call.toString())
            }
        })

        return liveData
    }
}