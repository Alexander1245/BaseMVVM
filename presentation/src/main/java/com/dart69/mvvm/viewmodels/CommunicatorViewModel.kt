package com.dart69.mvvm.viewmodels

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class CommunicatorViewModel<S, E>(initialState: S) : BaseViewModel<S>(initialState) {
    protected open val events = MutableSharedFlow<E>()

    open suspend fun collectEvents(collector: FlowCollector<E>): Nothing = events.collect(collector)

    protected fun MutableSharedFlow<E>.launch(
        event: E,
        context: CoroutineContext = viewModelScope.coroutineContext
    ): Job = viewModelScope.launch(context) { emit(event) }
}