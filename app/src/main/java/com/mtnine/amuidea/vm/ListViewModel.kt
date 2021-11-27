package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.model.Post
import com.mtnine.amuidea.repository.Repository

class ListViewModel : BaseViewModel() {
    var itemListData = MutableLiveData<ArrayList<Post>>()
    var items = ArrayList<Post>()

    init {
        items = arrayListOf(Post("2021/11/27", "사과,배,구름", "구름 낀 날에 배에서 사과 팔기"))
        itemListData.value = items
    }

    fun callGetItems(id: String): LiveData<ArrayList<Post>> {
        itemListData = Repository.callGetIdeas(id)
        return itemListData
    }

    fun putCurrentState(context: Context) {
        Repository.putCurrentState(context, 2)
    }
}