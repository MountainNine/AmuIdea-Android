package com.mtnine.amuidea.model

import com.google.gson.annotations.SerializedName
import com.mtnine.amuidea.vm.Item


data class PostResponse(
    @SerializedName("statusCode")
    val statusCode: String,
    @SerializedName("msg")
    val msg: ArrayList<Item>
    )