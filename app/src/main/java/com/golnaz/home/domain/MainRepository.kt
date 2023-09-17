package com.golnaz.home.domain

import com.golnaz.home.network.model.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getItemsList(): Flow<NetworkResult<CustomersDomainModel>>
    suspend fun getDetailItem(userId: String): Flow<NetworkResult<CustomerDetailDomainModel>>
}