package com.mtnine.amuidea.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.model.UserResponse
import com.mtnine.amuidea.repository.Repository

class LoginViewModel : BaseViewModel() {
    var onLoginClick = MutableLiveData<Unit>()
    var onAccountClick = MutableLiveData<Unit>()
    var liveData: MutableLiveData<UserResponse>? = null

    fun login() {
        onLoginClick.value = Unit
    }

    fun callLogin(id: String, pw: String) : LiveData<UserResponse>? {
        liveData = Repository.callLogin(id, pw)
        return liveData
    }


    fun goAccount() {
        onAccountClick.value = Unit
    }

}