package com.mtnine.amuidea.model

import com.google.gson.annotations.SerializedName


data class PostResponse(
    @SerializedName("statusCode")
    var statusCode: String? = null,
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("words")
    var words: String? = null,
    @SerializedName("idea")
    var idea: String? = null,
    )