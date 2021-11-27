package com.mtnine.amuidea.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.model.PostResponse
import com.mtnine.amuidea.repository.Repository

class ListViewModel : BaseViewModel() {
    var itemListData = MutableLiveData<PostResponse>()

    fun callGetItems(id: String): LiveData<PostResponse> {
        itemListData = Repository.callGetIdeas(id)
        return itemListData
    }
}