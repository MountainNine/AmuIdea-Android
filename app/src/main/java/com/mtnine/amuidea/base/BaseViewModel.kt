package com.mtnine.amuidea.base

import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

open class BaseViewModel : ViewModel(), KoinComponent {
    override fun onCleared() {
        super.onCleared()
    }
}