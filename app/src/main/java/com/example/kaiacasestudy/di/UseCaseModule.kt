package com.example.kaiacasestudy.di

import com.example.kaiacasestudy.usecase.MainActivityUseCase
import com.example.kaiacasestudy.usecase.MainActivityUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun mainActivityUseCase(mainActivityUseCaseImpl: MainActivityUseCaseImpl): MainActivityUseCase
}