package com.abdelrahman.amr.myshop.models.home


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("discount")
    var discount: Int? = 0,
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("image")
    var image: String? = "",
    @SerializedName("images")
    var images: List<String?>? = listOf(),
    @SerializedName("in_cart")
    var inCart: Boolean? = false,
    @SerializedName("in_favorites")
    var inFavorites: Boolean? = false,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("old_price")
    var oldPrice: Double? = 0.0,
    @SerializedName("price")
    var price: Double? = 0.0
)