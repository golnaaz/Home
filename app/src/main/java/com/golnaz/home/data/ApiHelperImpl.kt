package com.golnaz.home.data

import com.golnaz.home.data.model.DetailResponseModel
import com.golnaz.home.data.model.ItemsResponseModel
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getItems(): Response<ItemsResponseModel> = apiService.getItems()
    override suspend fun getDetailById(id: String): Response<DetailResponseModel> =
        apiService.getDetailById(id)
}