package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.base.MutableSingleLiveData
import com.mtnine.amuidea.base.SingleLiveData
import com.mtnine.amuidea.model.WordResponse
import com.mtnine.amuidea.repository.Repository
import javax.inject.Inject

class StartViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    var liveData: MutableSingleLiveData<WordResponse>? = null

    fun getLoginId(context: Context): String {
        return repository.getLoginId(context)
    }

    fun callAddWord(id: String, date: String) : SingleLiveData<WordResponse>? {
        liveData = repository.callAddWord(id, date)
        return liveData
    }
}