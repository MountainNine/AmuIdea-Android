package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.base.MutableSingleLiveData
import com.mtnine.amuidea.base.SingleLiveData
import com.mtnine.amuidea.model.SimpleResponse
import com.mtnine.amuidea.repository.Repository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    var onLoginClick = MutableSingleLiveData<Unit>()
    var onAccountClick = MutableSingleLiveData<Unit>()
    var liveData: MutableSingleLiveData<SimpleResponse>? = null

    fun callLogin(id: String, pw: String): SingleLiveData<SimpleResponse>? {
        liveData = repository.callLogin(id, pw)
        return liveData
    }

    fun getCurrentState(id: String, date: String): SingleLiveData<SimpleResponse> {
        return repository.callCurrentState(id, date)
    }

    fun checkAutoLogin(context: Context, isChecked: Boolean) {
        repository.putLoginState(context, isChecked)
    }

    fun putLoginId(context: Context, id: String) {
        repository.putLoginId(context, id)
    }
}