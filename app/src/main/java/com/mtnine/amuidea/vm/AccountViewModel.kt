package com.mtnine.amuidea.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.model.UserResponse
import com.mtnine.amuidea.repository.Repository

class AccountViewModel : BaseViewModel() {
    var onAccountClick = MutableLiveData<Unit>()
    var liveData: MutableLiveData<UserResponse>? = null

    fun createAccount() {
        onAccountClick.value = Unit
    }

    fun callAccount(id: String, pw: String, nick: String) : LiveData<UserResponse>? {
        liveData = Repository.callAccount(id, pw, nick)
        return liveData
    }
}