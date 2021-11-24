package com.mtnine.amuidea.model

import com.google.gson.annotations.SerializedName


class LoginResponse {
    @SerializedName("statusCode")
    var statusCode: String? = null

    @SerializedName("body")
    var body: String? = null
}