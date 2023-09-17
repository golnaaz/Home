package com.golnaz.home.network.utils

import com.golnaz.home.network.model.NetworkResult
import retrofit2.Response

suspend fun <T, R> safeApiCall(
    apiCall: suspend () -> Response<T>,
    transform: (T) -> R
): NetworkResult<R> {
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                return NetworkResult.Success(transform(body))
            }
        }
        return NetworkResult.Fail("${response.code()} ${response.message()}")
    } catch (e: Exception) {
        return NetworkResult.Fail(e.message ?: e.toString())
    }
}