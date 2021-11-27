package com.example.kaiacasestudy.network

sealed class NetworkResult<out T> {
    object Loading : NetworkResult<Nothing>()
    data class Success<Data>(val data: Data) : NetworkResult<Data>()
    data class Error(val error: Throwable) : NetworkResult<Nothing>()
}

fun <T, R> NetworkResult<T>.mapIfSuccess(mapper: (T) -> R): NetworkResult<R> = when (this) {
    is NetworkResult.Error -> NetworkResult.Error(this.error)
    NetworkResult.Loading -> NetworkResult.Loading
    is NetworkResult.Success -> NetworkResult.Success(mapper(this.data))
}