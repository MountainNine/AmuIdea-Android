package com.mtnine.amuidea.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.model.SimpleResponse
import com.mtnine.amuidea.repository.Repository

class AccountViewModel : BaseViewModel() {
    var onAccountClick = MutableLiveData<Unit>()
    var liveData: MutableLiveData<SimpleResponse>? = null
    val inputId = MutableLiveData<String>()
    val inputPw = MutableLiveData<String>()
    val inputName = MutableLiveData<String>()

    init {
        inputId.value = ""
        inputPw.value = ""
        inputName.value = ""
    }

    fun createAccount() {
        onAccountClick.value = Unit
    }

    fun callAccount(id: String, pw: String, nick: String) : LiveData<SimpleResponse>? {
        liveData = Repository.callAccount(id, pw, nick)
        return liveData
    }
}