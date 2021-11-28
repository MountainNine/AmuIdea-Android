package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.model.SimpleResponse
import com.mtnine.amuidea.repository.Repository

class LoginViewModel : BaseViewModel() {
    var onLoginClick = MutableLiveData<Unit>()
    var onAccountClick = MutableLiveData<Unit>()
    var liveData: MutableLiveData<SimpleResponse>? = null

    fun login() {
        onLoginClick.value = Unit
    }

    fun callLogin(id: String, pw: String): LiveData<SimpleResponse>? {
        liveData = Repository.callLogin(id, pw)
        return liveData
    }

    fun getCurrentState(context: Context): Int {
        return Repository.getCurrentState(context)
    }

    fun checkAutoLogin(context: Context, isChecked: Boolean) {
        Repository.putLoginState(context, isChecked)
    }

    fun putLoginId(context: Context, id: String) {
        Repository.putLoginId(context, id)
    }


    fun goAccount() {
        onAccountClick.value = Unit
    }

}