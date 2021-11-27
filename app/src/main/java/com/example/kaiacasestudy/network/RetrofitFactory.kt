package com.example.kaiacasestudy.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitFactory @Inject constructor(private val okhttpClient: OkHttpClient) {

    companion object {
        val contentType = "application/json".toMediaType()
        const val BASE_URL = "https://jsonblob.com/api/jsonBlob/"

        val json = Json {
            ignoreUnknownKeys = true
        }
    }

    fun createBlogRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(okhttpClient)
        .build()
}
