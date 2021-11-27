package com.mtnine.amuidea.vm

import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel

class MainViewModel() : BaseViewModel() {
    val onButtonClick = MutableLiveData<Unit>()

    fun goListActivity() {
        onButtonClick.value = Unit
    }
}