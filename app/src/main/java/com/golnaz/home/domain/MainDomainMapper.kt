package com.golnaz.home.domain

import com.golnaz.home.data.model.DetailResponseModel
import com.golnaz.home.data.model.ItemModel
import com.golnaz.home.data.model.ItemsResponseModel
import com.golnaz.home.domain.model.ItemDetailDomainModel
import com.golnaz.home.domain.model.ItemsDomainModel

fun ItemsResponseModel.toDomain(): ItemsDomainModel {
    return ItemsDomainModel(
        items = items.map {
            it.toDomain()
        } as ArrayList<ItemDetailDomainModel>
    )
}

fun ItemModel.toDomain(): ItemDetailDomainModel {
    return ItemDetailDomainModel(
        bedrooms = bedrooms,
        city = city,
        id = id,
        area = area,
        url = url,
        price = price,
        professional = professional,
        propertyType = propertyType,
        offerType = offerType,
        rooms = rooms,
    )
}

fun DetailResponseModel.toDomain(): ItemDetailDomainModel {
    return ItemDetailDomainModel(
        bedrooms = bedrooms,
        city = city,
        id = id,
        area = area,
        url = url,
        price = price,
        professional = professional,
        propertyType = propertyType,
        offerType = offerType,
        rooms = rooms,
    )
}