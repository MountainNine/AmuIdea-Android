package com.mtnine.amuidea.vm

import android.content.Context
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.repository.Repository

class SplashViewModel : BaseViewModel() {
    fun getLoginState(context : Context) : Boolean {
        return Repository.getLoginState(context)
    }

    fun getCurrentState(context : Context) : Int {
        return Repository.getCurrentState(context)
    }
}