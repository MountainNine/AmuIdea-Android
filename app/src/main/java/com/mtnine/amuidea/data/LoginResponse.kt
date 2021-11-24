package com.mtnine.amuidea.data

import com.google.gson.annotations.SerializedName


class LoginResponse {
    @SerializedName("statusCode")
    var statusCode: String? = null

    @SerializedName("body")
    var body: String? = null

    @SerializedName("token")
    var token: String? = null
}