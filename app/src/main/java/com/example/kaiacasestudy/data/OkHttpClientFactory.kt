package com.example.kaiacasestudy.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class OkHttpClientFactory @Inject constructor(
    private val httpLoggingInterceptor: HttpLoggingInterceptor
) {

    fun create(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
}