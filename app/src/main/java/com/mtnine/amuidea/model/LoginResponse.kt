package com.mtnine.amuidea.model

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("statusCode")
    var statusCode: String? = null,
    @SerializedName("msg")
    var msg: String? = null
)