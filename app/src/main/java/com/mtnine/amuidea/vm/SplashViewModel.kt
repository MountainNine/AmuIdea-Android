package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.model.SimpleResponse
import com.mtnine.amuidea.repository.Repository

class SplashViewModel : BaseViewModel() {
    fun getLoginState(context : Context) : Boolean {
        return Repository.getLoginState(context)
    }

    fun getCurrentState(context:Context, date:String) : LiveData<SimpleResponse> {
        return Repository.callCurrentState(Repository.getLoginId(context), date)
    }
}