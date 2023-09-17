package com.golnaz.home.network.model

sealed class NetworkResult<out T> {
    data class Success<out T>(val value: T): NetworkResult<T>()
    data class Fail(val error: String): NetworkResult<Nothing>()
}