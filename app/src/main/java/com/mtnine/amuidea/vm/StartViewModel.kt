package com.mtnine.amuidea.vm

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

}