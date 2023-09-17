package com.golnaz.home.data

import com.golnaz.home.data.model.DetailResponseModel
import com.golnaz.home.data.model.ItemsResponseModel
import retrofit2.Response

interface ApiHelper {
    suspend fun getItems(): Response<ItemsResponseModel>
    suspend fun getDetailById(id:String): Response<DetailResponseModel>
}