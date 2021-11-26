package com.mtnine.amuidea.model

import com.google.gson.annotations.SerializedName


data class WordResponse(
    @SerializedName("statusCode")
    var statusCode: String? = null,
    @SerializedName("msg")
    var msg: List<String>? = null
)