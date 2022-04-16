package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.base.MutableSingleLiveData
import com.mtnine.amuidea.base.SingleLiveData
import com.mtnine.amuidea.repository.Repository
import javax.inject.Inject

class ListViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    var itemListData = MutableSingleLiveData<ArrayList<JsonObject>>()
    var items = ArrayList<JsonObject>()

    init {
        itemListData.setValue(items)
    }

    fun getLoginId(context: Context): String {
        return repository.getLoginId(context)
    }

    fun callGetIdeas(id: String, date: String): SingleLiveData<ArrayList<JsonObject>> {
        itemListData = repository.callGetIdeas(id, date)
        return itemListData
    }
}