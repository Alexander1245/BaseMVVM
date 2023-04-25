package com.dart69.data.response.wrapper

import com.dart69.core.mapper.Mapper
import retrofit2.Response

interface ResponseWrapper {
    suspend fun <T> wrap(
        errorCodeMapper: Mapper<Int, Throwable>,
        block: suspend () -> Response<T>,
    ): T
}

suspend fun <T> wrapResponseErrors(
    errorCodeMapper: Mapper<Int, Throwable>,
    block: suspend () -> Response<T>,
): T = ResponseWrapperImpl().wrap(errorCodeMapper, block)