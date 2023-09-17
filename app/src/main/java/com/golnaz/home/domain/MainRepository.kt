package com.golnaz.home.domain

import com.golnaz.home.domain.model.ItemDetailDomainModel
import com.golnaz.home.domain.model.ItemsDomainModel
import com.golnaz.home.network.model.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getItemsList(): Flow<NetworkResult<ItemsDomainModel>>
    suspend fun getDetailItem(userId: String): Flow<NetworkResult<ItemDetailDomainModel>>
}