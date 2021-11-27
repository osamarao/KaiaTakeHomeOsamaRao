package com.example.kaiacasestudy.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface KaiaDispatchers {

    fun ioDispatcher(): CoroutineDispatcher

    fun defaultDispatcher(): CoroutineDispatcher

    fun mainDispatcher(): CoroutineDispatcher
}

class RuntimeDispatchers @Inject constructor() : KaiaDispatchers {
    override fun ioDispatcher() = Dispatchers.IO
    override fun defaultDispatcher() = Dispatchers.Default
    override fun mainDispatcher() = Dispatchers.Main
}
