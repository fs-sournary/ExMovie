package com.itlifelang.extrememovie.shared.usecase

import com.itlifelang.extrememovie.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(params: P): Result<R> = try {
        withContext(coroutineDispatcher) {
            val data = execute(params)
            Result.Success(data)
        }
    } catch (e: Exception) {
        Result.Error(e)
    }

    abstract fun execute(params: P): R
}
