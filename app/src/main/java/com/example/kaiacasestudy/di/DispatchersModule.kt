package com.example.kaiacasestudy.di

import com.example.kaiacasestudy.data.KaiaDispatchers
import com.example.kaiacasestudy.data.RuntimeDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatchersModule {

    @Binds
    abstract fun dispatchers(runtimeDispatchers: RuntimeDispatchers): KaiaDispatchers
}