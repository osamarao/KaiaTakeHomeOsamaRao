package com.example.kaiacasestudy.di

import com.example.kaiacasestudy.repositories.ExerciseRepository
import com.example.kaiacasestudy.repositories.ExerciseRepositoryImpl
import com.example.kaiacasestudy.repositories.FavoritesRepository
import com.example.kaiacasestudy.repositories.FavoritesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun exerciseRepository(exerciseRepositoryImpl: ExerciseRepositoryImpl) : ExerciseRepository

    @Binds
    @Singleton
    abstract fun favoritesRepository(favoritesRepository: FavoritesRepositoryImpl) : FavoritesRepository
}