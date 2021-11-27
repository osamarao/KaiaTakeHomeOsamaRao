package com.example.kaiacasestudy.di

import com.example.kaiacasestudy.usecase.ExerciseListUseCase
import com.example.kaiacasestudy.usecase.ExerciseListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun exerciseListUseCase(exerciseListUseCaseImpl: ExerciseListUseCaseImpl): ExerciseListUseCase
}