package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.base.MutableSingleLiveData
import com.mtnine.amuidea.base.SingleLiveData
import com.mtnine.amuidea.model.SimpleResponse
import com.mtnine.amuidea.repository.Repository

class LoginViewModel : BaseViewModel() {
    var onLoginClick = MutableSingleLiveData<Unit>()
    var onAccountClick = MutableSingleLiveData<Unit>()
    var liveData: MutableSingleLiveData<SimpleResponse>? = null

    fun login() {
        onLoginClick.setValue(Unit)
    }

    fun callLogin(id: String, pw: String): SingleLiveData<SimpleResponse>? {
        liveData = Repository.callLogin(id, pw)
        return liveData
    }

    fun getCurrentState(id: String, date: String): SingleLiveData<SimpleResponse> {
        return Repository.callCurrentState(id, date)
    }

    fun checkAutoLogin(context: Context, isChecked: Boolean) {
        Repository.putLoginState(context, isChecked)
    }

    fun putLoginId(context: Context, id: String) {
        Repository.putLoginId(context, id)
    }


    fun goAccount() {
        onAccountClick.setValue(Unit)
    }

}