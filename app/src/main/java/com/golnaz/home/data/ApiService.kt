package com.golnaz.home.data

import com.golnaz.home.data.model.DetailResponseModel
import com.golnaz.home.data.model.ItemsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/listings.json")
    suspend fun getItems(): Response<ItemsResponseModel>
    @GET("/listings/{listingId}.json")
    suspend fun getDetailById(@Path("listingId") id:String): Response<DetailResponseModel>
}