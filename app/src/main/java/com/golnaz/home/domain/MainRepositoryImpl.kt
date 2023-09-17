package com.golnaz.home.domain

import com.golnaz.home.data.ApiHelper
import com.golnaz.home.domain.model.ItemDetailDomainModel
import com.golnaz.home.domain.model.ItemsDomainModel
import com.golnaz.home.network.model.NetworkResult
import com.golnaz.home.network.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepositoryImpl(private val apiHelper: ApiHelper) : MainRepository {

    override suspend fun getItemsList(): Flow<NetworkResult<ItemsDomainModel>> {
        return flow {
            val result = safeApiCall(
                apiCall = { apiHelper.getItems() },
                transform = { data ->
                    data.toDomain()
                }
            )
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDetailItem(userId: String)
            : Flow<NetworkResult<ItemDetailDomainModel>> {
        return flow {
            val result = safeApiCall(
                apiCall = { apiHelper.getDetailById(userId) },
                transform = { data ->
                    data.toDomain()
                })
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}

fun provideMainRepository(apiHelper: ApiHelper): MainRepository {
    return MainRepositoryImpl(apiHelper)
}