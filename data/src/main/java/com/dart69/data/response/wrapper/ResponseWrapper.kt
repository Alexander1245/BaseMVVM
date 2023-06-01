package com.dart69.data.response.wrapper

import com.dart69.core.mapper.Mapper
import retrofit2.Response

interface ResponseWrapper {
    suspend fun <T> wrap(
        block: suspend () -> Response<T>,
    ): T
}

suspend fun <T> wrapResponseErrors(
    block: suspend () -> Response<T>,
): T = ResponseWrapperImpl().wrap(block)