package com.abdelrahman.amr.myshop.models.home


import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("data")
    var `data`: Data? = Data(),
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("status")
    var status: Boolean? = false
)