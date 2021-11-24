package com.mtnine.amuidea.model

import com.google.gson.annotations.SerializedName




class LoginRequest {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("pw")
    var pw: String? = null

    constructor(id: String?, pw: String?) {
        this.id = id
        this.pw = pw
    }
}