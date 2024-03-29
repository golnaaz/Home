package com.golnaz.home

import com.golnaz.home.domain.model.ItemDetailDomainModel
import com.golnaz.home.domain.model.ItemsDomainModel

fun fakeItems() = ItemsDomainModel(
    items = arrayListOf(
        ItemDetailDomainModel(
            bedrooms = 4,
            city = "Villers-sur-Mer",
            id = 1,
            area = 250,
            url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
            price = 140000,
            professional = "GSL EXPLORE",
            propertyType = "Maison - Villa",
            offerType = 1,
            rooms = 8
        )
    )
)

fun fakeItemDetail() = ItemDetailDomainModel(
    bedrooms = 4,
    city = "Villers-sur-Mer",
    id = 1,
    area = 250,
    url = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
    price = 140000,
    professional = "GSL EXPLORE",
    propertyType = "Maison - Villa",
    offerType = 1,
    rooms = 8
)