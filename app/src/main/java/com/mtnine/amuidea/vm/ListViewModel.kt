package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.repository.Repository

class ListViewModel : BaseViewModel() {
    var itemListData = MutableLiveData<ArrayList<JsonObject>>()
    var items = ArrayList<JsonObject>()

    init {
        itemListData.value = items
    }

    fun getLoginId(context: Context): String {
        return Repository.getLoginId(context)
    }

    fun callGetIdeas(id: String, date: String): LiveData<ArrayList<JsonObject>> {
        itemListData = Repository.callGetIdeas(id, date)
        return itemListData
    }
}