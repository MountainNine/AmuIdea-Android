package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.base.SingleLiveData
import com.mtnine.amuidea.model.SimpleResponse
import com.mtnine.amuidea.repository.Repository
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    fun getLoginState(context : Context) : Boolean {
        return repository.getLoginState(context)
    }

    fun getCurrentState(context:Context, date:String) : SingleLiveData<SimpleResponse> {
        return repository.callCurrentState(repository.getLoginId(context), date)
    }
}