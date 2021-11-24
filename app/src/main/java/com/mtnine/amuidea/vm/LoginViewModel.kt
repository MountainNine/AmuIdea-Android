package com.mtnine.amuidea.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.data.RetrofitClient
import com.mtnine.amuidea.model.LoginResponse
import com.mtnine.amuidea.model.User
import com.mtnine.amuidea.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : BaseViewModel() {
    var onLoginClick = MutableLiveData<Unit>()
    var onAccountClick = MutableLiveData<Unit>()
    var liveData: MutableLiveData<LoginResponse>? = null

    fun login() {
        onLoginClick.value = Unit
    }

    fun callLogin(id: String, pw: String) : LiveData<LoginResponse>? {
        liveData = Repository.callLogin(id, pw)
        return liveData
    }


    fun goAccount() {
        onAccountClick.value = Unit
    }

}