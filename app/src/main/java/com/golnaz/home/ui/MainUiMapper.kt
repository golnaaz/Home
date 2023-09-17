package com.golnaz.home.ui

import com.golnaz.home.domain.model.ItemDetailDomainModel
import com.golnaz.home.domain.model.ItemsDomainModel
import com.golnaz.home.ui.model.ItemUiModel

fun ArrayList<ItemDetailDomainModel>.toContent(): ArrayList<ItemUiModel> {
    return map {
        it.toContent()
    } as ArrayList<ItemUiModel>
}

fun ItemDetailDomainModel.toContent(): ItemUiModel = ItemUiModel(
    bedrooms = bedrooms.toString(),
    city = city,
    id = id,
    area = area.toString(),
    url = url ?: "",
    priceValue = price.toString().plus("€"),
    professional = professional,
    propertyType = propertyType,
    offerType = offerType,
    rooms = rooms.toString(),
)