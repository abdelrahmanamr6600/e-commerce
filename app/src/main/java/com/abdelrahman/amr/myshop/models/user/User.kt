package com.abdelrahman.amr.myshop.models.user


import com.abdelrahman.amr.myshop.models.BaseResponse
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("image")
    var image: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("password")
    var password: String? = "",
    @SerializedName("phone")
    var phone: String? = ""


)

data class UserResponse(
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("image")
    var image: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("password")
    var password: String? = "",
    @SerializedName("phone")
    var phone: String? = "",
    @SerializedName("token")
    var token:String? = null

):BaseResponse<UserResponse>()

