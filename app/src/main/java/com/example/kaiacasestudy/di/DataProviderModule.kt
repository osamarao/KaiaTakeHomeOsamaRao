package com.example.kaiacasestudy.di

import com.example.kaiacasestudy.network.ExerciseDataProvider
import com.example.kaiacasestudy.network.ExerciseNetworkDataProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataProviderModule {

    @Binds
    abstract fun exerciseDataProvider(exerciseData: ExerciseNetworkDataProvider): ExerciseDataProvider
}