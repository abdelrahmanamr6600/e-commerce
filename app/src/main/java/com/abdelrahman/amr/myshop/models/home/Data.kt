package com.abdelrahman.amr.myshop.models.home


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("ad")
    var ad: String? = "",
    @SerializedName("banners")
    var banners: ArrayList<Banner?>? = ArrayList(),
    @SerializedName("products")
    var products: ArrayList<Product?>? = ArrayList()
)