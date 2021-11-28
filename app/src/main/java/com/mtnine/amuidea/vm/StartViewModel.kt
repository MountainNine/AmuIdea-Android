package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.model.WordResponse
import com.mtnine.amuidea.repository.Repository

class StartViewModel : BaseViewModel() {
    var onStartClick = MutableLiveData<Unit>()
    var liveData: MutableLiveData<WordResponse>? = null

    fun getWord() {
        onStartClick.value = Unit
    }

    fun getLoginId(context: Context): String {
        return Repository.getLoginId(context)
    }

    fun callAddWord(id: String, date: String) : LiveData<WordResponse>? {
        liveData = Repository.callAddWord(id, date)
        return liveData
    }

    fun putCurrentState(context: Context) {
        Repository.putCurrentState(context, 0)
    }

    fun putWords(context:Context, words: ArrayList<String>) {
        Repository.putWords(context, words)
    }

}