package com.mtnine.amuidea.vm

import android.content.Context
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.base.MutableSingleLiveData
import com.mtnine.amuidea.base.SingleLiveData
import com.mtnine.amuidea.model.Post
import com.mtnine.amuidea.model.SimpleResponse
import com.mtnine.amuidea.model.WordResponse
import com.mtnine.amuidea.repository.Repository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    var liveData: MutableSingleLiveData<SimpleResponse>? = null
    var wordLiveData: MutableSingleLiveData<WordResponse>? = null

    fun getLoginId(context: Context): String {
        return repository.getLoginId(context)
    }

    fun callAddIdea(post: Post): SingleLiveData<SimpleResponse>? {
        liveData = repository.callAddIdea(post)
        return liveData
    }

    fun callGetWord(id: String, date: String): SingleLiveData<WordResponse>? {
        wordLiveData = repository.callGetWord(id, date)
        return wordLiveData
    }
}