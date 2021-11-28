package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.model.Post
import com.mtnine.amuidea.model.PostResponse
import com.mtnine.amuidea.model.SimpleResponse
import com.mtnine.amuidea.model.WordResponse
import com.mtnine.amuidea.repository.Repository

class MainViewModel() : BaseViewModel() {
    val onButtonClick = MutableLiveData<Unit>()
    var liveData: MutableLiveData<SimpleResponse>? = null
    var wordLiveData: MutableLiveData<WordResponse>? = null

    fun goListActivity() {
        onButtonClick.value = Unit
    }

    fun getLoginId(context: Context): String {
        return Repository.getLoginId(context)
    }

    fun callAddIdea(post: Post) : LiveData<SimpleResponse>? {
        liveData = Repository.callAddIdea(post)
        return liveData
    }

    fun callGetWord(id: String, date: String) : LiveData<WordResponse>? {
        wordLiveData = Repository.callGetWord(id, date)
        return wordLiveData
    }
}