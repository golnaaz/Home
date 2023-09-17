package com.golnaz.home.domain.model

data class ItemDetailDomainModel(
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