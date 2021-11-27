package com.example.kaiacasestudy.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.kaiacasestudy.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.builtins.IntArraySerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

interface FavoritesDataProvider {
    fun favorites(): Flow<NetworkResult<List<Int>>>

    suspend fun updateFavorites(intList: List<Int>)
}

class FavoritesDataProviderPreferences @Inject constructor(
    @ApplicationContext val context: Context,
) : FavoritesDataProvider {
    companion object {
        private val KEY_PREFERENCES_FAVORITES_IDS = stringPreferencesKey("PREFERENCES_FAVORITES_IDS")
    }

    override fun favorites(): Flow<NetworkResult<List<Int>>> = context.dataStore.data.map { prefs ->
        prefs[KEY_PREFERENCES_FAVORITES_IDS]
    }.map { savedList ->
        savedList?.let { list ->
            NetworkResult.Success(Json.decodeFromString(IntArraySerializer(), list).toList())
        } ?: NetworkResult.Success(emptyList())
    }

    override suspend fun updateFavorites(intList: List<Int>) {
        context.dataStore.edit { prefs ->
            prefs[KEY_PREFERENCES_FAVORITES_IDS] = Json.encodeToString(IntArraySerializer(), intList.toIntArray())
        }
    }
}