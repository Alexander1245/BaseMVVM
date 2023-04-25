package com.dart69.core.results

sealed class Results<T> {
    class Loading<T> : Results<T>()

    data class Success<T>(val data: T) : Results<T>()

    data class Error<T>(val error: Throwable) : Results<T>()
}

fun <T, R> Results<T>.map(mapper: (T) -> R): Results<R> =
    when (this) {
        is Results.Success -> Results.Success(mapper(data))
        is Results.Error -> Results.Error(error)
        is Results.Loading -> Results.Loading()
    }

fun <T> Results<T>.takeData(): T? = if (this is Results.Success) data else null

fun Results<*>.takeError(): Throwable? = if (this is Results.Error) error else null

fun Results<*>.isError(): Boolean = this is Results.Error

fun Results<*>.isLoading(): Boolean = this is Results.Loading

fun Results<*>.isSuccess(): Boolean = this is Results.Success