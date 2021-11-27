package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.repository.Repository

class MainViewModel() : BaseViewModel() {
    val onButtonClick = MutableLiveData<Unit>()

    fun goListActivity() {
        onButtonClick.value = Unit
    }

    fun putCurrentState(context:Context) {
        Repository.putCurrentState(context, 1)
    }

    fun getExistWord(context:Context, index:Int) : String? {
        return Repository.getExistWord(context, index)
    }
}