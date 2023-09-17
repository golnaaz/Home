package com.golnaz.home.domain

import com.golnaz.home.data.ApiHelper
import com.golnaz.home.network.model.NetworkResult
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(private val apiHelper: ApiHelper) : MainRepository {
    override suspend fun getItemsList(): Flow<NetworkResult<CustomersDomainModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailItem(userId: String): Flow<NetworkResult<CustomerDetailDomainModel>> {
        TODO("Not yet implemented")
    }


}

fun provideMainRepository(apiHelper: ApiHelper): MainRepository {
    return MainRepositoryImpl(apiHelper)
}