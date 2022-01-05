package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.base.MutableSingleLiveData
import com.mtnine.amuidea.base.SingleLiveData
import com.mtnine.amuidea.model.WordResponse
import com.mtnine.amuidea.repository.Repository

class StartViewModel : BaseViewModel() {
    var onStartClick = MutableSingleLiveData<Unit>()
    var liveData: MutableSingleLiveData<WordResponse>? = null

    fun getWord() {
        onStartClick.setValue(Unit)
    }

    fun getLoginId(context: Context): String {
        return Repository.getLoginId(context)
    }

    fun callAddWord(id: String, date: String) : SingleLiveData<WordResponse>? {
        liveData = Repository.callAddWord(id, date)
        return liveData
    }
}