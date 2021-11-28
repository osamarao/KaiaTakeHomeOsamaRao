package com.example.kaiacasestudy.di

import com.example.kaiacasestudy.data.ExerciseDataProvider
import com.example.kaiacasestudy.data.ExerciseNetworkDataProvider
import com.example.kaiacasestudy.data.FavoritesDataProvider
import com.example.kaiacasestudy.data.FavoritesDataProviderPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataProviderModule {

    @Binds
    abstract fun exerciseDataProvider(exerciseData: ExerciseNetworkDataProvider): ExerciseDataProvider

    @Binds
    abstract fun favoritesDataProvider(favoritesDataProvider: FavoritesDataProviderPreferences): FavoritesDataProvider
}