package com.mtnine.amuidea.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName


data class PostResponse(
    @SerializedName("statusCode")
    val statusCode: String,
    @SerializedName("msg")
    val msg: ArrayList<JsonObject>
    )