package com.golnaz.home.data.model

data class ItemsResponseModel(val items: ArrayList<ItemModel>)

data class ItemModel(
    val bedrooms: Int,
    val city: String,
    val id: Int,
    val area: Int,
    val url: String,
    val price: Long,
    val professional: String,
    val propertyType: String,
    val offerType: Int,
    val rooms: Int
)