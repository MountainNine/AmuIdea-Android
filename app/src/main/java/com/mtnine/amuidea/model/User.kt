package com.mtnine.amuidea.model

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("pw")
    var pw: String? = null,

    @SerializedName("name")
    var name: String? = null
)