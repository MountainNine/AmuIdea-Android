package com.mtnine.amuidea.vm

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.model.UserResponse
import com.mtnine.amuidea.repository.Repository
import util.StringUtil.PREF

class LoginViewModel : BaseViewModel() {
    var onLoginClick = MutableLiveData<Unit>()
    var onAccountClick = MutableLiveData<Unit>()
    var liveData: MutableLiveData<UserResponse>? = null

    fun login() {
        onLoginClick.value = Unit
    }

    fun callLogin(id: String, pw: String): LiveData<UserResponse>? {
        liveData = Repository.callLogin(id, pw)
        return liveData
    }

    fun getCurrentState(context: Context): Int {
        return Repository.getCurrentState(context)
    }

    fun checkAutoLogin(context: Context, isChecked: Boolean) {
        Repository.putLoginState(context, isChecked)
    }


    fun goAccount() {
        onAccountClick.value = Unit
    }

}