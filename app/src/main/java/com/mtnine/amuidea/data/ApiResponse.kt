package com.mtnine.amuidea.data

import com.google.gson.annotations.SerializedName


class ApiResponse {
    @SerializedName("result")
    var resultCode: String? = null

    @SerializedName("body")
    var body: String? = null
}