package com.mtnine.amuidea.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.base.MutableSingleLiveData
import com.mtnine.amuidea.base.SingleLiveData
import com.mtnine.amuidea.model.SimpleResponse
import com.mtnine.amuidea.repository.Repository
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    var onAccountClick = MutableSingleLiveData<Unit>()
    var liveData: MutableSingleLiveData<SimpleResponse>? = null

    fun createAccount() {
        onAccountClick.setValue(Unit)
    }

    fun callAccount(id: String, pw: String, nick: String) : SingleLiveData<SimpleResponse>? {
        liveData = repository.callAccount(id, pw, nick)
        return liveData
    }
}