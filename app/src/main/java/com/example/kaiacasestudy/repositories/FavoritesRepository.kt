package com.example.kaiacasestudy.repositories

import com.example.kaiacasestudy.data.FavoritesDataProvider
import com.example.kaiacasestudy.data.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FavoritesRepository {
    fun favorites(): Flow<NetworkResult<List<Int>>>
    suspend fun updateFavorites(idList: List<Int>)
}

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDataProvider: FavoritesDataProvider,
) : FavoritesRepository {
    override fun favorites(): Flow<NetworkResult<List<Int>>> = favoritesDataProvider.favorites()

    override suspend fun updateFavorites(idList: List<Int>) {
        favoritesDataProvider.updateFavorites(idList)
    }
}