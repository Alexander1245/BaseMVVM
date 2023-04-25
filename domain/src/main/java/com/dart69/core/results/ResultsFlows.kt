package com.dart69.core.results

import kotlinx.coroutines.flow.*

typealias ResultsFlow<T> = Flow<Results<T>>
typealias ResultsMutableSharedFlow<T> = MutableSharedFlow<Results<T>>
typealias ResultsMutableStateFlow<T> = MutableStateFlow<Results<T>>

fun <T> resultsFlowOf(block: suspend () -> T): ResultsFlow<T> = flow<Results<T>> {
    emit(Results.Success(block()))
}.onStart {
    emit(Results.Loading())
}.catch { error ->
    emit(Results.Error(error))
}

suspend fun <T> ResultsMutableSharedFlow<T>.emitResults(block: suspend () -> T) {
    emitAll(resultsFlowOf(block))
}

@Suppress("FunctionName")
fun <T> ResultsMutableStateFlow(initial: T): ResultsMutableStateFlow<T> =
    MutableStateFlow(Results.Success(initial))

fun ResultsFlow<*>.onLoading(block: suspend () -> Unit): ResultsFlow<*> =
    onEach { results ->
        if (results is Results.Loading) {
            block()
        }
    }

fun ResultsFlow<*>.onError(block: suspend (Throwable) -> Unit): ResultsFlow<*> =
    onEach { results ->
        if (results is Results.Error) {
            block(results.error)
        }
    }

fun <T> ResultsFlow<T>.onSuccess(block: suspend (T) -> Unit): ResultsFlow<T> =
    onEach { results ->
        if (results is Results.Success) {
            block(results.data)
        }
    }