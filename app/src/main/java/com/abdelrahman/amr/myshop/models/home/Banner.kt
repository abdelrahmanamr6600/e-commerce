package com.abdelrahman.amr.myshop.models.home


import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("category")
    var category: Any? = Any(),
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("image")
    var image: String? = "",
    @SerializedName("product")
    var product: Any? = Any()
)