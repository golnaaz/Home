package com.golnaz.home.data.model

data class DetailResponseModel(
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