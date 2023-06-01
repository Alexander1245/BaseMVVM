package com.dart69.data.response.wrapper

import com.dart69.core.errors.ApiError
import com.dart69.core.errors.NetworkTimeOutError
import com.dart69.core.errors.NoNetworkError
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ResponseWrapperImpl : ResponseWrapper {
    override suspend fun <T> wrap(
        block: suspend () -> Response<T>,
    ): T = wrapNetworkErrors {
        val response = block()
        if (response.isSuccessful) {
            response.body()!!
        } else {
            throw ApiError(response.code())
        }
    }

    private suspend fun <T> wrapNetworkErrors(block: suspend () -> T): T =
        try {
            block()
        } catch (hostError: UnknownHostException) {
            throw NoNetworkError()
        } catch (timeOutError: SocketTimeoutException) {
            throw NetworkTimeOutError()
        }
}