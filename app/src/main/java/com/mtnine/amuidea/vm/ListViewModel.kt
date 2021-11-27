package com.mtnine.amuidea.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.model.PostResponse
import com.mtnine.amuidea.repository.Repository

class ListViewModel : BaseViewModel() {
    var itemListData = MutableLiveData<ArrayList<Item>>()
    var items = ArrayList<Item>()

    init {
        items = arrayListOf(Item("2021/11/27", "사과,배,구름", "구름 낀 날에 배에서 사과 팔기"))
        itemListData.value = items
    }

    fun callGetItems(id: String): LiveData<ArrayList<Item>> {
        itemListData = Repository.callGetIdeas(id)
        return itemListData
    }
}