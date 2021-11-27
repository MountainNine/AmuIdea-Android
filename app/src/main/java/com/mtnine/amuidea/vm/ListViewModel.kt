package com.mtnine.amuidea.vm

import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel

class ListViewModel : BaseViewModel() {
    var listItemData = MutableLiveData<ArrayList<Item>>()
}