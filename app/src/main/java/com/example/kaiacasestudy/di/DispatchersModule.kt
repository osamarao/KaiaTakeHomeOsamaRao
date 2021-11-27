package com.example.kaiacasestudy.di

import com.example.kaiacasestudy.network.KaiaDispatchers
import com.example.kaiacasestudy.network.RuntimeDispatchers
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