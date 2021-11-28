package com.mtnine.amuidea.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtnine.amuidea.base.BaseViewModel
import com.mtnine.amuidea.model.Post
import com.mtnine.amuidea.model.PostResponse
import com.mtnine.amuidea.model.SimpleResponse
import com.mtnine.amuidea.model.WordResponse
import com.mtnine.amuidea.repository.Repository

class MainViewModel() : BaseViewModel() {
    val onButtonClick = MutableLiveData<Unit>()
    var liveData: MutableLiveData<SimpleResponse>? = null
    var wordLiveData: MutableLiveData<WordResponse>? = null

    fun goListActivity() {
        onButtonClick.value = Unit
    }

    fun putCurrentState(context:Context) {
        Repository.putCurrentState(context, 1)
    }

    fun getExistWord(context:Context, index:Int) : String? {
        return Repository.getExistWord(context, index)
    }

    fun getAllWord(context: Context): List<String?> {
        val words = mutableListOf<String?>()
        for (i in 0..2) {
            words.add(getExistWord(context, i))
        }

        return words
    }

    fun getLoginId(context: Context): String {
        return Repository.getLoginId(context)
    }

    fun callAddIdea(post: Post) : LiveData<SimpleResponse>? {
        liveData = Repository.callAddIdea(post)
        return liveData
    }

    fun callGetWord(id: String, date: String) : LiveData<WordResponse>? {
        wordLiveData = Repository.callGetWord(id, date)
        return wordLiveData
    }
}