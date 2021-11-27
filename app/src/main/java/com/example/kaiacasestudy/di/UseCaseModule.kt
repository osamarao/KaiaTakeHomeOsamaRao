package com.example.kaiacasestudy.di

import com.example.kaiacasestudy.usecase.ExerciseListFragmentUseCase
import com.example.kaiacasestudy.usecase.ExerciseListFragmentUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun exerciseListFragmentUseCase(mainActivityUseCaseImpl: ExerciseListFragmentUseCaseImpl): ExerciseListFragmentUseCase
}