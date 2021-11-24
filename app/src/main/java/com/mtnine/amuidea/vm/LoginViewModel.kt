package com.mtnine.amuidea.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.model.User
import com.mtnine.amuidea.repository.Repository

class LoginViewModel : BaseViewModel() {
    var onLoginClick = MutableLiveData<Unit>()
    var onAccountClick = MutableLiveData<Unit>()

    fun login() {
        onLoginClick.value = Unit
    }

    fun goAccount() {
        onAccountClick.value = Unit
    }

}