package com.mtnine.amuidea.model

import com.google.gson.annotations.SerializedName


data class SimpleResponse(
    @SerializedName("statusCode")
    val statusCode: String? = null,
    @SerializedName("msg")
    val msg: String? = null
)