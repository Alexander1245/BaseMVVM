package com.dart69.mvvm.viewmodels

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

fun repeatOnStarted(
    lifecycleOwner: LifecycleOwner,
    block: suspend () -> Unit
): Job = lifecycleOwner.lifecycleScope.launch {
    lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        block()
    }
}

fun <S> BaseViewModel<S>.statesAsFlow(): Flow<S> = flow {
    collectStates { state -> emit(state) }
}

fun <E> CommunicatorViewModel<*, E>.eventsAsFlow(): Flow<E> = flow {
    collectEvents { event -> emit(event) }
}