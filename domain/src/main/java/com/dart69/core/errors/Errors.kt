package com.dart69.core.errors

import java.io.IOException

class NoNetworkError: IOException()

class NetworkTimeOutError: IOException()

class ApiError(val errorCode: Int): IOException()