package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.base.MutableSingleLiveData
import com.mtnine.amuidea.base.SingleLiveData
import com.mtnine.amuidea.model.Post
import com.mtnine.amuidea.model.SimpleResponse
import com.mtnine.amuidea.model.WordResponse
import com.mtnine.amuidea.repository.Repository

class MainViewModel() : BaseViewModel() {
    val onButtonClick = MutableSingleLiveData<Unit>()
    var liveData: MutableSingleLiveData<SimpleResponse>? = null
    var wordLiveData: MutableSingleLiveData<WordResponse>? = null

    fun goListActivity() {
        onButtonClick.setValue(Unit)
    }

    fun getLoginId(context: Context): String {
        return Repository.getLoginId(context)
    }

    fun callAddIdea(post: Post): SingleLiveData<SimpleResponse>? {
        liveData = Repository.callAddIdea(post)
        return liveData
    }

    fun callGetWord(id: String, date: String): SingleLiveData<WordResponse>? {
        wordLiveData = Repository.callGetWord(id, date)
        return wordLiveData
    }
}