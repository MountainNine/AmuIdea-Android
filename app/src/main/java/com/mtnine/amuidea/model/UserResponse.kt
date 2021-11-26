package com.mtnine.amuidea.model

import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("statusCode")
    var statusCode: String? = null,
    @SerializedName("msg")
    var msg: String? = null
)