package com.mtnine.amuidea.data

import com.google.gson.annotations.SerializedName




class ApiRequest {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("pw")
    var pw: String? = null

    fun getInputId(): String? {
        return id
    }

    fun getInputPw(): String? {
        return pw
    }

    fun setInputId(inputId: String?) {
        this.id = inputId
    }

    fun setInputPw(inputPw: String?) {
        this.pw = inputPw
    }

    fun LoginRequest(id: String?, pw: String?) {
        this.id = id
        this.pw = pw
    }
}