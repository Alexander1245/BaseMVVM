package com.dart69.mvvm.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel<S>(initialState: S) : ViewModel() {
    protected open val states = MutableStateFlow(initialState)

    open suspend fun collectStates(collector: FlowCollector<S>): Nothing = states.collect(collector)
}