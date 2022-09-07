/**
 * Copyright 2021 ItLifeLang LLC
 */

package com.itlifelang.extrememovie.shared.data.api

import com.itlifelang.extrememovie.shared.BuildConfig
import java.util.concurrent.TimeUnit.SECONDS
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * The class defines related information about api.
 * It also manages api connection and communication between api and client.
 */
object ApiManager {
    private const val QUERY_PARAMS = "api_key"
    private const val TIMEOUT_DURATION = 10L

    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_DURATION, SECONDS)
        .readTimeout(TIMEOUT_DURATION, SECONDS)
        .writeTimeout(TIMEOUT_DURATION, SECONDS)
        .addInterceptor(getLoggingInterceptor())
        .addInterceptor(getHeaderInterceptor())
        .build()

    private fun getHeaderInterceptor(): Interceptor = Interceptor { chain ->
        val currentRequest = chain.request()
        val newUrl = currentRequest.url
            .newBuilder()
            .addQueryParameter(QUERY_PARAMS, BuildConfig.API_KEY)
            .build()
        val newRequest = currentRequest.newBuilder().apply { url(newUrl) }.build()
        chain.proceed(newRequest)
    }

    private fun getLoggingInterceptor(): Interceptor {
        val logLevel = when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }
        return HttpLoggingInterceptor().apply { level = logLevel }
    }
}
