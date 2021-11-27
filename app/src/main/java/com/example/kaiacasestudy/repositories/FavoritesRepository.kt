package com.example.kaiacasestudy.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface FavoritesRepository {
    fun favorites(): Flow<List<Int>>
}

class CannedFavoritesRepository @Inject constructor() : FavoritesRepository {
    override fun favorites(): Flow<List<Int>> = flowOf(listOf(1, 2))
}