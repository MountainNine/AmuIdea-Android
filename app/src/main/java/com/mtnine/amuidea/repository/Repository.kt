package com.mtnine.amuidea.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.JsonObject
import com.mtnine.amuidea.base.MutableSingleLiveData
import com.mtnine.amuidea.data.ApiInterface
import com.mtnine.amuidea.model.Post
import com.mtnine.amuidea.model.SimpleResponse
import com.mtnine.amuidea.model.User
import com.mtnine.amuidea.model.WordResponse
import com.mtnine.amuidea.util.StringUtil.LOGIN_ID
import com.mtnine.amuidea.util.StringUtil.LOGIN_STATE
import com.mtnine.amuidea.util.StringUtil.PREF
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getLoginState(context: Context): Boolean {
        val pref: SharedPreferences = context.getSharedPreferences(PREF, MODE_PRIVATE)
        return pref.getBoolean(LOGIN_STATE, false)
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

    fun callCurrentState(id: String, date: String): MutableSingleLiveData<SimpleResponse> {
        val simpleLiveData = MutableSingleLiveData<SimpleResponse>()

        CoroutineScope(Dispatchers.IO).launch {

            apiInterface.getState(Post(id, date, null, null)).let { response ->
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        simpleLiveData.postValue(SimpleResponse(it.statusCode, it.msg))
                    }
                }
            }
        }
        return simpleLiveData
    }

    fun callLogin(id: String, pw: String): MutableSingleLiveData<SimpleResponse> {
        val userLiveData = MutableSingleLiveData<SimpleResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            apiInterface.getLoginResponse(User(id, pw, null)).let { response ->
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        userLiveData.postValue(SimpleResponse(it.statusCode, it.msg))
                    }
                }
            }
        }

        return userLiveData
    }

    fun callAccount(id: String, pw: String, nick: String): MutableSingleLiveData<SimpleResponse> {
        val userLiveData = MutableSingleLiveData<SimpleResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            apiInterface.createAccount(User(id, pw, nick)).let { response ->
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        userLiveData.postValue(SimpleResponse(it.statusCode, it.msg))
                    }
                }
            }
        }

        return userLiveData
    }

    fun callAddWord(id: String, date: String): MutableSingleLiveData<WordResponse> {
        val wordLiveData = MutableSingleLiveData<WordResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            apiInterface.addWord(Post(id, date, null, null)).let { response ->
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        wordLiveData.postValue(WordResponse(it.statusCode, it.msg))
                    }
                }
            }
        }

        return wordLiveData
    }

    fun callGetWord(id: String, date: String): MutableSingleLiveData<WordResponse> {
        val wordLiveData = MutableSingleLiveData<WordResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            apiInterface.getWord(Post(id, date, null, null)).let { response ->
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        wordLiveData.postValue(WordResponse(it.statusCode, it.msg))
                    }
                }
            }
        }

        return wordLiveData
    }

    fun callGetIdeas(id: String, date: String): MutableSingleLiveData<ArrayList<JsonObject>> {
        val postLiveData = MutableSingleLiveData<ArrayList<JsonObject>>()
        CoroutineScope(Dispatchers.IO).launch {
            apiInterface.getIdea(Post(id, date, null, null)).let { response ->
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        postLiveData.postValue(it.msg)
                    }
                }
            }
        }

        return postLiveData
    }

    fun callAddIdea(post: Post): MutableSingleLiveData<SimpleResponse> {
        val liveData = MutableSingleLiveData<SimpleResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            apiInterface.addIdea(post).let { response ->
                if (response.isSuccessful) {
                    val data = response.body()
                    data?.let {
                        liveData.postValue(SimpleResponse(it.statusCode, it.msg))
                    }
                }
            }
        }

        return liveData
    }
}