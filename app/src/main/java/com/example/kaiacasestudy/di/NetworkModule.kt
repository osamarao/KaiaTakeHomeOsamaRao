package com.example.kaiacasestudy.di

import com.example.kaiacasestudy.data.ExerciseService
import com.example.kaiacasestudy.data.OkHttpClientFactory
import com.example.kaiacasestudy.data.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @Provides
    @Singleton
    fun providesOkHttpClient(okHttpClientFactory: OkHttpClientFactory) =
        okHttpClientFactory.create()

    @Provides
    @Singleton
    fun providesBlogRetrofit(retrofitFactory: RetrofitFactory): Retrofit =
        retrofitFactory.createBlogRetrofit()

    @Provides
    @Singleton
    fun providesExerciseService(retrofit: Retrofit): ExerciseService =
        retrofit.create(ExerciseService::class.java)

}