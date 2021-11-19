/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.result

import com.itlifelang.extrememovie.shared.result.Result.Error
import com.itlifelang.extrememovie.shared.result.Result.Success

/**
 * A sealed class handles data and its loading status.
 * @param R type of data if loading successes.
 */
sealed class Result<out R> {

    object Loading : Result<Nothing>()

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val e: Exception) : Result<Nothing>()

    override fun toString(): String = when (this) {
        is Success -> "Success[data=$data]"
        is Error -> "Error[exception=$e]"
        else -> "Loading"
    }
}

/**
 * An extension variable get data if loading successes. Otherwise, it returns null.
 */
val <T> Result<T>.data: T?
    get() = (this as? Success)?.data

/**
 * An extension function get data if loading successes. Otherwise, it implements and returns
 * [fallback]
 */
fun <T> Result<T>.successOr(fallback: () -> T): T = (this as? Success)?.data ?: fallback()

val Result<Any>.error: Exception?
    get() = (this as? Error)?.e
