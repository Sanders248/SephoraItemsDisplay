package com.example.sephoraitemsdisplay.libraries.network

import retrofit2.HttpException
import retrofit2.Response

inline fun <reified T : Any?> apiCall(call: () -> Response<T>): Result<T> = try {
    val response: Response<T> = call()

    val result = when {
        !response.isSuccessful -> throw HttpException(response)
        Unit is T -> Unit
        null is T -> requireNotNull(response.body())
        else -> response.body() as T
    }

    Result.success(result)
} catch (e: Exception) {
    Result.failure(e)
}