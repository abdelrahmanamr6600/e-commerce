package com.abdelrahman.amr.myshop.models
import com.google.gson.annotations.SerializedName
open class BaseResponse<T> {
    @SerializedName("status")
    var status: Boolean? = null
    @SerializedName("data")
    var data:T? = null
    @SerializedName("message")
    var message: String? = ""

}