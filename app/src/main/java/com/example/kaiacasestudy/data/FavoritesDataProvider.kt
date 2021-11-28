package com.example.kaiacasestudy.data

import kotlinx.coroutines.flow.Flow

interface FavoritesDataProvider {
    fun favorites(): Flow<NetworkResult<List<Int>>>

    suspend fun updateFavorites(intList: List<Int>)
}

