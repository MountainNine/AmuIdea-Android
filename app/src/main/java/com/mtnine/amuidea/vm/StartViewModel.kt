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

    fun callGetWord() : LiveData<WordResponse>? {
        liveData = Repository.callGetWord()
        return liveData
    }

    fun putCurrentState(context: Context) {
        Repository.putCurrentState(context, 0)
    }

    fun putWords(context:Context, words: ArrayList<String>) {
        Repository.putWords(context, words)
    }

}