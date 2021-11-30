package com.mtnine.amuidea.model

import com.google.gson.annotations.SerializedName


data class WordResponse(
    @SerializedName("statusCode")
    val statusCode: String? = null,
    @SerializedName("msg")
    val msg: List<String?>
)