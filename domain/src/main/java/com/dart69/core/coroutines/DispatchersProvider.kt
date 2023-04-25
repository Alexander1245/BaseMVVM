package com.dart69.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersProvider {
    fun provideUiDispatcher(): CoroutineDispatcher

    fun provideBackgroundDispatcher(): CoroutineDispatcher

    companion object {
        operator fun invoke(): DispatchersProvider = object : DispatchersProvider {
            override fun provideUiDispatcher(): CoroutineDispatcher =
                Dispatchers.Main.immediate

            override fun provideBackgroundDispatcher(): CoroutineDispatcher =
                Dispatchers.IO
        }
    }
}